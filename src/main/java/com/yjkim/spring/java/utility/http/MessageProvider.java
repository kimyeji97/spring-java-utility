package com.yjkim.spring.java.utility.http;

public interface MessageProvider
{
    public String getMessage(String messageCode, String... parameters);

    public String getErrorMessage(String messageCode, String... parameters);
}
