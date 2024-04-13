package com.yjkim.spring.java.utility.object;

import com.sun.jdi.InternalException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * List 타입관련 유틸리티
 */
public class ListUtil
{

    public ListUtil ()
    {
        throw new IllegalStateException("ListUtil is utility class.");
    }

    @FunctionalInterface
    public interface BaseProcessor<T>
    {
        void process (List<T> list);
    }

    /**
     * list를 size수로 분할 하여 반환하는 함수
     *
     * @param <T>
     * @param list
     * @param size
     * @return
     */
    public static <T> Collection<List<T>> partitionBasedOnSize (List<T> list, int size)
    {
        final AtomicInteger counter = new AtomicInteger(0);
        return list.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
    }

    /**
     * list를 size로 분할해 BaseProcessor 실행
     *
     * @param <T>
     * @param list
     * @param size
     * @param baseProcessor
     * @return
     */
    public static <T> boolean partitionBasedOnSize (List<T> list, int size, BaseProcessor<T> baseProcessor)
    {
        if (ObjectUtils.isEmpty(list))
        {
            return true;
        }

        try
        {
            Collection<List<T>> listCollection = ListUtil.partitionBasedOnSize(list, (size < 1 ? 100 : size));
            for (List<T> listPartition : listCollection)
            {
                baseProcessor.process(listPartition);
            }
        } catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * 병합한 리스트 반환
     *
     * @param toList
     * @param fromList
     * @param <T>
     * @return
     */
    public static <T> List<T> makeMergedList (List<T> toList, List<T> fromList)
    {
        List<T> result = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(toList))
        {
            result = toList;
        }

        if (ObjectUtils.isNotEmpty(fromList))
        {
            result.addAll(fromList);
        }

        return result;
    }

    /**
     * formList를 toList에 머지.
     *
     * @param toList
     * @param formList
     * @param <T>
     */
    public static <T> void mergeAddAll (List<T> toList, List<T> formList)
    {
        if (toList == null || ObjectUtils.isEmpty(formList))
        {
            return;
        }

        toList.addAll(formList);
    }

    /**
     * List에서 해당 index에 존재하는 값을 Object로 리턴
     *
     * @param list  리스트
     * @param index 반환 인덱스
     * @param <T>   리스트 요소 타입
     * @return 반환 인덱스의 값
     */
    public static <T> Object findListValueByIndex (List<T> list, Integer index)
    {
        if (ObjectUtils.isEmpty(list))
        {
            throw new InternalException("The find target list is empty.");
        }

        if (ObjectUtils.isEmpty(index))
        {
            throw new InternalException("The index is empty.");
        }

        if (list.size() <= index)
        {
            throw new InternalException("index more then list size.");
        }
        return list.get(index);
    }
}
