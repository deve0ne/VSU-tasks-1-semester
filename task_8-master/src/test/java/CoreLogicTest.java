import com.deveone.core.CoreLogic;
import com.deveone.core.FileRW;
import com.deveone.core.WinSituations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CoreLogicTest {

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void whichPlayerWinTest(String dataFilePath, WinSituations correctAnswer) {
        CoreLogic coreLogic = new CoreLogic();
        FileRW fileRW = new FileRW(coreLogic);

        try {
            fileRW.readGamefieldFromFile(dataFilePath);
        } catch (FileNotFoundException e) {
            fail("Входной файл не найден");
        }

        assertEquals(correctAnswer, coreLogic.whichPlayerWin());
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                arguments("/home/timofey/IdeaProjects/task_8/src/test/java/input/inputExample1.txt", WinSituations.NO_WINNERS),
                arguments("/home/timofey/IdeaProjects/task_8/src/test/java/input/inputExample2.txt", WinSituations.PLAYER_1_WIN),
                arguments("/home/timofey/IdeaProjects/task_8/src/test/java/input/inputExample3.txt", WinSituations.PLAYER_0_WIN),
                arguments("/home/timofey/IdeaProjects/task_8/src/test/java/input/inputExample4.txt", WinSituations.DRAW)
        );
    }
}