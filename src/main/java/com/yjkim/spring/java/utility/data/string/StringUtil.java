package com.yjkim.spring.java.utility.data.string;

import org.apache.commons.lang3.StringUtils;

public class StringUtil
{
    public StringUtil ()
    {
        throw new IllegalStateException("StringUtil is utility class.");
    }

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
     * 쿼리 Like 패턴도 검색되도록
     *
     * @param str
     * @return
     */
    public static String replaceQueryLikePattern (String str)
    {
        return str.replace("%", "\\%").replace("_", "\\_");
    }

}
