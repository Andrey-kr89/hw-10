import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.*;

public class Streams {
    public static void main(String[] args) {
        //Task One
        List<String> names = List.of("Vasya", "Petya", "Kolya", "Anatoliy", "Galya", "Anakoliy");
        List<String> nameStr = IntStream.range(0, names.size())
                .filter(value -> value % 2 == 1)
                .mapToObj(value -> value + ". " + names.get(value))
                .collect(Collectors.toList());
        System.out.println(nameStr);

        //Task two
        Stream<String> nameStream = names.stream();
        List<String> v = nameStream
                .sorted(Collections.reverseOrder())
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(v.toString());

        //Task three
        List<String> numbers = Stream.of("1, 2, 0", "4, 5", "3, 9, 8")
                .flatMap(Pattern.compile(", ")::splitAsStream)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(numbers.toString());

        //Task four
        Stream<Integer> res = randomNumber(25214903917l, 11, 2 ^ 48L, 0);
        res.forEach(System.out::println);

        //task five
        Stream<String> streamA = Stream.of("A", "B", "C", "D");
        Stream<String> streamB = Stream.of("1", "2", "3", "4", "5", "6");
        Stream s = zip(streamA, streamB);
        s.forEach(System.out::println);
    }


    public static Stream<Integer> randomNumber(long a, int c, long m, int seed) {

        return Stream.iterate(seed, x -> x = Math.toIntExact(((a * x + c) % m)))
                .limit(5);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Stream<T> result = null;
        List<T> a = first.collect(Collectors.toList());
        List<T> b = second.collect(Collectors.toList());
        List<T> c = new LinkedList<>();

        int i = 0;
        while (!a.isEmpty() && !b.isEmpty()) {
            Random xr = new Random();
            int x = xr.nextInt(a.size());
            int y = xr.nextInt(b.size());
            boolean bool = xr.nextBoolean();
            if (bool) {
                c.add(a.get(x));
                a.remove(x);
            } else {
                c.add(b.get(y));
                b.remove(y);
            }
        }
        result = c.stream();
        return result;
    }

}
