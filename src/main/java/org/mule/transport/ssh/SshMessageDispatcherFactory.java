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

import org.mule.transport.AbstractMessageDispatcherFactory;
import org.mule.api.MuleException;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.api.transport.MessageDispatcher;

/**
 * <code>SshMessageDispatcherFactory</code> Todo document
 */

public class SshMessageDispatcherFactory extends AbstractMessageDispatcherFactory
{

    /* For general guidelines on writing transports see
       http://mule.mulesource.org/display/MULE/Writing+Transports */

    public MessageDispatcher create(OutboundEndpoint endpoint) throws MuleException
    {
        return new SshMessageDispatcher(endpoint);
    }

}
