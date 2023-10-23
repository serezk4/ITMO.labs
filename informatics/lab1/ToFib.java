package inf.lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToFib {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.append("enter value to convert: ").flush();

        String input = reader.readLine();
        if (!input.matches("\\d+")) return;

        long number = Long.parseLong(input);
        List<Long> fib = getFib(number);
        List<Long> copy = new ArrayList<>(fib);
        int[] used = new int[fib.size()];

        System.out.println(fib);

        while (number > 0) {
            if (fib.contains(number)) {
                int index = fib.indexOf(number);
                used[index] = 1;
                fib.set(index, -1L);
                break;
            }

            final long fn = number;
            long approx = fib.stream().filter(val -> val <= fn).mapToLong(l->l).max().orElse(-1);
            if (approx == -1) return;

            number -= approx;
            int index = fib.indexOf(approx);
            fib.set(index, -1L);
            used[index] = 1;
        }

        writer.append(IntStream.range(0, used.length).filter(i -> used[i] == 1).mapToObj(copy::get).mapToLong(q->q).mapToObj(String::valueOf).map(q -> q + " ").collect(Collectors.joining()).stripIndent()).append("\n");

        String result = Arrays.stream(used).mapToObj(String::valueOf).collect(Collectors.joining()) + " ";
        writer.append("result: ").append(new StringBuilder(result.substring(1, result.lastIndexOf('1')+2)).reverse().toString()).flush();

        System.gc();

        reader.close();
        writer.close();
    }

    public static List<Long> getFib(long to) {
        List<Long> list = new ArrayList<>(List.of(1L,1L));

        for (int i = 2; list.get(list.size() - 1) <= to; i++)
            list.add(list.get(i - 2) + list.get(i - 1));

        return list;
    }
}
