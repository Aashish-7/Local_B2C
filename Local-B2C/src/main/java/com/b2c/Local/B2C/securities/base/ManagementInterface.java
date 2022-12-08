package com.b2c.Local.B2C.securities.base;

import java.util.Map;

public interface ManagementInterface {

    Map<String, Object> getRuntime();

    Map<String, Object> getThreadsDetails();

    Map<String, Object> getMemoryDetails();

}
