package com.yjkim.spring.java.utility.data.string;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class StringUtil {
    
    public static final String REGX_NEWLINE = "(\r\n|\r|\n|\n\r)";
    public static final String REGX_NOT_NUMBER = "[^0-9]";

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
    
    /**
     * 숫자만 추출
     * d
     * @param str
     * @return
     */
    public static String extractNumbers(String str)
    {
        if (StringUtils.isBlank(str))
        {
            return str;
        }
        
        return str.replaceAll(REGX_NOT_NUMBER, "");
    }
    
    /**
     * 자릿수(length) 만큼 랜덤한 숫자 + 문자 + 특수문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     *
     * @return String 숫자 + 문자 + 특수 문자 조합 문자열
     */
    public static String generateRandomMixAll(int length) {
        SecureRandom secureRandom = new SecureRandom();
        /*
         * 1. 특수문자의 범위 33 ~ 47, 58 ~ 64, 91 ~ 96
         * 2. 숫자의 범위 : 48 ~ 57
         * 3. 대문자의 범위: 65 ~ 90
         * 4. 소문자의 범위: 97 ~ 122
         */
        String charNSpecialChar =
                IntStream.concat(
                                IntStream.rangeClosed(48 , 57) ,
                                IntStream.concat(
                                        IntStream.rangeClosed(65 , 90) ,
                                        IntStream.rangeClosed(97 , 122)))
                        .mapToObj(i -> String.valueOf((char) i))
                        .collect(Collectors.joining());
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < length ; i++) {
            builder.append(charNSpecialChar.charAt(secureRandom.nextInt(charNSpecialChar.length())));
        }
        return builder.toString();
    }
    
    /**
     * 큰따옴표를 고려한 split
     *
     * @param value 대상 문자열
     * @param delimiter 구분자 (큰따옴표 안에 있는 경우 스킵)
     * @return
     */
    public List<String> splitRespectingQuotes(String value, String delimiter) {
        List<String> result = new ArrayList<>();
        String regex = String.format("\"([^\"]*)\"|([^%s]+)", Pattern.quote(delimiter));
        Matcher matcher = Pattern.compile(regex).matcher(value);
        while (matcher.find())
        {
            // 큰따옴표로 감싸진 값
            if (matcher.group(1) != null)
            {
                result.add(matcher.group(1));
            }
            // 일반 값
            else
            {
                result.add(matcher.group(2));
            }
        }
        return result;
    }
}
