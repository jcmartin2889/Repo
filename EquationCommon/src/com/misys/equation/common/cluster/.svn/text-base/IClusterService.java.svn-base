package com.misys.equation.common.cluster;

/**
 * Cluster service interface
 * 
 * If other services are identified, a common service interface could be extracted
 */
public interface IClusterService
{
	/** Starts the service */
	public void start();

	/** Stops the service */
	public void stop();

	/** Publish a cluster wide command */
	public void publishCommand(CommandData commandData);

	/** The id of this node */
	public String getNodeId();

}
