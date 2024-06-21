package com.yjkim.spring.java.utility.exception.http;


import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;

import java.util.List;

public class PlatformAccessDeniedException extends PlatformHttpException
{

	private static final long serialVersionUID = 6991196264206421622L;
	private static final String title = "Access is denied";

	public PlatformAccessDeniedException()
    {
        super(PlatformHttpStatus.FORBIDDEN);
        setTitle(title);
    }

    public PlatformAccessDeniedException(String errorCode, MessageProvider mp, String... keyParams)
    {
        super(PlatformHttpStatus.FORBIDDEN, errorCode, mp, keyParams);
        setTitle(title);
    }

    public PlatformAccessDeniedException(String message, List<String> details)
    {
        super(PlatformHttpStatus.FORBIDDEN, message, details);
        setTitle(title);
    }

    public PlatformAccessDeniedException(String message)
    {
        super(PlatformHttpStatus.FORBIDDEN, message);
        setTitle(title);
    }

    public PlatformAccessDeniedException(Throwable cause)
    {
        super(PlatformHttpStatus.FORBIDDEN, cause);
        setTitle(title);
    }

    public PlatformAccessDeniedException(Throwable cause, List<String> details)
    {
        super(PlatformHttpStatus.FORBIDDEN, cause, details);
        setTitle(title);
    }

    public PlatformAccessDeniedException(String message, Throwable cause)
    {
        super(PlatformHttpStatus.FORBIDDEN, message, cause);
        setTitle(title);
    }

}
