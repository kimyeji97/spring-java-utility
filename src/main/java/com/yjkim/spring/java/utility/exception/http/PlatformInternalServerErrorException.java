package com.yjkim.spring.java.utility.exception.http;

import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;

import java.util.List;

public class PlatformInternalServerErrorException extends PlatformHttpException
{

	private static final long serialVersionUID = 6991196264206421622L;
	private static final String title = "Internal Server Error";

	public PlatformInternalServerErrorException()
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR);
        setTitle(title);
    }

    public PlatformInternalServerErrorException(String errorCode, MessageProvider mp, String... keyParams)
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR, errorCode, mp, keyParams);
        setTitle(title);
    }

    public PlatformInternalServerErrorException(String message, List<String> details)
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR, message, details);
        setTitle(title);
    }

    public PlatformInternalServerErrorException(String message)
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR, message);
        setTitle(title);
    }

    public PlatformInternalServerErrorException(Throwable cause)
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR, cause);
        setTitle(title);
    }

    public PlatformInternalServerErrorException(Throwable cause, List<String> details)
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR, cause, details);
        setTitle(title);
    }

    public PlatformInternalServerErrorException(String message, Throwable cause)
    {
        super(PlatformHttpStatus.INTERNAL_SERVER_ERROR, message, cause);
        setTitle(title);
    }

}
