/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and
 *    "Apache Geronimo" must not be used to endorse or promote products
 *    derived from this software without prior written permission. For
 *    written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    "Apache Geronimo", nor may "Apache" appear in their name, without
 *    prior written permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * ====================================================================
 */

//
// This source code implements specifications defined by the Java
// Community Process. In order to remain compliant with the specification
// DO NOT add / change / or delete method signatures!
//

package javax.jms;

/** A <CODE>ConnectionFactory</CODE> object encapsulates a set of connection
 * configuration
 * parameters that has been defined by an administrator. A client uses
 * it to create a connection with a JMS provider.
 *
 * <P>A <CODE>ConnectionFactory</CODE> object is a JMS administered object and
 *  supports concurrent use.
 *
 * <P>JMS administered objects are objects containing configuration
 * information that are created by an administrator and later used by
 * JMS clients. They make it practical to administer the JMS API in the
 * enterprise.
 *
 * <P>Although the interfaces for administered objects do not explicitly
 * depend on the Java Naming and Directory Interface (JNDI) API, the JMS API
 * establishes the convention that JMS clients find administered objects by
 * looking them up in a JNDI namespace.
 *
 * <P>An administrator can place an administered object anywhere in a
 * namespace. The JMS API does not define a naming policy.
 *
 * <P>It is expected that JMS providers will provide the tools an
 * administrator needs to create and configure administered objects in a
 * JNDI namespace. JMS provider implementations of administered objects
 * should be both <CODE>javax.jndi.Referenceable</CODE> and
 * <CODE>java.io.Serializable</CODE> so that they can be stored in all
 * JNDI naming contexts. In addition, it is recommended that these
 * implementations follow the JavaBeans<SUP><FONT SIZE="-2">TM</FONT></SUP>
 * design patterns.
 *
 * <P>This strategy provides several benefits:
 *
 * <UL>
 *   <LI>It hides provider-specific details from JMS clients.
 *   <LI>It abstracts administrative information into objects in the Java
 *       programming language ("Java objects")
 *       that are easily organized and administered from a common
 *       management console.
 *   <LI>Since there will be JNDI providers for all popular naming
 *       services, this means that JMS providers can deliver one implementation
 *       of administered objects that will run everywhere.
 * </UL>
 *
 * <P>An administered object should not hold on to any remote resources.
 * Its lookup should not use remote resources other than those used by the
 * JNDI API itself.
 *
 * <P>Clients should think of administered objects as local Java objects.
 * Looking them up should not have any hidden side effects or use surprising
 * amounts of local resources.
 *
 * @version     $Revision: 1.1 $ $Date: 2003/08/16 02:29:57 $
 * @author      Mark Hapner
 * @author      Rich Burridge
 * @author      Kate Stout
 *
 * @see         javax.jms.Connection
 * @see         javax.jms.QueueConnectionFactory
 * @see         javax.jms.TopicConnectionFactory
 */

public interface ConnectionFactory {
    /** Creates a connection with the default user identity.
     * The connection is created in stopped mode. No messages
     * will be delivered until the <code>Connection.start</code> method
     * is explicitly called.
     *
     * @return a newly created connection
     *
     * @exception JMSException if the JMS provider fails to create the
     *                         connection due to some internal error.
     * @exception JMSSecurityException  if client authentication fails due to
     *                         an invalid user name or password.
     * @since 1.1
     */

    Connection createConnection() throws JMSException;


    /** Creates a connection with the specified user identity.
     * The connection is created in stopped mode. No messages
     * will be delivered until the <code>Connection.start</code> method
     * is explicitly called.
     *
     * @param userName the caller's user name
     * @param password the caller's password
     *
     * @return a newly created  connection
     *
     * @exception JMSException if the JMS provider fails to create the
     *                         connection due to some internal error.
     * @exception JMSSecurityException  if client authentication fails due to
     *                         an invalid user name or password.
     * @since 1.1
     */

    Connection createConnection(String userName, String password) throws JMSException;
}
