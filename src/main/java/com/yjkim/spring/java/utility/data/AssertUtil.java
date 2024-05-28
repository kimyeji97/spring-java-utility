package com.yjkim.spring.java.utility.data;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class AssertUtil {

    public AssertUtil() {
        throw new IllegalStateException("AssertUtil is utility class.");
    }

    public static void assertIsNotNull(Object obj, String fieldName) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new NullPointerException(StringUtils.join("'", fieldName, "' is null."));
        }
    }

    public static void assertIsNotBlank(Object object, String fieldName) {
        AssertUtil.assertIsNotNull(object, fieldName);
        if (StringUtils.isBlank(String.valueOf(object))) {
            throw new NullPointerException(StringUtils.join("'", fieldName, "' is blank."));
        }
    }

    public static void assertIsDateFormat(String string, String format, String fieldName) {
        if (StringUtils.isBlank(string)) {
            return;
        }

        AssertUtil.assertIsNotBlank(format, "format");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(string);
        } catch (ParseException ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(StringUtils.join("'", fieldName, "' can not parse to '", format, "' pattern."));
        }
    }
}
