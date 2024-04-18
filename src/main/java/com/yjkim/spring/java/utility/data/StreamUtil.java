package com.yjkim.spring.java.utility.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Stream 객체 관련 유틸리티
 */
public class StreamUtil
{

    public StreamUtil ()
    {
        throw new IllegalStateException("StreamUtil is utility class.");
    }

    /**
     * Stream 에서 특정 field 값 유니크 여부 체크
     * (Stream filter method 에서 사용 가능)
     *
     * @param keyExtractor field getter
     * @param <T>          stream items type
     * @return 유니크 여부
     */
    public static <T> Predicate<T> distinctByKey (Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
