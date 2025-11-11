package com.yjkim.spring.java.utility.data.list;

import com.sun.jdi.InternalException;
import org.apache.commons.lang3.ObjectUtils;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
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
    
    
    /**
     * 그룹핑된 기준으로 1개 랜덤으로 필터링
     *
     * @param list
     * @param randomGrouper
     * @return
     * @param <T>
     * @param <K>
     */
    public static <T, K> List<T> drawRandomMatchingFilter(List<T> list, Function<T, K> randomGrouper) {
        return ListUtil.drawRandomMatchingFilter(list, randomGrouper, 1);
    }
    
    /**
     * 그룹핑된 기준으로 n개 랜덤으로 필터링
     *
     * @param list
     * @param randomGrouper
     * @param drawCnt
     * @return
     * @param <T>
     * @param <K>
     */
    public static <T, K> List<T> drawRandomMatchingFilter(List<T> list, Function<T, K> randomGrouper, int drawCnt)
    {
        if (ObjectUtils.isEmpty(list) || drawCnt < 1)
        {
            return Collections.emptyList();
        }
        
        // 그룹핑: grouper로 키를 추출해 맵으로 그룹화
        Map<K, List<T>> grouped = list.stream().collect(Collectors.groupingBy(randomGrouper));
        
        // 각 그룹에서 랜덤으로 최대 count개 선택
        SecureRandom random = new SecureRandom();
        List<T> result = new ArrayList<>();
        
        for (List<T> group : grouped.values())
        {
            // 그룹 크기와 count 중 작은 값만큼 선택
            int itemsToSelect = Math.min(group.size(), drawCnt);
            if (itemsToSelect == 0) {
                continue;
            }
            
            // 그룹 내에서 셔플 후 상위 itemsToSelect개 선택
            List<T> shuffled = new ArrayList<>(group);
            Collections.shuffle(shuffled, random);
            result.addAll(shuffled.subList(0, itemsToSelect));
        }
        
        return result;
    }
    
    public static <T, K> List<T> sortPreKey(List<T> list, Function<T, K> keyGetter, Function<T, K> preKeyGetter) {
        if (list == null || list.size() <= 1) return list;
        
        Map<K, T> keyToItem = new HashMap<>();
        Map<K, List<T>> preKeyToItems = new HashMap<>();
        Set<K> allKeys = new HashSet<>();
        
        // 1. 매핑
        for (T item : list) {
            K key = keyGetter.apply(item);
            K preKey = preKeyGetter.apply(item);
            keyToItem.put(key, item);
            preKeyToItems.computeIfAbsent(preKey, k -> new ArrayList<>()).add(item);
            allKeys.add(key);
        }
        
        List<T> result = new ArrayList<>();
        LinkedList<K> queue = new LinkedList<>();
        
        // 2. 루트 노드 (preKey == null) 큐에 추가
        for (T item : list) {
            if (preKeyGetter.apply(item) == null) {
                queue.add(keyGetter.apply(item));
            }
        }
        
        // 3. 위상 정렬 (Kahn's Algorithm)
        while (!queue.isEmpty()) {
            K key = queue.poll();
            T item = keyToItem.get(key);
            result.add(item);
            
            List<T> children = preKeyToItems.getOrDefault(key, Collections.emptyList());
            for (T child : children) {
                K childKey = keyGetter.apply(child);
                queue.add(childKey);
            }
        }
        
        return result;
    }
}
