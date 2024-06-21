package com.yjkim.spring.java.utility.exception.http;


import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;

import java.util.List;

public class PlatfornTokenVerificationException extends PlatformHttpException
{

	private static final long serialVersionUID = 1304197950075750813L;
	private final String title = "Unauthenticated User";

	public PlatfornTokenVerificationException()
    {
        super(PlatformHttpStatus.UNAUTHORIZED);
        setTitle(title);
    }

    public PlatfornTokenVerificationException(String errorCode, MessageProvider mp, String... keyParams)
    {
        super(PlatformHttpStatus.UNAUTHORIZED, errorCode, mp, keyParams);
        setTitle(title);
    }

    public PlatfornTokenVerificationException(String message, List<String> details)
    {
        super(PlatformHttpStatus.UNAUTHORIZED, message, details);
        setTitle(title);
    }

    public PlatfornTokenVerificationException(String message)
    {
        super(PlatformHttpStatus.UNAUTHORIZED, message);
        setTitle(title);
    }

    public PlatfornTokenVerificationException(Throwable cause)
    {
        super(PlatformHttpStatus.UNAUTHORIZED, cause);
        setTitle(title);
    }

    public PlatfornTokenVerificationException(Throwable cause, List<String> details)
    {
        super(PlatformHttpStatus.UNAUTHORIZED, cause, details);
        setTitle(title);
    }

    public PlatfornTokenVerificationException(String message, Throwable cause)
    {
        super(PlatformHttpStatus.UNAUTHORIZED, message, cause);
        setTitle(title);
    }
}
