package net.bddtrader.practicetests;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ForEachTests {
    public static void main(String[] args) {
        //arrayListExample();
        //mapExample();
        //printEvenNumbers();
        printHashMap();

    }

    private static void printHashMap() {
        Map<String, Integer> someMap = new HashMap<>();
        someMap.put("A", 1);
        someMap.put("B", 2);
        someMap.put("C", 3);
        someMap.put("D", 4);

           //Set<Integer> finalSet =
                   someMap.entrySet()
                .stream()
                .filter(o -> o.getValue()%2 == 0)
                        .collect(Collectors.toList())
                      .stream().map(o -> o.getValue()).collect(Collectors.toSet()).forEach(System.out::println);


    }

    private static void printEvenNumbers() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        list.stream()
                .filter(n -> n%2 == 0)
                .forEach(System.out::println);
        Consumer<Integer>  toPrint = System.out::print;
        list.forEach(toPrint);
        list.forEach(n -> System.out.print(n));
    }

    private static void mapExample() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        Consumer<Map.Entry<String,Integer>> action = entry -> {
            System.out.println("key is : " + entry.getKey());
            System.out.println("value is : " + entry.getValue());
        };

        System.out.println("Entry set with key and values");
        map.entrySet().forEach(System.out::println);
        System.out.println("Key set");
        map.keySet().forEach(System.out::println);
        map.keySet().forEach(e ->{
            System.out.println("key is :" + e.getBytes());
        });
        System.out.println("Values");
        map.values().forEach(System.out::println);
        map.values().forEach(e -> {
            System.out.println("value is :" + e.longValue());
        });
        map.entrySet().forEach(e -> {
            System.out.println("key is :" + e.getKey());
            System.out.println("value is :" + e.getValue());
        });
    }

    private static void arrayListExample() {
        ArrayList<Integer>  numbersList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Consumer<Integer> toDo = System.out::println;
        numbersList
                .stream()
                .filter(n -> n%2 == 0)
                .forEach(System.out::println);

        numbersList.stream().forEach(toDo);
    }
}
