package com.yjkim.spring.java.utility.exception;

/**
 * Macrogen framework에서 사용할 최상위 명시적 Exception 입니다.
 */
public class PlatformException extends Exception
{
    /**
     * @see Exception#Exception()
     */
    public PlatformException() {
        super();
    }

    /**
     * @see Exception#Exception(String)
     */
    public PlatformException(String message) {
        super(message);
    }

    /**
     * @see Exception#Exception(String, Throwable)
     */
    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see Exception#Exception(Throwable)
     */
    public PlatformException(Throwable cause) {
        super(cause);
    }

    /**
     * @see Exception#Exception(String, Throwable, boolean, boolean)
     */
    protected PlatformException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
