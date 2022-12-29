package com.b2c.Local.B2C.securities.service;


import com.b2c.Local.B2C.securities.base.ManagementInterface;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class ManagementService implements ManagementInterface {

    SpringSessionBackedSessionRegistry<? extends Session> springSessionBackedSessionRegistry;

    @Override
    public Map<String, Object> getRuntime() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("JvmName",ManagementFactory.getRuntimeMXBean().getName());
        objectMap.put("StartTime", new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
        return objectMap;
    }

    @Override
    public Map<String, Object> getThreadsDetails() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("AllThreadsCount", ManagementFactory.getThreadMXBean().getThreadCount());
        objectMap.put("CurrentThreadDetail", ManagementFactory.getThreadMXBean().getThreadInfo(Thread.currentThread().getId()));
        objectMap.put("AllThreadsDetails", ManagementFactory.getThreadMXBean().getThreadInfo(ManagementFactory.getThreadMXBean().getAllThreadIds()));
        return objectMap;
    }

    @Override
    public Map<String, Object> getMemoryDetails() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("Heap Memory Max Bytes", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax());
        objectMap.put("Heap Memory Used Bytes", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed());
        objectMap.put("Non Heap Memory Usage Bytes", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed());
        objectMap.put("Non Heap Memory Max Bytes", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax());
        return objectMap;
    }
}
