/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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

package javax.jms;

/** An <CODE>XAQueueConnection</CODE> provides the same create options as 
  * <CODE>QueueConnection</CODE> (optional).  
  * The only difference is that an <CODE>XAConnection</CODE> is by definition 
  * transacted.
  *
  *<P>The <CODE>XAQueueConnection</CODE> interface is optional.  JMS providers 
  * are not required to support this interface. This interface is for 
  * use by JMS providers to support transactional environments. 
  * Client programs are strongly encouraged to use the transactional support
  * available in their environment, rather than use these XA
  * interfaces directly. 
  *
  * @version     1.1 February 2 - 2002
  * @author      Mark Hapner
  * @author      Rich Burridge
  * @author      Kate Stout
  *
  * @see         javax.jms.XAConnection
  */

public interface XAQueueConnection 
	extends XAConnection, QueueConnection {

    /** Creates an <CODE>XAQueueSession</CODE> object.
      *  
      * @return a newly created <CODE>XAQueueSession</CODE>
      *  
      * @exception JMSException if the <CODE>XAQueueConnection</CODE> object 
      *                         fails to create an XA queue session due to some
      *                         internal error.
      */ 

    XAQueueSession
    createXAQueueSession() throws JMSException;

    /** Creates an <CODE>XAQueueSession</CODE> object.
      *
      * @param transacted       usage undefined
      * @param acknowledgeMode  usage undefined
      *  
      * @return a newly created <CODE>XAQueueSession</CODE>
      *  
      * @exception JMSException if the <CODE>XAQueueConnection</CODE> object 
      *                         fails to create an XA queue session due to some
      *                         internal error.
      */ 
    QueueSession
    createQueueSession(boolean transacted,
                       int acknowledgeMode) throws JMSException;
}
