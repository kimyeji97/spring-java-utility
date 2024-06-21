package com.yjkim.spring.java.utility.exception;

/**
 *
 * Macrogen framework에서 사용할 최상위 RuntimeException 입니다.
 *
 * @author Gwanggeun Yoo
 */
public class PlatformInitializingFailedException extends PlatformRuntimeException
{
    /**
     * @see RuntimeException#RuntimeException()
     */
    public PlatformInitializingFailedException() {
        super();
    }

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public PlatformInitializingFailedException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public PlatformInitializingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see RuntimeException#RuntimeException(Throwable)
     */
    public PlatformInitializingFailedException(Throwable cause) {
        super(cause);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable, boolean, boolean)
     */
    protected PlatformInitializingFailedException(String message, Throwable cause,
                                                 boolean enableSuppression,
                                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
