package com.yjkim.spring.java.utility.data.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 유효성 검증 유틸리티
 *
 * @author yjkim
 */
@Slf4j
public class StringValidUtil
{
    // URL 패
    private static final Pattern PATTERN_URL = Pattern.compile("^((http(s)?|ftp):\\/\\/)?+((([^\\/:]\\S+\\.)?([[^\\/.:]&&\\S]+)?+)(:(\\p{Digit}+))?+)?+(\\/([\\S&&[^\\?#]])*)?(\\??(&?[\\S&&[^&=#]]+=?[\\S&&[^&=#]]+)*)?(#.*)?$");
    // HEX color 패턴
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
    // 이메일 패턴
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    // 우편번호 패턴
    private static final Pattern PATTERN_ZIP = Pattern.compile("^(\\d\\d\\d)?([-])?(\\d\\d\\d)?$");
    // 핸드폰 번호
    private static final Pattern PATTERN_KOREAN_MOBILE =
            Pattern.compile("^(010|011|016|017|018|019)?([-])?(\\d\\d\\d(\\d)?)+([-])?(\\d\\d\\d\\d)+$");
    private static final String ZERO_TO_255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
    private static final Pattern IP_PATTERN = Pattern.compile(
            ZERO_TO_255 + "\\." + ZERO_TO_255 + "\\." + ZERO_TO_255 + "\\." + ZERO_TO_255);
    private static final Pattern BRN_PATTERN = Pattern.compile("([0-9]{3})(-?)([0-9]{2})(-?)([0-9]{5})");
    private static final Pattern RRN_PATTERN = Pattern.compile("([0-9]{6})(-?)([0-9]{7})");
    private static final Pattern POSTAL_PATTERN = Pattern.compile("[0-9]{5}");
    
    
    /**
     * mobile no 패턴 데이터인지 확인한다.
     *
     * @param mobile 원문 데이터
     * @return boolean mobile no 패턴 체크 여부
     */
    public static boolean isValidMobile (String mobile)
    {
        return PATTERN_KOREAN_MOBILE.matcher(mobile).matches();
    }

    /**
     * 유효한 이메일 문자열인지 반환
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail (String email)
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
    public static boolean isWhiteSpace (String pwd)
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
    public static boolean isDigit (String pwd)
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
    public static boolean isIncludeChar (String str)
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
    public static boolean isIncludeSPChar (String str)
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
    public static boolean isIncludeContinuous (String str)
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
    public static boolean isInclude3Same (String pwd)
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
    public static boolean checkMaxLength (String str, int maxLen)
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
    public static boolean checkMinLength (String str, int minLen)
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
    public static boolean isHexColorCode (String colorCode)
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
    public static Boolean checkIpPattern (String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return null;
        }

        return IP_PATTERN.matcher(str).matches();
    }

    /**
     * 우편번호 패턴 데이터인지 확인한다.
     *
     * @param zip 원문 데이터
     * @return boolean 우편번호 패턴 체크 여부
     */
    public static boolean isValidZip (String zip)
    {
        return PATTERN_ZIP.matcher(zip).matches();
    }

    /**
     * String 데이터가 URL 데이터인지 확인한다.
     *
     * @param url 확인 할 데이터
     * @return boolean URL 데이터 여부
     */
    public static boolean isValidURL (String url)
    {
        return PATTERN_URL.matcher(url).matches();
    }
    
    /**
     * 사업자 등록 번호 체크
     *
     * @param brn
     * @return
     */
    public static boolean isValidBRN(String brn) {
        if (brn == null || brn.isBlank()) {
            return false;
        }
        return BRN_PATTERN.matcher(brn).matches();
    }
    
    /**
     * 주민등록번호 체크
     *
     * @param rrn
     * @return
     */
    public static boolean isValidRRN(String rrn) {
        if (rrn == null || rrn.isBlank()) {
            return false;
        }
        return RRN_PATTERN.matcher(rrn).matches();
    }
    
    /**
     * 우편번호 체크
     *
     * @param post
     * @return
     */
    public static boolean isValidPost(String post) {
        if (post == null || post.isBlank()) {
            return false;
        }
        return POSTAL_PATTERN.matcher(post).matches();
    }
}
