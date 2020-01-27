package net.bddtrader.practicetests;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBuilders {



    public static void main(String[] args) {
        //streamOf();
        //streamOfArrayElements();
        //listStream();
        //stringStream();


        //*******convert streams to collections********
       // convertStreamToListAndPrint();
        // convertStreamToArrayAndPrint();
       // filterStartsWith();
        //filterAndMap();
        matchUtility();
    }

    private static void matchUtility() {
        List<String> names = addNames();
        boolean one = names.stream().anyMatch(s -> s.startsWith("S"));
        boolean two = names.stream().anyMatch(s -> s.startsWith("s"));
        boolean three = names.stream().allMatch(s -> s.endsWith("S"));
        boolean four = names.stream().noneMatch(s -> s.isEmpty());
        System.out.println(one + " " + two+ " " +three+ " " +four);
        long count = names.stream().filter(s ->s.startsWith("S")).count();
        System.out.println("starting with S " + count);
    }

    private static void filterAndMap() {
        List<String> names = addNames();
        names.stream().filter(s-> s.startsWith("S")).sorted()
        .map(String::toUpperCase)
        .forEach(System.out::println);

        List<String> sortedList = names.stream()
                .filter(s->s.startsWith("S"))
                .sorted().map(String::toLowerCase)
                .collect(Collectors.toList());
        System.out.println("sorted list is :" + sortedList);
    }

    private static void filterStartsWith() {

        List<String> names =  addNames();
        names.stream().filter((s -> s.startsWith("A"))).forEach(System.out::println);
    }

    private static List<String> addNames() {

        List<String> names = new ArrayList<>();
        names.add("Amitabh");
        names.add("Shekar");
        names.add("Aman");
        names.add("Rahul");
        names.add("Shahrukh");
        names.add("Salman");
        names.add("Yana");
        names.add("Lokesh");
        return names;
    }

    private static void convertStreamToArrayAndPrint() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i <10 ; i++) {
            integerList.add(i);
        }

        Integer[] integerArray = integerList.stream().filter(i-> i%2 == 0).toArray(Integer[]::new);
        Arrays.stream(integerArray).forEach(i-> System.out.print(i));
    }

    private static void convertStreamToListAndPrint() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <10; i++) {
            list.add(i);
        }

       // list.stream().filter(i -> i%2 ==0).forEach(i -> System.out.print(i));
        List<Integer> evenNumbersList = list.stream().filter(i -> i%2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbersList);
    }


    private static void generate() {
        Stream<Date> stream = Stream.generate(() -> {return new Date();});
        stream.forEach(p -> System.out.println(p));
    }



    private static void stringStream() {
        Stream.of("s$t$u$v".split("\\$")).forEach(p-> System.out.println(p));
    }

    private static void listStream() {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<10; i++){
            list.add(i);
        }
        list.stream().forEach(i-> System.out.println(i));
    }

    private static void streamOfArrayElements() {
        Stream<Integer> stream = Stream.of(new Integer[]{1,2,3,4,5});
        stream.forEach(i-> System.out.println(i));
    }

    private static void streamOf() {
        Stream<Integer> stream = Stream.of(1,2,3,4,5);
        stream.forEach(i -> System.out.println(i));
    }
}
