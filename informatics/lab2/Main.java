import java.util.Scanner;

public class Main {

    public static boolean stringValidation(String line) {
        if (line.length() != 7) {
            return false;
        }
        for (char c : line.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    public static int[] stringToBits(String line) {
        int[] bits = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            bits[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
        }
        return bits;
    }

    public static int[] calculatingSyndrome(int[] bits) {
        int s1 = (bits[0] + bits[2] + bits[4] + bits[6]) % 2;
        int s2 = (bits[1] + bits[2] + bits[5] + bits[6]) % 2;
        int s3 = (bits[3] + bits[4] + bits[5] + bits[6]) % 2;
        return new int[]{s1, s2, s3};
    }

    public static boolean hasError(int[] bitsTuple) {
        return bitsTuple[0] != 0 || bitsTuple[1] != 0 || bitsTuple[2] != 0;
    }

    public static int calculatingIndex(int[] syndrome) {
        StringBuilder reversedSyndrome = new StringBuilder();
        for (int i = syndrome.length - 1; i >= 0; i--) {
            reversedSyndrome.append(syndrome[i]);
        }
        return Integer.parseInt(reversedSyndrome.toString(), 2);
    }

    public static String calculatingWrongElement(int index) {
        String[] symbols = {"r1", "r2", "i1", "r3", "i2", "i3", "i4"};
        return symbols[index - 1];
    }

    public static String makeMessageFromList(int[] bits) {
        bits = new int[]{bits[2], bits[4], bits[5], bits[6]};
        StringBuilder message = new StringBuilder();
        for (int bit : bits) {
            message.append(bit);
        }
        return message.toString();
    }

    public static int getInverse(int element) {
        return (element == 0) ? 1 : 0;
    }

    public static int[] getCorrectList(int[] bits, String wrongElement) {
        int wrongElementIndex = Character.getNumericValue(wrongElement.charAt(wrongElement.length() - 1)) - 1 + 3;
        bits[wrongElementIndex] = getInverse(bits[wrongElementIndex]);
        return bits;
    }

    public static String returnCorrectMessage(int[] bits, String wrongElement) {
        if (wrongElement.charAt(0) == 'r') {
            return makeMessageFromList(bits);
        }
        return makeMessageFromList(getCorrectList(bits, wrongElement));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите набор из 7 цифр \"0\" и \"1\":");
        String inputString = scanner.nextLine();

        if (stringValidation(inputString)) {
            int[] bits = stringToBits(inputString);
            int[] syndrome = calculatingSyndrome(bits);

            if (hasError(syndrome)) {
                int index = calculatingIndex(syndrome);
                String wrongElement = calculatingWrongElement(index);
                System.out.printf("В сообщении была допущена ошибка! Ошибка была в символе %s. Правильное сообщение: %s%n",
                        wrongElement, returnCorrectMessage(bits, wrongElement));
            } else {
                System.out.printf("В сообщении не было допущено ошибок! Правильное сообщение: %s%n",
                        makeMessageFromList(bits));
            }

        } else {
            System.out.println("Некорректный ввод! Строка должна быть длины 7 и содержать только \"0\" и \"1\"!");
        }
    }
}
