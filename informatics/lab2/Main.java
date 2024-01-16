import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public int[] calculatingSyndrome(int[] bits) {
        int s1 = (bits[0] + bits[2] + bits[4] + bits[6]) % 2;
        int s2 = (bits[1] + bits[2] + bits[5] + bits[6]) % 2;
        int s3 = (bits[3] + bits[4] + bits[5] + bits[6]) % 2;

        info("s1 = %d, s2 = %d, s3 = %d", s1, s2, s3);

        return new int[]{s1, s2, s3};
    }

    public boolean hasError(int[] bitsTuple) {
        info("checking error %s", Arrays.toString(bitsTuple));
        return bitsTuple[0] != 0 || bitsTuple[1] != 0 || bitsTuple[2] != 0;
    }

    public int calculatingIndex(int[] syndrome) {
        info("calculating index with syndrome ", Arrays.toString(syndrome));
        StringBuilder reversedSyndrome = new StringBuilder();
        for (int i = syndrome.length - 1; i >= 0; i--) {
            reversedSyndrome.append(syndrome[i]);
            info("-- appending %d reversed syndrome", syndrome[i]);
        }

        final int index = Integer.parseInt(reversedSyndrome.toString(), 2);
        info("reversed syndrome equals to %s = %d(10) => error on %d index", reversedSyndrome.toString(), index, index);
        return index;
    }

    private static final String[] symbols = {"r1", "r2", "i1", "r3", "i2", "i3", "i4"};

    public String calculatingWrongElement(int index) {
        info("calculating wrong element, index = %d", index);
        return symbols[index - 1];
    }

    public String makeMessageFromList(final int[] bits) {
        info("converting %s to message (pick indexes 2,4,5,6)", Arrays.toString(bits));
        return IntStream.of(2,4,5,6)
                .map(i -> bits[i]).boxed()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public int getInverse(int element) {
        return (element == 0) ? 1 : 0;
    }

    public int[] getCorrectList(int[] bits, String wrongElement) {
        int wrongElementIndex = Character.getNumericValue(wrongElement.charAt(wrongElement.length() - 1)) - 1 + 3;
        bits[wrongElementIndex] = getInverse(bits[wrongElementIndex]);
        info("wrong element at index %d, inverse it and get %s", wrongElementIndex, Arrays.toString(bits));
        return bits;
    }

    public String returnCorrectMessage(int[] bits, String wrongElement) {
        if (wrongElement.charAt(0) == 'r') {
            info("error in element %s, skip and return value", wrongElement);
            return makeMessageFromList(bits);
        }
        return makeMessageFromList(getCorrectList(bits, wrongElement));
    }

    private boolean debug;
    private static long counter = 0;
    private void info(String message, Object... data) {
        info(String.format(message, data));
    }

    private void info(String message) {
        if (!debug) return;
        System.out.printf("%d [INFO] %s%n", counter++, message);
    }

    private void run() {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {

            consoleWriter.append("Введите 7 цифр [0/1]: ").flush();
            final String enteredValue = consoleReader.readLine().trim();
            if (!enteredValue.matches("[01]{7}(d?)"))
                throw new InvalidInputException("набор из 7 цифр", "должны присутствовать только зачения '0' или '1' и длина сообщения должна быть 7 символов");

            if (enteredValue.endsWith("d")) debug = true;

            final int[] bits = Arrays.stream(enteredValue.split("")).limit(7).mapToInt(Integer::parseInt).toArray();
            final int[] syndrome = calculatingSyndrome(bits);

            if (hasError(syndrome)) {
                String wrongElement = calculatingWrongElement(calculatingIndex(syndrome));
                consoleWriter.append(String.format("Ошибка в символе %s. Правильное сообщение: %s",
                        wrongElement, returnCorrectMessage(bits, wrongElement))).append("\n").flush();
                return;
            }

            consoleWriter.append(String.format("Сообщение без ошибок. %s", enteredValue)).append("\n").flush();

        } catch (IOException | InvalidInputException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String valueName) {
            super(String.format("Неверный ввод значения для %s", valueName));
        }

        public InvalidInputException(String valueName, String addition) {
            super(String.format("Неверный ввод значения для %s [%s]", valueName, addition));
        }
    }
}
