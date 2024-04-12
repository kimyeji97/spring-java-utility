package com.yjkim.spring.java.utility.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * {@link ApplicationContext}의 Bean관련 유틸리티
 */
@Component
public class BeanUtil implements ApplicationContextAware
{
    private static ApplicationContext context;

    @Override
    public void setApplicationContext (ApplicationContext applicationContext)
    {
        context = applicationContext;
    }

    /**
     * 특정 타입의 Bean을 조회
     *
     * @param beanClass class
     * @param <T>       타입
     * @return 빈
     */
    public static <T> T getBean (Class<T> beanClass)
    {
        return context.getBean(beanClass);
    }

    /**
     * 특정 이름의 bean 조회
     *
     * @param beanName 빈 이름
     * @return 빈
     */
    public static Object getBean (String beanName)
    {
        return context.getBean(beanName);
    }
}