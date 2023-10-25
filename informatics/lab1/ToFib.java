package inf.lab1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToFib {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.append("Перевод из десятичной системы счисления в фибоначчи").append("\n\n").flush();
        writer.append("введите число в десятичной системе счисления: ").flush();

        String input = reader.readLine();
        long number;
        if (!input.matches("\\d+") || (number = Long.parseLong(input)) <= 0) {
            System.err.println("введите число > 0!");
            return;
        }

        List<Long> fib = getFib(number);
        int[] used = new int[fib.size()];

        writer.append("ряд Фибоначчи: ").append(fib.toString()).append("\n").flush();

        while (number > 0) {
            if (fib.contains(number) && used[fib.indexOf(number)] != 1) {
                used[fib.indexOf(number)] = 1;
                break;
            }

            final long finalNumber = number;
            long approx = fib.stream().filter(val -> val <= finalNumber && used[fib.indexOf(val)] != 1)
                    .mapToLong(l -> l).max()
                    .orElseThrow(NumberFormatException::new);

            number -= approx;
            used[fib.indexOf(approx)] = 1;
        }

        writer.append("использованные числа: ").append(IntStream.range(0, used.length)
                .filter(i -> used[i] == 1)
                .mapToObj(index -> fib.get(index) + " ")
                .collect(Collectors.joining()).stripIndent()).append("\n");

        String result = Arrays.stream(used).mapToObj(String::valueOf).collect(Collectors.joining()) + " ";
        writer.append("результат: ").append(new StringBuilder(result.substring(0, result.lastIndexOf('1') + 2)).reverse().toString()).flush();

        reader.close();
        writer.close();
    }

    public static List<Long> getFib(long to) {
        List<Long> list = new ArrayList<>(List.of(1L, 1L));

        for (int i = 2; list.get(list.size() - 1) <= to; i++)
            list.add(list.get(i - 2) + list.get(i - 1));

        return list;
    }
}

