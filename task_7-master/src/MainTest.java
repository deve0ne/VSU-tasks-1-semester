import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MainTest {

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void testFindingMatchingSeq(int[] inputArr, int[] expectedAnswer) {
        assertArrayEquals(expectedAnswer, Main.findMatchingSeq(inputArr));
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                arguments(new int[]{1, 2, -2, 1, 10, 3, 105, 4, -5, 4, 10, 1, 1, 3, 4, 7, 12, 10}, new int[]{3, 4, 7, 12}),
                arguments(new int[]{1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10}, new int[]{1, 2, 3, 4, 5, 6, 7}),
                arguments(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}, new int[]{1}),
                arguments(new int[]{2, 6, 7, 4, 6, 9, 12, 45, 32, 5, 1}, new int[]{6, 9, 12, 45, 32, 5}),
                arguments(new int[]{1, 3, 1, 3, 231213321, 3, 4}, new int[]{3, 4}),
                arguments(new int[]{-213, 453, 12312, 444, 56, 67, 123, 5454, 777}, new int[]{123, 5454, 777}),
                arguments(new int[]{667, 776, 222228, 822222, 1477}, new int[]{822222, 1477}),
                arguments(new int[]{551, 552, 553, 554, 555, 556, 557, 558}, new int[]{551, 552, 553, 554, 555, 556, 557, 558}),
                arguments(new int[]{-1, 2, -3, 4, -4, 5, -5, 6, 7, 8}, new int[]{-5, 6, 7, 8}),
                arguments(new int[]{-13, 14, 28, 999999999, 0, -999999999}, new int[]{28, 999999999, 0, -999999999})
        );
    }
}