package com.yjkim.spring.java.utility.os;

import org.apache.commons.lang3.StringUtils;

public class SystemUtil {
    public enum OS_TYPE {
        WINDOWS, MAC, LINUX, UNIX, SOLARIS, UNKNOWN;
    }
    
    public static String getOsNameUpper() {
        return System.getProperty("os.name").toUpperCase();
    }
    
    public static OS_TYPE getOsType() {
        return switch (SystemUtil.getOsNameUpper()) {
            case "WINDOWS" -> OS_TYPE.WINDOWS;
            case "MAC" -> OS_TYPE.MAC;
            case "LINUX" -> OS_TYPE.LINUX;
            case "UNIX" -> OS_TYPE.UNIX;
            case "SOLARIS" -> OS_TYPE.SOLARIS;
            default -> OS_TYPE.UNKNOWN;
        };
    }
    
    public static boolean isLocal() {
        String property = System.getProperty("spring.profiles.active");
        return StringUtils.equalsIgnoreCase(property , "local");
    }
}
