package com.yjkim.spring.java.utility.data.string;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class StringUtil {
    
    public static final String REGX_NEWLINE = "(\r\n|\r|\n|\n\r)";

    /**
     * str이 empty인경우 dfStr 반환.
     *
     * @param str
     * @param dfStr
     * @return
     */
    public static String getDefaultIsEmpty (String str, String dfStr)
    {
        if (StringUtils.isNotBlank(str))
        {
            return dfStr == null ? StringUtils.EMPTY : dfStr;
        }
        return str;
    }

    /**
     * value 값에 '0' 문자열을 길이만큼 채운다.
     *
     * @param value     값
     * @param zeroCount 채울 0의 길이
     * @return String 0 이 append 된 문자열
     */
    public static String fillToZeroPadding (int value, int zeroCount)
    {
        return String.format("%0" + zeroCount + "d", value);
    }

    /**
     * 쿼리 Like 패턴도 검색되도록
     *
     * @param str
     * @return
     */
    public static String replaceQueryLikePattern (String str)
    {
        return str.replace("%", "\\%").replace("_", "\\_");
    }
    
    /**
     * 개행문자 치환
     *
     * @param value
     * @param replacement
     * @return
     */
    public static String replaceNewLine(String value , String replacement)
    {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        return value.replaceAll(REGX_NEWLINE, replacement);
    }

    /**
     * 문자열 뒷부분 생략
     *
     * @param str
     * @param maxLength
     * @return
     */
    public static String getEllipsis (String str, int maxLength)
    {
        if (str == null || StringUtils.length(str) <= maxLength)
        {
            return str;
        }

        return StringUtils.join(StringUtils.substring(str, 0, maxLength - 3), "...");
    }
}
