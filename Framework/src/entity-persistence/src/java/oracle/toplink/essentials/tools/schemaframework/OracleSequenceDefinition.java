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
package oracle.toplink.essentials.tools.schemaframework;

import java.io.*;
import oracle.toplink.essentials.exceptions.*;
import oracle.toplink.essentials.sequencing.NativeSequence;
import oracle.toplink.essentials.internal.sessions.AbstractSession;

/**
 * <p>
 * <b>Purpose</b>: Support Oracle native sequence creation.
 *     Oracle has custom support for sequences.
 * <p>
 */
public class OracleSequenceDefinition extends SequenceDefinition {

    /** The increment can be used to support pre-allocation. */
    protected int increment;

    /** The start is the first sequence value that will be available for TopLink to use. */
    protected int start = 1;

    public OracleSequenceDefinition(String name, int preallocationSize) {
        super(name);
        setIncrement(preallocationSize);
    }

    public OracleSequenceDefinition(String name, int preallocationSize, int start) {
        super(name);
        // sequence value should be positive
        if(start <= 0) {
            start = 1;
        }
        setIncrement(preallocationSize);
        setStart(start);
	}
    
    public OracleSequenceDefinition(String name) {
        this(name, 1);
	}

    public OracleSequenceDefinition(NativeSequence sequence) {
        this(sequence.getName(), sequence.getPreallocationSize(), sequence.getInitialValue());
	}

    /**
     * INTERNAL:
     * Return the SQL required to create the Oracle sequence object.
     */
    public Writer buildCreationWriter(AbstractSession session, Writer writer) {
        try {
            writer.write("CREATE SEQUENCE ");
            writer.write(getFullName());
            if (getIncrement() != 1) {
                writer.write(" INCREMENT BY " + getIncrement());
            }
            // startWith value calculated using the initial value and increment.
            // The first time TopLink calls select nextval, the value equal to startWith is returned.
            // The first sequence value TopLink may assign is startWith - getIncrement() + 1.
            int startWith = getStart() + getIncrement() - 1;
            writer.write(" START WITH " + startWith);
        } catch (IOException ioException) {
            throw ValidationException.fileError(ioException);
        }
        return writer;
    }

    /**
     * INTERNAL:
     * Return the SQL required to drop the Oracle sequence object.
     */
    public Writer buildDeletionWriter(AbstractSession session, Writer writer) {
        try {
            writer.write("DROP SEQUENCE ");
            writer.write(getFullName());
        } catch (IOException ioException) {
            throw ValidationException.fileError(ioException);
        }
        return writer;
    }

    /**
     * INTERNAL:
     * Return the SQL required to alter INCREMENT BY
     */
    public Writer buildAlterIncrementWriter(AbstractSession session, Writer writer) {
        try {
            writer.write("ALTER SEQUENCE ");
            writer.write(getFullName());
            writer.write(" INCREMENT BY " + getIncrement());
        } catch (IOException ioException) {
            throw ValidationException.fileError(ioException);
        }
        return writer;
    }

    /**
     * INTERNAL:
     * Check if the sequence object already exists, in which case dont create it.
     */
    public boolean checkIfExist(AbstractSession session) throws DatabaseException {
        try {
            session.executeSelectingCall(new oracle.toplink.essentials.queryframework.SQLCall("SELECT " + getName() + ".NEXTVAL FROM DUAL"));
        } catch (DatabaseException exception) {
            return false;
        }
        return true;
    }

    /**
     * The increment can be used to support pre-allocation.
     */
    public int getIncrement() {
        return increment;
    }

    /**
     * The increment can be used to support pre-allocation.
     */
    public void setIncrement(int increment) {
        this.increment = increment;
    }

    /**
     * The start used as a starting value for sequence
     */
    public int getStart() {
        return start;
    }

    /**
     * The start used as a starting value for sequence
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * The start used as a starting value for sequence
     */
    public void setStartAndIncrement(int value) {
        setStart(1);
        setIncrement(value);
    }

    /**
     * INTERNAL:
     * Indicates whether alterIncrement is supported
     */
    public boolean isAlterSupported() {
        return true;
    }

    /**
     * INTERNAL:
     * Execute the SQL required to alter sequence increment.
     * Assume that the sequence exists.
     */
    public void alterOnDatabase(AbstractSession session) throws TopLinkException {
        try {
            session.executeNonSelectingCall(new oracle.toplink.essentials.queryframework.SQLCall(buildAlterIncrementWriter(session, new StringWriter()).toString()));
        } catch (DatabaseException exception) {
            createOnDatabase(session);
        }
    }

    /**
     * INTERNAL:
     * Execute the SQL required to alter sequence increment.
     * Assume that the sequence exists.
     */
    public void alterIncrement(AbstractSession session, Writer schemaWriter) throws ValidationException {
        if (schemaWriter == null) {
            this.alterOnDatabase(session);
        } else {
            this.buildAlterIncrementWriter(session, schemaWriter);
        }
    }
}
