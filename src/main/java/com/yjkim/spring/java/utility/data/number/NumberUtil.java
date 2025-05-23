package com.yjkim.spring.java.utility.data.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 숫자 관련 공통 유틸리티
 */
@Slf4j
public class NumberUtil {
    public static Random RANDOM;

    static {
        try {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            RANDOM = new Random();
        }
    }

    public static <T extends Number> boolean isOrMore(T value, T num) {
        if (value == null) {
            return false;
        }

        try {
            return value.doubleValue() >= num.doubleValue();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * ##################################################################################
     * Integer
     * ##################################################################################
     */

    /**
     * 해당 숫자가 특정 범위 안에 있는지 확인
     *
     * @param num
     * @param s
     * @param e
     * @return
     */
    public static boolean isIncludeInRange(Integer num, Integer s, Integer e) {
        if (num == null || (s == null && e == null)) {
            return false;
        }

        if (s == null) {
            return num <= e;
        }

        if (e == null) {
            return s <= num;
        }

        return s <= num && num <= e;
    }

    /**
     * 숫자에 천단위 콤마 세팅후 반환
     *
     * @param num 숫자
     * @return 천단위 콤마 찍힌 문자
     */
    public static String numberFormatComma(Number num) {
        if (num == null) {
            return StringUtils.EMPTY;
        }
        return NumberFormat.getInstance().format(num);
    }
    
    /**
     * null -> 0
     *
     * @param val
     * @return
     */
    public static Integer convertNullToZero(Integer val) {
        if (val == null) {
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
    public static Integer convertNullOrMinusToZero(Integer val) {
        Integer result = convertNullToZero(val);
        return result < 1 ? 0 : result;
    }
    
    /**
     * 해당 범위 내에서 랜덤 값 추출
     *
     * @param start
     * @param end
     * @return
     */
    public static int randomInRange(int start, int end) {
        if (end <= start) {
            return -1;
        }
        return RANDOM.nextInt(start, end + 1);
    }
    
    /**
     * ##################################################################################
     * Long
     * ##################################################################################
     */
    
    /**
     * 해당 범위 내에서 랜덤 값 추출
     *
     * @param start
     * @param end
     * @return
     */
    public static long randomInRange(long start, long end) {
        if (end <= start) {
            return -1L;
        }
        return RANDOM.nextLong(start, end + 1);
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
    public static Double convertNullToZero(Double val) {
        if (val == null) {
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
    public static Double convertNullOrMinusToZero(Double val) {
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
    public static Double round(Double d, int n) {
        if (d == null || n < 0) {
            return d;
        }
        double m = Math.pow(10.0, n);
        return Math.round(d * m) / m;
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
    public static BigDecimal convertNullToZero(BigDecimal val) {
        if (val == null) {
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
    public static BigDecimal convertNullOrMinusToZero(BigDecimal val) {
        BigDecimal result = convertNullToZero(val);
        return (result.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO : result;
    }
    
    /**
     * 0 이하 여부
     *
     * @param val
     * @return
     */
    public static boolean isLessThanOrEqualToZero(BigDecimal val) {
        return val == null || val.compareTo(BigDecimal.ZERO) <= 0;
    }
    
    /**
     * 퍼센트 계산
     *
     * @param val
     * @param percent
     * @return
     */
    public static BigDecimal calculatePercent(BigDecimal val, int percent) {
        if(isLessThanOrEqualToZero(val)) {
            return val;
        }
        return NumberUtil.calculatePercent(val, new BigDecimal(percent));
    }
    
    
    public static BigDecimal calculatePercent(BigDecimal val, BigDecimal percent) {
        if(isLessThanOrEqualToZero(val)) {
            return val;
        }
        return val.multiply(percent.multiply(new BigDecimal(0.01)));
    }
    
    /**
     * 특저 단위로 반올림 처리 후 scale
     *
     * @param val          값
     * @param unit         단위
     * @param roundingMode 반올림 모드
     * @return scale된 값
     */
    public static BigDecimal scale(BigDecimal val, int unit, RoundingMode roundingMode) {
        return val == null || val.equals(BigDecimal.ZERO) ?
                val :
                val.divide(new BigDecimal(unit)).setScale(0, roundingMode);
    }

    public static BigDecimal scale(BigDecimal val, int unit, int scale,  RoundingMode roundingMode) {
        return val == null || val.equals(BigDecimal.ZERO) ?
                val :
                val.divide(new BigDecimal(unit)).setScale(scale, roundingMode);
    }

    /**
     * 천 만 단위 절삭
     *
     * @param val 절삭 대상 숫자
     * @return 천 만 단위로 절삭된 숫자
     */
    public static BigDecimal scaleDownToMillion(BigDecimal val) {
        return NumberUtil.scale(val, 1000000, RoundingMode.DOWN);
    }
    public static BigDecimal scaleFloorToMillion(BigDecimal val) {
        return NumberUtil.scale(val, 1000000, RoundingMode.FLOOR);
    }
    public static BigDecimal scaleFloorToMillion(BigDecimal val, int scale) {
        return NumberUtil.scale(val, 1000000, scale, RoundingMode.FLOOR);
    }

    /**
     * 숫자들 천 만 단위 절삭
     *
     * @param vals 절삭 대상 숫자들
     * @return 천 만 단위로 전삭된 숫자들
     */
    public static BigDecimal[] scaleDownToMillion(BigDecimal[] vals) {
        BigDecimal[] result = new BigDecimal[vals.length];
        for (int i = 0; i < vals.length; i++) {
            result[i] = NumberUtil.scaleDownToMillion(vals[i]);
        }
        return result;
    }

    /**
     * TODO ChoiceFormat 활용한 메서드 하나 만들기
     */
}
