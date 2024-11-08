package kr.co.rland.boot3_api.streamapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Ex01 {
    public static void main(String[] args) {

//        System.out.println(Arrays.toString(stringArray));
////        Arrays.sort(stringArray,(o1, o2) -> o1.compareTo(o2)); 람다식으로도 할수있지만 이것마저 더 간단하게 하고싶다.
//        Arrays.sort(stringArray, String::compareTo);
//        System.out.println(Arrays.toString(stringArray));

        String[] stringArray = {"Barbara", "Zimy", "James", "Mary", "Hello", "John", "Amy", "Patricia", "Robert", "Michael", "Linda"};
        List<String> stringList = Arrays.asList(stringArray);

        stringList.stream()
                .filter(string -> string.length() > 4)
                .sorted(String::compareTo)
                .map(s -> s+" kakak")
                .forEach(System.out::println);


        Exam[] exams = {
                new Exam(1, 1, 1),
                new Exam(4, 4, 4),
                new Exam(2, 2, 2),
                new Exam(3, 3, 3),
        };

        List<Exam> examList = Arrays.asList(exams);
        examList.stream()
                .filter(exam -> exam.total()<10)
                .map(exam -> exam.total())
                .sorted(Comparator.naturalOrder())
                .forEach(exam -> System.out.println(exam.toString()));

//        Arrays.sort(exams,Exam::compareTo);
//        System.out.println(Arrays.toString(exams));


    }
}
