package com.yjkim.spring.java.utility.exception;

/**
 *
 * Macrogen 메소드를 직접 호출하면 안되는 상황, 또는 구현되지 않았을 때 사용한다.
 *
 * @author Gwanggeun Yoo
 */
public class PlatformNotSupportedMethodException extends PlatformRuntimeException
{
    /**
     * @see RuntimeException#RuntimeException()
     */
    public PlatformNotSupportedMethodException() {
        super();
    }

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public PlatformNotSupportedMethodException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public PlatformNotSupportedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see RuntimeException#RuntimeException(Throwable)
     */
    public PlatformNotSupportedMethodException(Throwable cause) {
        super(cause);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable, boolean, boolean)
     */
    protected PlatformNotSupportedMethodException(String message, Throwable cause,
                                                 boolean enableSuppression,
                                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
