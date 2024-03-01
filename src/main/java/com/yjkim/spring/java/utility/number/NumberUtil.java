package com.yjkim.spring.java.utility.number;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

/**
 * 숫자 관련 공통 유틸리티
 */
@Slf4j
public class NumberUtil
{
    public static Random RANDOM;

    static
    {
        try
        {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage());
            RANDOM = new Random();
        }
    }

    /**
     * 해당 숫자가 특정 범위 안에 있는지 확인
     *
     * @param num
     * @param s
     * @param e
     * @return
     */
    public static boolean isIncludeInRange(Integer num, Integer s, Integer e)
    {
        if (num == null || (s == null && e == null))
        {
            return false;
        }

        if (s == null)
        {
            return num <= e;
        }

        if (e == null)
        {
            return s <= num;
        }

        return s <= num && num <= e;
    }

    /**
     * ##################################################################################
     * Double
     * ##################################################################################
     */

    /**
     * null -> 0
     *
     * @param val
     * @return
     */
    public static Double convertNullToZero(Double val)
    {
        if (val == null)
        {
            return 0d;
        }
        return val;
    }

    /**
     * null 또는 음수 -> 0
     *
     * @param val
     * @return
     */
    public static Double convertNullOrMinusToZero(Double val)
    {
        Double result = convertNullToZero(val);
        return result < 1 ? 0 : result;
    }

    /**
     * n자리에서 반올림
     *
     * @param d
     * @param n
     * @return
     */
    public static Double round(Double d, int n)
    {
        if (d == null || n < 0)
        {
            return d;
        }
        double m = Math.pow(10.0, n);
        return Math.round(d * m) / m;
    }

    /**
     * ##################################################################################
     * Integer
     * ##################################################################################
     */

    /**
     * null -> 0
     *
     * @param val
     * @return
     */
    public static Integer convertNullToZero(Integer val)
    {
        if (val == null)
        {
            return 0;
        }
        return val;
    }

    /**
     * null 또는 음수 -> 0
     *
     * @param val
     * @return
     */
    public static Integer convertNullOrMinusToZero(Integer val)
    {
        Integer result = convertNullToZero(val);
        return result < 1 ? 0 : result;
    }

    /**
     * ##################################################################################
     * BigDecimal
     * ##################################################################################
     */

    /**
     * null -> 0
     *
     * @param val
     * @return
     */
    public static BigDecimal convertNullToZero(BigDecimal val)
    {
        if (val == null)
        {
            return BigDecimal.ZERO;
        }
        return val;
    }

    /**
     * null or 음수 -> 0
     *
     * @param val
     * @return
     */
    public static BigDecimal convertNullOrMinusToZero(BigDecimal val)
    {
        BigDecimal result = convertNullToZero(val);
        return (result.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO : result;
    }
}
