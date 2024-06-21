package com.yjkim.spring.java.utility.exception;

/**
 *
 * Macrogen framework에서 사용할 최상위 RuntimeException 입니다.
 *
 */
public class PlatformRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = -3176982830265998007L;

	/**
     * @see RuntimeException#RuntimeException()
     */
    public PlatformRuntimeException() {
        super();
    }

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public PlatformRuntimeException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable) 
     */
    public PlatformRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see RuntimeException#RuntimeException(Throwable) 
     */
    public PlatformRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable, boolean, boolean)
     */
    protected PlatformRuntimeException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
