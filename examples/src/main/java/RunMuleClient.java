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


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.DefaultMuleMessage;
import org.mule.MuleMessageTestCase;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.config.MuleProperties;
import org.mule.module.client.MuleClient;
import org.mule.transport.ssh.SshConnector;

public class RunMuleClient
{
	private static final Log logger = LogFactory.getLog(RunMuleClient.class);
	/**
	 * @param args
	 * @throws MuleException 
	 */
	public static void main(String[] args) throws MuleException
	{
		RunMuleClient wrapper = new RunMuleClient();
		wrapper.start();
	
	}
	
	public void start() throws MuleException
	{
		MuleMessage response = new DefaultMuleMessage(null);
		logger.info("start RunMuleClient");
		MuleClient client = new MuleClient("./mule-ssh-sample.xml");
		try{
			client.getMuleContext().start();
	
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(MuleProperties.MULE_EVENT_TIMEOUT_PROPERTY, "3000");
			
			response = new DefaultMuleMessage(null);
	
				response = client.send("vm://test", "echo hello world!", properties);
		}catch(MuleException e){
			e.printStackTrace();
		}finally{
			client.getMuleContext().dispose();
			client.dispose();
		}
		
		try
		{
			logger.info("response : " + response.getPayloadAsString());
			//System.out.println("response : " + response.getPayloadAsString());

			logger.info("message properties : " + response.getProperty(SshConnector.SSH_EXIT_STATUS));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
