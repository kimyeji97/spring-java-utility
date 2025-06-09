package com.yjkim.spring.java.utility.data.number;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilTest
{
    @Test
    void weightedRandom ()
    {
        List<NumberUtil.RandomWeight> points = Arrays.asList(
                new NumberUtil.RandomWeight(1, 10.0),
                new NumberUtil.RandomWeight(3, 30.0),
                new NumberUtil.RandomWeight(5, 60.0)
        );

        // 100번 시뮬레이션
        Map<Integer, Integer> resultCount = new HashMap<>();
        int trials = 1000;

        for (int i = 0; i < trials; i++)
        {
            int point = NumberUtil.weightedRandom(points);
            resultCount.put(point, resultCount.getOrDefault(point, 0) + 1);
        }

        // 결과 출력
        System.out.println("After " + trials + " trials:");
        resultCount.forEach((point, count) -> {
            double percentage = (count * 100.0) / trials;
            System.out.printf("Point %d: %.2f%% (Expected: %.2f%%)%n",
                    point, percentage, points.stream()
                            .filter(p -> p.getValue() == point)
                            .mapToDouble(NumberUtil.RandomWeight::getWeight)
                            .findFirst().orElse(0.0));
        });

        // 결과 검증 (대략적인 확률 분포 확인)
        double point1Percent = resultCount.getOrDefault(1, 0) * 100.0 / trials;
        double point3Percent = resultCount.getOrDefault(3, 0) * 100.0 / trials;
        double point5Percent = resultCount.getOrDefault(5, 0) * 100.0 / trials;

        // 허용 오차 ±5%로 검증
        assertTrue(Math.abs(point1Percent - 10.0) <= 5.0, "Point 1 percentage out of range");
        assertTrue(Math.abs(point3Percent - 30.0) <= 5.0, "Point 3 percentage out of range");
        assertTrue(Math.abs(point5Percent - 60.0) <= 5.0, "Point 5 percentage out of range");
    }

}