package com.yjkim.spring.java.utility.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * String 유효성 검증 유틸리티
 *
 * @author yjkim
 */
@Slf4j
public class StringValidUtil
{

    // HEX color 패턴
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
    // 이메일 패턴
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");

    private static final String ZERO_TO_255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
    private static final Pattern IP_PATTERN = Pattern.compile(
        ZERO_TO_255 + "\\." + ZERO_TO_255 + "\\." + ZERO_TO_255 + "\\." + ZERO_TO_255);

    /**
     * 유효한 이메일 문자열인지 반환
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email)
    {
        boolean result = true;
        if (email == null || isWhiteSpace(email))
        {
            return result;
        }

        Matcher m = EMAIL_PATTERN.matcher(email);
        if (m.matches())
        {
            result = false;
        }
        return result;
    }

    /**
     * pwd에 공백이 있는지 판별한다.
     *
     * @param pwd 비밀번호
     * @return 판별여부
     */
    public static boolean isWhiteSpace(String pwd)
    {
        String regex = "\\s";
        return Pattern.compile(regex).matcher(pwd).find();
    }

    /**
     * pwd에 숫자가 있는지 판별한다.
     *
     * @param pwd 비밀번호
     * @return 판별여부
     */
    public static boolean isDigit(String pwd)
    {
        String regex = "[0-9]";
        return Pattern.compile(regex).matcher(pwd).find();
    }

    /**
     * 문자가 있는지 판별한다.
     *
     * @param str
     * @return 판별여부
     */
    public static boolean isIncludeChar(String str)
    {
        String regex = "[a-zA-Z]";
        return Pattern.compile(regex).matcher(str).find();
    }

    /**
     * 특수문자가 있는지 판별한다.
     *
     * @param str
     * @return 판별여부
     */
    public static boolean isIncludeSPChar(String str)
    {
        String regex = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
        return !Pattern.compile(regex).matcher(str).find();
    }

    /**
     * 연속된 숫자 / 문자가 있는지 판별한다.
     *
     * @param str
     * @return 판별여부
     */
    public static boolean isIncludeContinuous(String str)
    {
        int len = str.length();
        boolean check = false;
        boolean isPositive = false;
        for (int i = 0; i < len - 1; i++)
        {
            char now = str.charAt(i);
            char next = str.charAt(i + 1);
            int value = now - next;
            boolean tempB = value > 0;
            if (Math.abs(value) == 1)
            {
                if (check)
                {
                    if (isPositive == tempB)
                    {
                        return true;
                    }
                }
                isPositive = tempB;
                check = true;
            } else
            {
                check = false;
            }
        }
        return false;
    }

    /**
     * 같은 문자 / 숫자가 3개이상 반복되는지 판별한다.
     *
     * @param pwd 비밀번호
     * @return 판별여부
     */
    public static boolean isInclude3Same(String pwd)
    {
        String regex = "(\\w)\\1\\1";
        return Pattern.compile(regex).matcher(pwd).find();
    }

    /**
     * 문자 최대 길이 체크
     *
     * @param str
     * @param maxLen
     * @return
     */
    public static boolean checkMaxLength(String str, int maxLen)
    {
        if (StringUtils.isBlank(str))
        {
            return true;
        }
        return str.length() <= maxLen;
    }

    /**
     * 문자 최소 길이 체크
     *
     * @param str
     * @param minLen
     * @return
     */
    public static boolean checkMinLength(String str, int minLen)
    {
        if (StringUtils.isBlank(str) && minLen < 1)
        {
            return true;
        }
        return str.length() >= minLen;
    }

    /**
     * HEX 컬러 체크
     *
     * @param colorCode
     * @return
     */
    public static boolean isHexColorCode(String colorCode)
    {
        if (colorCode == null || colorCode.isBlank())
        {
            return true;
        }

        return HEX_COLOR_PATTERN.matcher(colorCode).matches();
    }

    /**
     * IP 패턴이 맞는지 체크한다.
     */
    public static Boolean checkIpPattern(String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return null;
        }

        return IP_PATTERN.matcher(str).matches();
    }
}
