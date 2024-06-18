package com.yjkim.spring.java.utility.os;

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
}
