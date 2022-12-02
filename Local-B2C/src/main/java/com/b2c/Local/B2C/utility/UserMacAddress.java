package com.b2c.Local.B2C.utility;

import org.apache.commons.io.IOUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserMacAddress {

    public void pingRemoteIp() throws IOException {
        Process process = Runtime.getRuntime().exec("ping -c 1 192.168.86.53");
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = r.readLine()) != null) {
            System.out.println(line);
        }
        process.destroy();
    }

    @Cacheable(cacheNames = "arpByRemoteIp", key = "#remoteIp")
    public String arpByRemoteIp(String remoteIp) throws IOException {
        try {
            Process process = Runtime.getRuntime().exec("arp -a " + remoteIp);
            String arpDetail = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
            process.destroy();
            Pattern p = Pattern.compile("(..:..:..:..:..:..)");
            Matcher matcher = p.matcher(arpDetail);
            if (matcher.find()) {
                return matcher.group(0);
            } else {
                return "unIdentified";
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
