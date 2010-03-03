/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) Osaka Gas Information System Research Institute Co.,Ltd. All rights reserved. http://www.ogis-ri.co.jp/
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.ssh.config;

import org.mule.config.spring.handlers.AbstractMuleNamespaceHandler;
import org.mule.endpoint.AbstractEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.transport.ssh.SshConnector;

/**
 * Registers a Bean Definition Parser for handling <code><ssh:connector></code>
 * elements and supporting endpoint elements.
 */
public class SshNamespaceHandler extends AbstractMuleNamespaceHandler
{
	public static final String SSH_ATTRIBUTE = "ssh";
	public static final String[] ADDRESS_ATTRIBUTES = { SSH_ATTRIBUTE };
	public static final String MAPPINGS = "SEND=" + SshConnector.SSH_OUT;
	
	public static final String HOST = "host";
	public static final String PORT = "port";
	public static final String LOGIN_ID = "loginId";
	public static final String PASSWORD = "password";
	public static final String PRIVATE_KEY_PATH = "privateKeyPath";
	public static final String RESOPNSE_TIMEOUT = "responseTimeout";
	public static final String KEX_TIMEOUT = "kexTimeout"; // key exchange timeout

	public static final String SUDO_PASSWORD = "sudoPassword";
	public static final String USE_SUDO = "useSudo";


	public static final String[] ENDPOINT_ATTRIBUTES = {
			SUDO_PASSWORD, USE_SUDO, RESOPNSE_TIMEOUT
			};
	public static final String[] ALL_ATTRIBUTES = {
			HOST, PORT, LOGIN_ID, PASSWORD,
			PRIVATE_KEY_PATH, RESOPNSE_TIMEOUT, KEX_TIMEOUT,
			SUDO_PASSWORD, USE_SUDO
			};

	public void init()
	{
		/* This creates handlers for 'endpoint', 'outbound-endpoint' and 'inbound-endpoint' elements.
		   The defaults are sufficient unless you have endpoint styles different from the Mule standard ones
		   The URIBuilder as constants for common required attributes, but you can also pass in a user-defined String[].
		 */
		
		registerStandardTransportEndpoints(SshConnector.SSH, ADDRESS_ATTRIBUTES)
				.addMapping(SSH_ATTRIBUTE, MAPPINGS).addAlias(SSH_ATTRIBUTE, URIBuilder.PATH)
				.addAlias(USE_SUDO, USE_SUDO)
				.addAlias(SUDO_PASSWORD, SUDO_PASSWORD)
				.addAlias(RESOPNSE_TIMEOUT, AbstractEndpointBuilder.PROPERTY_RESPONSE_TIMEOUT);
	       //registerBeanDefinitionParser("outbound-endpoint", new GenericEndpointDefinitionParser(OutboundEndpointFactoryBean.class));
		
		/* This will create the handler for your custom 'connector' element.  You will need to add handlers for any other
		   xml elements you define.  For more information see:
		   http://www.mulesource.org/display/MULE2USER/Creating+a+Custom+XML+Namespace
		*/
		registerConnectorDefinitionParser(SshConnector.class);
	}
}
