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

import java.io.IOException;

import net.sf.commons.ssh.AuthenticationOptions;
import net.sf.commons.ssh.Connection;
import net.sf.commons.ssh.ConnectionFactory;
import net.sf.commons.ssh.PasswordAuthenticationOptions;
import net.sf.commons.ssh.PublicKeyAuthenticationOptions;
import net.sf.commons.ssh.jsch.JschConnectionFactory;

import org.mule.api.MuleException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.transport.AbstractConnector;


/* For general guidelines on writing transports see
   http://mule.mulesource.org/display/MULE/Writing+Transports */

/* IMPLEMENTATION NOTE: All configuaration for the transport should be set
   on the Connector object, this is the object that gets configured in
   MuleXml */

/**
 * <code>SshConnector</code> TODO document
 */
public class SshConnector extends AbstractConnector
{
	/* This constant defines the main transport protocol identifier */
	public static final String SSH = "ssh";
	public static final String SSH_PREFIX = "ssh.";
	public static final String SSH_OUT = SSH_PREFIX + "out";
	public static final String SSH_EXIT_STATUS = SSH_OUT + "exit_status";

	private String host;
	private int port;

	private String loginId;
	private byte[] password = "".getBytes();

	/** ex) privateKeyPath = "./conf/id_rsa" */
	private String privateKeyPath = null;
	
	/** @see <a href="http://commons-ssh.sourceforge.net/apidocs/index.html"/>AuthenticationOptions</a> */
	private AuthenticationOptions authOptions = null;

	private int kexTimeout;

	/* IMPLEMENTATION NOTE: Is called once all bean properties have been
	   set on the connector and can be used to validate and initialise the
	   connectors state. */
	public void doInitialise() throws InitialisationException
	{
		if(authOptions == null)
		{
			logger.debug("creating authentication options Object");
			if (privateKeyPath == null)
			{ // Password Auth
				authOptions = new PasswordAuthenticationOptions(loginId,
						new String(password));
			}
			else
			{ // PublicKey Auth
				authOptions = new PublicKeyAuthenticationOptions(loginId,
						privateKeyPath,
						new String(password));
				logger.trace("use private key : " + privateKeyPath);
			}
			
			logger.trace("authenticaction options : " + authOptions.getClass());
		}
		
		loginId = ""; // replace dummy id.
		password = "".getBytes(); //replace dummy password.
	}

	public ConnectionFactory getFactory(int soTimeout)
	{
		ConnectionFactory factory = new JschConnectionFactory();
		factory.setKexTimeout(kexTimeout);
		factory.setSoTimeout(soTimeout);
		return factory;
	}

	public void doConnect() throws Exception
	{
		//do nothing
	}

	public void doDisconnect() throws Exception
	{
		// do nothing
	}

	public void doStart() throws MuleException
	{
		//do nothing
	}

	public void doStop() throws MuleException
	{
		// do nothing
	}

	public void doDispose()
	{
		// do nothing.
	}
	
	Connection openSshConnection(int soTimeout) throws IOException
	{	
		logger.debug("open ssh connection : " + loginId + "@" + host + ":" + port);
		return getFactory(soTimeout).openConnection(host, port, authOptions);
	}
	
	/* following is getter and setter */
	public String getProtocol()
	{
		return SSH;
	}
	
	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public void setPassword(String password)
	{
		this.password = password.getBytes();
	}

	public void setPrivateKeyPath(String privateKeyPath)
	{
		this.privateKeyPath = privateKeyPath;
	}

	public int getKexTimeout()
	{
		return kexTimeout;
	}

	public void setKexTimeout(int kexTimeout)
	{
		this.kexTimeout = kexTimeout;
	}
	
	AuthenticationOptions getAuthOptions()
	{
		return authOptions;
	}

	public void setAuthOptions(AuthenticationOptions authOptions)
	{
		this.authOptions = authOptions;
	}
}
