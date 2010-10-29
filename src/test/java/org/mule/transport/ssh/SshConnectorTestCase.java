/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) 2009 Osaka Gas Information System Research Institute Co., Ltd. 
 * All rights reserved.  http://www.ogis-ri.co.jp/
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package org.mule.transport.ssh;

import org.mule.api.transport.Connector;
import org.mule.transport.AbstractConnectorTestCase;

public class SshConnectorTestCase extends AbstractConnectorTestCase
{

    @Override
	public void testConnectorListenerSupport() throws Exception
	{
		// not supported
	}


	/* For general guidelines on writing transports see
       http://mule.mulesource.org/display/MULE/Writing+Transports */
	String host = "localhost";
	int port = 22;

	String loginId = "user";
	String password = "keypass";

	String privateKeyPath = "./key/test.id_rsa";

	int connectionTimeout = 5000;
	int kexTimeout = 20000;
	String sudoPassword = "sudopwd";
	
    public Connector createConnector() throws Exception
    {
        /* IMPLEMENTATION NOTE: Create and initialise an instance of your
           connector here. Do not actually call the connect method. */
    	
        SshConnector c = new SshConnector(muleContext);
        c.setName("Test");
        c.setHost(host);
        c.setPort(port);
        
        c.setLoginId(loginId);
        c.setPrivateKeyPath(privateKeyPath);
        c.setPassword(password);
        
        //c.setSudoPassword(sudoPassword);
        
        //c.setConnectionTimeout(connectionTimeout);
        c.setKexTimeout(kexTimeout);
        
        // TODO Set any additional properties on the connector here

        return c;
    }

    public String getTestEndpointURI()
    {
        // TODO Return a valid endpoint for you transport here
        
    	return "ssh://ssh.out";
    }

    public Object getValidMessage() throws Exception
    {
        // TODO Return an valid message for your transport
        //throw new UnsupportedOperationException("getValidMessage");
    	
    	return "  PID TTY          TIME CMD";
    }


    public void testProperties() throws Exception
    {
        // TODO test setting and retrieving any custom properties on the
        // Connector as necessary
    	return;
    }
}
