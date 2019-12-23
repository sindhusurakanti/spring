package org.brightlife.api.service.model.dto.response;

public class HealthStatus {
public String serverName = "";
	
	public long freeMemoryInMBs = 0L;
	public long totalMemoryInMBs = 0L;
	public long maxMemoryInMBs = 0L;
	public long usedMemoryInMBs = 0L;
	
	public long threadCount = 0L;
	public long peakThreadCount = 0L;
	public long deadLockThreadCount = 0L;
	public long daemonThreadCount = 0L;
	public long totalStartedThreadCount = 0L;
	public long virtualMachineStartime = 0L;
	public long virtualMachineUptimeInSecs = 0L;
}
