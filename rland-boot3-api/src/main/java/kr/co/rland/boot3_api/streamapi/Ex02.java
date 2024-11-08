package kr.co.rland.boot3_api.streamapi;

import org.apache.logging.log4j.util.PropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Ex02 {

    public static void main(String[] args) throws IOException {

        int[] ages = {10, 20, 30, 43, 23, 45, 12, 32, 42};
        IntStream stream = IntStream.of(ages);

        stream.filter(age -> age  >= 20)
                .sorted()
                .map(age -> age*2)
                .forEach(System.out::println);

        System.out.println("=======================================");


//        int[] result = stream
////                .filter(age -> age > 20) // 필터링
//                .sorted() // 정렬
//                .map(age -> age * 4) //가공
//                .toArray(); // stream과 .toArray사이에 데이터를 가공할 부분을 넣는다 intermediate부분

//        Arrays.stream(result).forEach(System.out::println);


//        System.out.println(Arrays.toString(result));


//        List<Exam> exams =
        OptionalInt max =
                Files
                        .lines(Path.of("exams.txt"))
                        .skip(1)
                        .map(Exam::of)
//                        .filter(exam -> exam.total() <= 100)
                        .mapToInt(exam -> exam.total())
                                .max();
//                        .sum();
//                                .count();
//                        .sorted(Exam::compareTo)
//                .forEach(exam -> {
//                    System.out.println(exam.total());
//                });
//                        .toList();  // Files.lines(Path.of()) 자체가 Stream api 를 사용하는것
        System.out.println(max.getAsInt());
//        System.out.println(sum);
//        System.out.println(count);
//        System.out.println(exams);
    }
}
