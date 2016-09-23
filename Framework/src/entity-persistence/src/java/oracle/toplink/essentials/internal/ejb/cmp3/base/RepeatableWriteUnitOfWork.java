/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * // Copyright (c) 1998, 2007, Oracle. All rights reserved.
 * 
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package oracle.toplink.essentials.internal.ejb.cmp3.base;

import java.util.Enumeration;

import oracle.toplink.essentials.descriptors.ClassDescriptor;
import oracle.toplink.essentials.internal.descriptors.DescriptorIterator;
import oracle.toplink.essentials.internal.helper.IdentityHashtable;
import oracle.toplink.essentials.internal.localization.ExceptionLocalization;
import oracle.toplink.essentials.internal.sessions.UnitOfWorkImpl;
import oracle.toplink.essentials.exceptions.DatabaseException;
import oracle.toplink.essentials.exceptions.OptimisticLockException;
import oracle.toplink.essentials.internal.sessions.UnitOfWorkChangeSet;
import oracle.toplink.essentials.internal.sessions.ObjectChangeSet;
import oracle.toplink.essentials.mappings.ForeignReferenceMapping;


public class RepeatableWriteUnitOfWork extends UnitOfWorkImpl {
    
    /** Used to store the final UnitOfWorkChangeSet for merge into the shared cache */
    protected UnitOfWorkChangeSet cumulativeUOWChangeSet;
    /** Used to store objects already deleted from the db and unregistered */
    protected IdentityHashtable unregisteredDeletedObjectsCloneToBackupAndOriginal;
    
    /** Used to determine if UnitOfWork should commit and rollback transactions 
     * This is used when an EntityTransaction is controlling the transaction
     */
    protected boolean shouldTerminateTransaction;
    
    /** Used to determine if UnitOfWork.synchronizeAndResume method should
     * resume (the normal behaviour); or alternatively clear the UnitOfWork.
     */
    protected boolean shouldClearForCloseInsteadOfResume = false;
    
    public RepeatableWriteUnitOfWork(oracle.toplink.essentials.internal.sessions.AbstractSession parentSession){
        super(parentSession);
        this.shouldTerminateTransaction = true;
        this.shouldNewObjectsBeCached = true;
    }
    
    /**
     * INTERNAL:
     * Call this method if the uow will no longer used for comitting transactions:
     * all the changes sets will be dereferenced, and (optionally) the cache cleared.
     * If the uow is not released, but rather kept around for ValueHolders, then identity maps shouldn't be cleared:
     * the parameter value should be 'false'. The lifecycle set to Birth so that uow ValueHolder still could be used.
     * Alternatively, if called from release method then everything should go and therefore parameter value should be 'true'.
     * In this case lifecycle won't change - uow.release (optionally) calls this method when it (uow) is already dead.
     * The reason for calling this method from release is to free maximum memory right away:
     * the uow might still be referenced by objects using UOWValueHolders (though they shouldn't be around
     * they still might).
     */
    public void clearForClose(boolean shouldClearCache){
        super.clearForClose(shouldClearCache);
        this.cumulativeUOWChangeSet = null;
        this.unregisteredDeletedObjectsCloneToBackupAndOriginal = null;
    }
    
    /**
     * INTERNAL:
     * Indicates whether clearForClose methor should be called by release method.
     */
    public boolean shouldClearForCloseOnRelease() {
        return true;
    }
    
    /**
     * INTERNAL:
     * Commit the changes to any objects to the parent.
     */
    public void commitRootUnitOfWork() throws DatabaseException, OptimisticLockException {
        commitToDatabaseWithChangeSet(false);
        // unit of work has been committed so it's ok to set the cumulative into the UOW for merge
        if(this.cumulativeUOWChangeSet != null) {
            this.cumulativeUOWChangeSet.mergeUnitOfWorkChangeSet((UnitOfWorkChangeSet)this.getUnitOfWorkChangeSet(), this, true);
            setUnitOfWorkChangeSet(this.cumulativeUOWChangeSet);
        }

        commitTransactionAfterWriteChanges(); // this method will commit the transaction
                                              // and set the transaction flags appropriately

        // Merge after commit	
        mergeChangesIntoParent();
    }

    /**
     * INTERNAL:
     * Traverse the object to find references to objects not registered in this unit of work.
     */
    public void discoverUnregisteredNewObjects(Object clone, IdentityHashtable knownNewObjects, IdentityHashtable unregisteredExistingObjects, IdentityHashtable visitedObjects) {
        // This define an inner class for process the itteration operation, don't be scared, its just an inner class.
        DescriptorIterator iterator = new DescriptorIterator() {
            public void iterate(Object object) {
                // If the object is read-only the do not continue the traversal.
                if (isClassReadOnly(object.getClass()) || isObjectDeleted(object)) {
                    this.setShouldBreak(true);
                    return;
                }
                //check for null mapping, this may be the first iteration
                if ((getCurrentMapping() != null) && ((ForeignReferenceMapping)getCurrentMapping()).isCascadePersist() ){
                    ((RepeatableWriteUnitOfWork)getSession()).registerNewObjectForPersist(object, new IdentityHashtable());
                }else{
                    if (!getCloneMapping().containsKey(object)){
                        if (! checkForUnregisteredExistingObject(object)){
                            throw new IllegalStateException(ExceptionLocalization.buildMessage("new_object_found_during_commit", new Object[]{object}));
                        }else{
                            ((IdentityHashtable)getUnregisteredExistingObjects()).put(object, object);
                            this.setShouldBreak(true);
                            return;
                        }
                    }
                }
            }
        };

        //set the collection in the UnitofWork to be this list
        setUnregisteredExistingObjects(unregisteredExistingObjects);

        iterator.setVisitedObjects(visitedObjects);
        iterator.setResult(knownNewObjects);
        iterator.setSession(this);
        // When using wrapper policy in EJB the iteration should stop on beans,
        // this is because EJB forces beans to be registered anyway and clone identity can be violated
        // and the violated clones references to session objects should not be traversed.
        iterator.setShouldIterateOverWrappedObjects(false);
        iterator.startIterationOn(clone);
    }
    
    /**
     * INTERNAL:
     * Has writeChanges() been attempted on this UnitOfWork?  It may have
     * either suceeded or failed but either way the UnitOfWork is in a highly
     * restricted state.
     */
    public boolean isAfterWriteChangesButBeforeCommit() {
        //dont' check for writechanges failure.
        return (getLifecycle() == CommitTransactionPending);
    }

    /**
     * INTERNAL:
     * Return if the object has been deleted in this unit of work.
     */
    public boolean isObjectDeleted(Object object) {
        if(super.isObjectDeleted(object)) {
            return true;
        } else {
            if(unregisteredDeletedObjectsCloneToBackupAndOriginal != null) {
                if(unregisteredDeletedObjectsCloneToBackupAndOriginal.containsKey(object)) {
                    return true;
                }
            }
            if (hasObjectsDeletedDuringCommit()) {
                return getObjectsDeletedDuringCommit().containsKey(object);
            } else {
                return false;
            }
        }
    }

    /**
     * INTERNAL:
     * For synchronized units of work, dump SQL to database
     */
    public void issueSQLbeforeCompletion() {

        super.issueSQLbeforeCompletion(false);

        if (this.cumulativeUOWChangeSet != null && this.getUnitOfWorkChangeSet() != null){
            // unit of work has been committed so it's ok to set the cumulative into the UOW for merge
            this.cumulativeUOWChangeSet.mergeUnitOfWorkChangeSet((UnitOfWorkChangeSet)this.getUnitOfWorkChangeSet(), this, true);
            setUnitOfWorkChangeSet(this.cumulativeUOWChangeSet);
        }

        commitTransactionAfterWriteChanges(); // this method will commit the transaction
                                              // and set the transaction flags appropriately
    }
    
    /**
     * INTERNAL:
     * Merge the attributes of the clone into the unit of work copy.
     */
    public Object mergeCloneWithReferences(Object rmiClone, int cascadePolicy, boolean forceCascade) {
        Object mergedObject = super.mergeCloneWithReferences(rmiClone, cascadePolicy, forceCascade);
        
        if (mergedObject != null) {
            // This will assign a sequence number to the merged object if it 
            // doesn't already have one (that is, it is a new object).
            assignSequenceNumber(mergedObject);
        }
        
        return mergedObject;
    }

    /**
     * INTERNAL:
     * This method is used internally to update the tracked objects if required
     */
    public void updateChangeTrackersIfRequired(Object objectToWrite, ObjectChangeSet changeSetToWrite, UnitOfWorkImpl uow, ClassDescriptor descriptor) {
        descriptor.getObjectChangePolicy().updateWithChanges(objectToWrite, changeSetToWrite, uow, descriptor);
    }

    /**
     * INTERNAL:
     * This method will cause the all of the tracked objects at this level to have
     * their changes written to the database.  It will then decrement the depth
     * level.
     */
    public void writeChanges() {
            if(unregisteredDeletedObjectsCloneToBackupAndOriginal == null) {
                unregisteredDeletedObjectsCloneToBackupAndOriginal = new IdentityHashtable(2);
            }
            IdentityHashtable allObjectsList = new IdentityHashtable();
            IdentityHashtable visitedNodes = new IdentityHashtable(2);
            IdentityHashtable newObjects = new IdentityHashtable(2);
            IdentityHashtable existingObjects = new IdentityHashtable(2);
            IdentityHashtable insertedNewObjects = new IdentityHashtable(2);
            for (Enumeration clones = getCloneMapping().keys(); clones.hasMoreElements();){
                Object object = clones.nextElement();
                discoverUnregisteredNewObjects(object, newObjects, existingObjects, visitedNodes);
                allObjectsList.put(object, object);
            }
            for (Enumeration newClones = getNewObjectsCloneToOriginal().keys();newClones.hasMoreElements();){
                Object object = newClones.nextElement();
                assignSequenceNumber(object);
                insertedNewObjects.put(object, object);
                // add potentially newly discovered new objects
                allObjectsList.put(object, object);
            }

            if (getUnitOfWorkChangeSet() == null) {
                setUnitOfWorkChangeSet(new UnitOfWorkChangeSet());
            }
            calculateChanges(allObjectsList, (UnitOfWorkChangeSet)getUnitOfWorkChangeSet());
            // write those changes to the database.
            UnitOfWorkChangeSet changeSet = (UnitOfWorkChangeSet)getUnitOfWorkChangeSet();
            if (!changeSet.hasChanges() && !changeSet.hasForcedChanges() && ! this.hasDeletedObjects() && ! this.hasModifyAllQueries()){
            	return;
            }
            try {
                commitToDatabaseWithPreBuiltChangeSet(changeSet, false);
                this.writesCompleted();
            } catch (RuntimeException ex) {
                setLifecycle(WriteChangesFailed);
                throw ex;
            }
            //bug 4730595: fix puts deleted objects in the UnitOfWorkChangeSet as they are removed.
            getDeletedObjects().clear();

            // unregister all deleted objects,
            // keep them along with their original and backup values in unregisteredDeletedObjectsCloneToBackupAndOriginal
            Enumeration enumDeleted = getObjectsDeletedDuringCommit().keys();
            while(enumDeleted.hasMoreElements()) {
                Object deletedObject = enumDeleted.nextElement();
                Object[] backupAndOriginal = {getCloneMapping().get(deletedObject), getCloneToOriginals().get(deletedObject)};
                unregisteredDeletedObjectsCloneToBackupAndOriginal.put(deletedObject, backupAndOriginal);
                unregisterObject(deletedObject);
            }
            getObjectsDeletedDuringCommit().clear();

            if(this.cumulativeUOWChangeSet == null) {
                this.cumulativeUOWChangeSet = (UnitOfWorkChangeSet)getUnitOfWorkChangeSet();
            } else {
                //merge those changes back into the backup clones and the final uowChangeSet
                this.cumulativeUOWChangeSet.mergeUnitOfWorkChangeSet((UnitOfWorkChangeSet)getUnitOfWorkChangeSet(), this, true);
            }
            //clean up
            setUnitOfWorkChangeSet(new UnitOfWorkChangeSet());
            Enumeration enumtr = insertedNewObjects.elements();
            while (enumtr.hasMoreElements()) {
                Object clone = enumtr.nextElement();
                Object original = getNewObjectsCloneToOriginal().remove(clone);
                if (original != null) {
                    getNewObjectsOriginalToClone().remove(original);
                    //no longer new to this unit of work 
                    getCloneToOriginals().put(clone, original);
                }
            }
        }

    /**
     * INTERNAL:
     * Called only by registerNewObjectForPersist method,
     * and only if newObject is not already registered.
     * If newObject is found in
     * unregisteredDeletedObjectsCloneToBackupAndOriginal then it's re-registered,
     * otherwise the superclass method called.
     */
    protected void registerNotRegisteredNewObjectForPersist(Object newObject, ClassDescriptor descriptor) {
        if(unregisteredDeletedObjectsCloneToBackupAndOriginal != null) {
            Object[] backupAndOriginal = (Object[])unregisteredDeletedObjectsCloneToBackupAndOriginal.remove(newObject);
            if(backupAndOriginal != null) {
                // backup
                getCloneMapping().put(newObject, backupAndOriginal[0]);
                // original
                registerNewObjectClone(newObject, backupAndOriginal[1], descriptor);

                // Check if the new objects should be cached.
                registerNewObjectInIdentityMap(newObject, newObject);
                
                return;
            }
        }
        super.registerNotRegisteredNewObjectForPersist(newObject, descriptor);
    }

    /**
     * INTERNAL:
     * This is internal to the uow, transactions should not be used explictly in a uow.
     * The uow shares its parents transactions.
     */
    public void rollbackTransaction() throws DatabaseException {
        if (this.shouldTerminateTransaction || getParent().getTransactionMutex().isNested()){
            super.rollbackTransaction();
        }else{
            //rollback called which means txn failed.
            //but rollback was stopped by entitytransaction which means the
            //transaction will want to call release later.  Make sure release
            //will rollback transaction.
            setWasTransactionBegunPrematurely(true);
        }
    }

    /**
     * INTERNAL
     * Synchronize the clones and update their backup copies.
     * Called after commit and commit and resume.
     */
    public void synchronizeAndResume() {
        if(this.shouldClearForCloseInsteadOfResume()) {
            this.clearForClose(false);
        } else {
            this.cumulativeUOWChangeSet = null;
            this.unregisteredDeletedObjectsCloneToBackupAndOriginal = null;
            super.synchronizeAndResume();
        }
    }
    
    /**
     * INTERNAL:
     * Called only by UnitOfWorkIdentityMapAccessor.getAndCloneCacheKeyFromParent method.
     * Return unregisteredDeletedClone corresponding to the passed original, or null
     */
    public Object getUnregisteredDeletedCloneForOriginal(Object original) {
        if(unregisteredDeletedObjectsCloneToBackupAndOriginal != null) {
            Enumeration keys = unregisteredDeletedObjectsCloneToBackupAndOriginal.keys();
            Enumeration values = unregisteredDeletedObjectsCloneToBackupAndOriginal.elements();
            while(keys.hasMoreElements()) {
                Object deletedObjectClone = keys.nextElement();
                Object[] backupAndOriginal = (Object[])values.nextElement();
                Object currentOriginal = backupAndOriginal[1];
                if(original == currentOriginal) {
                    return deletedObjectClone;
                }
            }
        }
        return null;
    }
    
  /**
   * INTERNAL:
   * Wraps the oracle.toplink.essentials.exceptions.OptimisticLockException in a  
   * javax.persistence.OptimisticLockException. This conforms to the EJB3 specs
   * @param commitTransaction 
   */
    protected void commitToDatabase(boolean commitTransaction) {
        try {
            super.commitToDatabase(commitTransaction);
        } catch (oracle.toplink.essentials.exceptions.OptimisticLockException ole) {
            throw new javax.persistence.OptimisticLockException(ole);
        }
    }

    /**
     * INTERNAL:
     * This is internal to the uow, transactions should not be used explictly in a uow.
     * The uow shares its parents transactions.
     */
    public void commitTransaction() throws DatabaseException {
        if (this.shouldTerminateTransaction || getParent().getTransactionMutex().isNested()){
            super.commitTransaction();
        }
    }

    public void setShouldTerminateTransaction(boolean shouldTerminateTransaction) {
        this.shouldTerminateTransaction = shouldTerminateTransaction;
    }
    
    public void setShouldClearForCloseInsteadOfResume(boolean shouldClearForCloseInsteadOfResume) {
        this.shouldClearForCloseInsteadOfResume = shouldClearForCloseInsteadOfResume;
    }

    public boolean shouldClearForCloseInsteadOfResume() {
        return shouldClearForCloseInsteadOfResume;
    }
}
