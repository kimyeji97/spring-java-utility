package com.yjkim.spring.java.utility.object;

import java.util.Collection;
import java.util.Collections;

/**
 * {@link Collection} 객체 유틸리티
 */
public class CollectionUtil
{
    public CollectionUtil ()
    {
        throw new IllegalStateException("CollectionUtil is utility class.");
    }

    /**
     * 컬렉션에 객체 배열의 모든 요소를 더한다.
     *
     * @param collection Collection
     * @param array      Object[]
     */
    public static <T> void addAll (Collection<T> collection, T[] array)
    {
        Collections.addAll(collection, array);
    }
}
