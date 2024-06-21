package com.yjkim.spring.java.utility.exception.http;


import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;

import java.util.List;

public class PlatformNotFoundException extends PlatformHttpException
{

	private static final long serialVersionUID = 8838556050207962291L;

	public PlatformNotFoundException()
    {
        super(PlatformHttpStatus.NOT_FOUND);
    }

    public PlatformNotFoundException(String errorCode, MessageProvider mp, String... keyParams)
    {
        super(PlatformHttpStatus.NOT_FOUND, errorCode, mp, keyParams);
    }

    public PlatformNotFoundException(String message, List<String> details)
    {
        super(PlatformHttpStatus.NOT_FOUND, message, details);
    }

    public PlatformNotFoundException(String message)
    {
        super(PlatformHttpStatus.NOT_FOUND, message);
    }

    public PlatformNotFoundException(Throwable cause)
    {
        super(PlatformHttpStatus.NOT_FOUND, cause);
    }

    public PlatformNotFoundException(Throwable cause, List<String> details)
    {
        super(PlatformHttpStatus.NOT_FOUND, cause, details);
    }

    public PlatformNotFoundException(String message, Throwable cause)
    {
        super(PlatformHttpStatus.NOT_FOUND, message, cause);
    }

}
