package com.yjkim.spring.java.utility.exception.http;


import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;

import java.util.List;

/**
 * @author Gwanggeun Yoo
 */
public class PlatformBadRequestException extends PlatformHttpException
{
    private static final long serialVersionUID = -2235869320525832134L;

    public PlatformBadRequestException()
    {
        super(PlatformHttpStatus.BAD_REQUEST);
    }

    public PlatformBadRequestException(String errorCode, MessageProvider mp, String... keyParams)
    {
        super(PlatformHttpStatus.BAD_REQUEST, errorCode, mp, keyParams);
    }

    public PlatformBadRequestException(String message, List<String> details)
    {
        super(PlatformHttpStatus.BAD_REQUEST, message, details);
    }

    public PlatformBadRequestException(String message)
    {
        super(PlatformHttpStatus.BAD_REQUEST, message);
    }

    public PlatformBadRequestException(Throwable cause)
    {
        super(PlatformHttpStatus.BAD_REQUEST, cause);
    }

    public PlatformBadRequestException(Throwable cause, List<String> details)
    {
        super(PlatformHttpStatus.BAD_REQUEST, cause, details);
    }

    public PlatformBadRequestException(String message, Throwable cause)
    {
        super(PlatformHttpStatus.BAD_REQUEST, message, cause);
    }
}
