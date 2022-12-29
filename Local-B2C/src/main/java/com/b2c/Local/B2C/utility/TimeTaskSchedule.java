package com.b2c.Local.B2C.utility;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@UtilityClass
public class TimeTaskSchedule {

    List<String> blockedIpsMyManyRequests= new ArrayList<>();

    public void blockIp(String remoteIp,long delay){
        blockedIpsMyManyRequests.add(remoteIp);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                blockedIpsMyManyRequests.remove(remoteIp);
            }
        };
        timer.schedule(timerTask,delay);
    }

    public boolean isBlocked(String remoteIp){
        if (blockedIpsMyManyRequests.contains(remoteIp))
            return true;
        else
            return false;
    }

}
