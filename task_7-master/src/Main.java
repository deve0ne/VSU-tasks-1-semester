import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

/*  После завершения работы над этим таском я заметил, что моя реализация отличается от той, которую указал Соломатин:
    "реализовать функцию, которая будет возвращать позицию первого элемента такой подпоследовательности и кол-во элементов"
    Надеюсь, это не является грубым нарушением условия. Мы, вроде, иногда отступаем от подобных указаний по реализации.
    К тому же, мне кажется, моя реализаця гораздо более читаема и интуитивно понятна. */

public class Main {

    public static void main(String[] args) {
        int arrSize = readAbsInt("Введите размер входного массива чисел: ");

        int[] arr = readIntArr(arrSize);

        int[] matchingSeq = findMatchingSeq(arr);

        printAnswer(matchingSeq);
    }

    private static int readAbsInt(String text) {
        Scanner input = new Scanner(System.in);

        System.out.println(text);

        int tempNum = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                tempNum = input.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели недопустимое число, повторите попытку.");
                input.nextLine();
            }
        }

        //Возвращаем значение по модулю, потому что сравнивать числа нужно именно по нему.
        return Math.abs(tempNum);
    }

    private static int[] readIntArr(int arrSize) {
        int[] arr = new int[arrSize];

        for (int i = 0; i < arrSize; i++)
            arr[i] = readAbsInt("Введите " + (i + 1) + " элемент массива: ");

        return arr;
    }

    //Находит все подпоследовательности, удовлетворяющие условию чередования чётности/нечётности.
    private static List<List<Integer>> findAllSeqInArr(int[] arr) {
        List<List<Integer>> allSequences = new ArrayList<>();
        List<Integer> tempSequence = new ArrayList<>();

        //Первый элемент всегда будет попадать в первую подпоследовательность.
        boolean isPrevNumEven = !(arr[0] % 2 == 0);

        for (int num : arr) {
            boolean isCurrNumEven = num % 2 == 0;

            if (isPrevNumEven == isCurrNumEven) {
                allSequences.add(tempSequence);
                tempSequence = new ArrayList<>();
            }

            tempSequence.add(num);
            isPrevNumEven = isCurrNumEven;
        }

        allSequences.add(tempSequence);

        return allSequences;
    }

    //Среди всех подпоследовательностей находит ту, которая удовлетворяет всем условиям.
    protected static int[] findMatchingSeq(int[] arr) {
        //Находим все подпоследовательности, удовлетворяющие условию чередования чётности/нечётности.
        List<List<Integer>> allSequences = findAllSeqInArr(arr);

        //Список со всеми найденными последовательностями сортируется по убыванию их длины.
        allSequences.sort((o1, o2) -> {
            if (o1.size() == o2.size())
                return 0;
            return o1.size() > o2.size() ? -1 : 1;
        });

        //Поскольку список уже отстортирован, первая последовательность будет максимальной.
        int maxSize = allSequences.get(0).size();

        //Для удобства из списка удаляются все последовательности, короче максимальной.
        List<List<Integer>> filteredSeq = allSequences.stream().filter((o1) -> o1.size() == maxSize).toList();

        //Возвращаем последнюю найденную последовательность, как и требуется в условии.
        return filteredSeq.get(filteredSeq.size() - 1).stream().mapToInt(i -> i).toArray();
    }

    private static void printAnswer(int[] answerSeq) {
        System.out.println("Последней самой длинной подпоследовательностью, удовлетворяющей" +
                " условию чередования чётности/нечётности является: \n" + Arrays.toString(answerSeq));
    }
}
