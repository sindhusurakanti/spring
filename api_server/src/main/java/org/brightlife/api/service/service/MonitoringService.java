package org.brightlife.api.service.service;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import org.brightlife.api.service.controller.MonitoringController;
import org.brightlife.api.service.model.dto.response.HealthStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class MonitoringService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MonitoringController.class);
	
	public HealthStatus getHealthStatus() {
		HealthStatus hs = new HealthStatus();
		Runtime runtime = Runtime.getRuntime();

		int mb = 1024 * 1024;
		hs.freeMemoryInMBs = runtime.freeMemory() / mb;
		hs.maxMemoryInMBs = runtime.maxMemory() / mb;
		hs.totalMemoryInMBs = runtime.totalMemory() / mb;
		hs.usedMemoryInMBs = (runtime.totalMemory() - runtime.freeMemory()) / mb;

		LOGGER.info("getServerHealthStatus : Got Memory Usage");

		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

		hs.threadCount = threadBean.getThreadCount();
		hs.peakThreadCount = threadBean.getPeakThreadCount();
		long[] deadlockThreads = threadBean.findDeadlockedThreads();
		hs.deadLockThreadCount = (deadlockThreads != null) ? deadlockThreads.length : 0;

		hs.daemonThreadCount = threadBean.getDaemonThreadCount();
		hs.totalStartedThreadCount = threadBean.getTotalStartedThreadCount();
		RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();

		hs.virtualMachineStartime = runtimeBean.getStartTime();
		hs.virtualMachineUptimeInSecs = runtimeBean.getUptime() / 1000;
		return hs;
	}
}
