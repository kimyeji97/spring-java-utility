package com.yjkim.spring.java.utility.object;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapUtil
{
    public MapUtil ()
    {
        throw new IllegalStateException("MapUtil is utility class.");
    }

    /**
     * Object를 Map으로 변환한다.
     *
     * @param obj 변환 대상 객체
     * @return Map<String, String>
     */
    public static Map<String, Object> converObjectToMap (Object obj)
    {
        return converObjectToMap(obj, ReflectionUtil.getPropertyDescriptors(obj.getClass()));
    }

    public static Map<String, Object> converObjectToMap (Object obj, List<PropertyDescriptor> propertyDescriptors)
    {
        if (obj == null)
        {
            return new HashMap<>();
        }
        Map<String, Object> resultMap = new HashMap<>();
        try
        {
            if (propertyDescriptors == null)
            {
                return resultMap;
            }

            for (PropertyDescriptor pd : propertyDescriptors)
            {
                Object value = ReflectionUtil.invokeGetMethod(obj, pd.getReadMethod());
                resultMap.put(pd.getName(), value);
            }
        } catch (IllegalArgumentException e)
        {
            log.trace(e.getMessage(), e);
        }
        return resultMap;
    }

}
