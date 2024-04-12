package com.yjkim.spring.java.utility.spring;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * Spring 주석 관련 유틸리티
 */
@Slf4j
public class AnnotationUtil
{

    /**
     * Annotation을 찾는다.
     *
     * @param className      클래스명
     * @param annotationType annotationType
     */
    public static <A extends Annotation> A findAnnotation (String className, Class<A> annotationType) throws ClassNotFoundException
    {
        try
        {
            Class<?> clazz = Class.forName(className);
            return clazz.getAnnotation(annotationType);
        } catch (ClassNotFoundException e)
        {
            log.error("Cannot find class -> {} ", className);
            throw e;
        }
    }

    /**
     * Annotation을 찾는다.
     *
     * @param className      클래스명
     * @param annotationType annotationType
     */
    public static <A extends Annotation> A[] findAnnotations (String className, Class<A> annotationType) throws ClassNotFoundException
    {
        try
        {
            Class<?> clazz = Class.forName(className);
            return clazz.getAnnotationsByType(annotationType);
        } catch (ClassNotFoundException e)
        {
            log.error("Cannot find class -> {} ", className);
            throw e;
        }
    }
}
