package com.misys.equation.common.cluster;

import com.google.gson.Gson;

/**
 * This class provides support for a clustered environment by replicating actions to all nodes in the cluster.
 * 
 * The current implementation uses JMS
 */
public class ClusterService implements IClusterService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ClusterService.java 17823 2014-01-30 09:23:00Z perkinj1 $";

	ClusterTransportJms transport;
	String nodeId;

	public ClusterService(String nodeId)
	{
		this.nodeId = nodeId;
	}

	@Override
	public void start()
	{
		transport = new ClusterTransportJms(this);
		transport.start();
	}

	@Override
	public void stop()
	{
		if (transport != null)
		{
			transport.stop();
		}
	}

	@Override
	public void publishCommand(CommandData commandData)
	{
		commandData.setOriginator(getNodeId());
		String json = new Gson().toJson(commandData);
		transport.publish(json);
	}

	@Override
	public String getNodeId()
	{
		return nodeId;
	}
}
