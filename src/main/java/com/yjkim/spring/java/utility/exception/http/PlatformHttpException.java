package com.yjkim.spring.java.utility.exception.http;

import com.yjkim.spring.java.utility.data.list.ArraysUtil;
import com.yjkim.spring.java.utility.exception.PlatformRuntimeException;
import com.yjkim.spring.java.utility.http.MessageProvider;
import com.yjkim.spring.java.utility.http.PlatformHttpStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class PlatformHttpException extends PlatformRuntimeException
{

	private static final long serialVersionUID = -5581759208454112995L;

    private final PlatformHttpStatus statusCode;
    private String errorCode;
    private String title;
    private final String message;
    private final String[] details;

    public PlatformHttpException(PlatformHttpStatus statusCode)
    {
        this(statusCode, statusCode.getReasonPhrase());
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, String errorCode, String message, Throwable cause,
                                 List<String> details)
    {
    	super(message, cause);

        this.statusCode = statusCode;
        this.message = message;
        this.details = details != null ? ArraysUtil.toStringArray(details) : null;
        this.errorCode = errorCode;
    }
    
    public PlatformHttpException(PlatformHttpStatus statusCode, String errorCode, MessageProvider mp, String... keyParams)
    {
        this(statusCode, errorCode, mp.getMessage(errorCode, keyParams), null, null);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, String message, List<String> details)
    {
        this(statusCode, null, message, null, details);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, String errorCode, String message, List<String> details)
    {
        this(statusCode, errorCode, message, null, details);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, String message)
    {
        this(statusCode, (String) null, message, null, null);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, String errorCode, String message)
    {
        this(statusCode, errorCode, message, null, null);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, Throwable cause)
    {
        this(statusCode, null, null, cause, null);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, Throwable cause, List<String> details)
    {
        this(statusCode, null, statusCode.getReasonPhrase(), cause, details);
    }

    public PlatformHttpException(PlatformHttpStatus statusCode, String message, Throwable cause)
    {
        this(statusCode, null, message, cause, null);
    }

    public String[] getDetails()
    {
        if (this.details == null)
        {
            return null;
        }
        return Arrays.copyOf(details, details.length);
    }
}
