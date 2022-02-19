package com.deveone;

import com.deveone.core.CoreLogic;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.*;

class CoreLogicTest {

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void findSequenceWithMaxLength(Integer[] testArr, Integer[] correctAnswer) {
        List<Integer> testList = new ArrayList<>(List.of(testArr));
        List<Integer> programAnswer = CoreLogic.findMaxLengthConsecutiveSeq(testList);

        assertArrayEquals(correctAnswer, programAnswer.toArray());
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                arguments(new Integer[]{3, 4, 5, 6, 2, 3, 4, 1, 3, 3, 4}, new Integer[]{1, 2, 3, 4, 5, 6}),
                arguments(new Integer[]{5, 27, 34, 6, 45, 56, 78, 7, 4, 334}, new Integer[]{4, 5, 6, 7}),
                arguments(new Integer[]{32, 56, 34, 23, 33, 12, 45, 55, 66}, new Integer[]{32, 33, 34}),
                arguments(new Integer[]{-23, 45, -22, 34, -21, 565}, new Integer[]{-23, -22, -21}),
                arguments(new Integer[]{-100, -101, 23, -110, 23, 123, -99}, new Integer[]{-101, -100, -99})
        );
    }
}