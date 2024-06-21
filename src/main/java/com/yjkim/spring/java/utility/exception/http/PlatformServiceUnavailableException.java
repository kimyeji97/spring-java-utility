package com.yjkim.spring.java.utility.exception.http;


import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;

import java.util.List;

public class PlatformServiceUnavailableException extends PlatformHttpException
{

	private static final long serialVersionUID = -2868711210331007198L;

	public PlatformServiceUnavailableException()
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE);
    }

    public PlatformServiceUnavailableException(String errorCode, MessageProvider mp, String... keyParams)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, errorCode, mp, keyParams);
    }


    public PlatformServiceUnavailableException(String errorCode, String message)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, errorCode, message);
    }


    public PlatformServiceUnavailableException(String message, List<String> details)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, message, details);
    }

    public PlatformServiceUnavailableException(String message)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, message);
    }

    public PlatformServiceUnavailableException(Throwable cause)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, cause);
    }

    public PlatformServiceUnavailableException(Throwable cause, List<String> details)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, cause, details);
    }

    public PlatformServiceUnavailableException(String message, Throwable cause)
    {
        super(PlatformHttpStatus.SERVICE_UNAVAILABLE, message, cause);
    }

}
