package net.bddtrader.practicetests;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Boxed {
    public static void main(String[] args) {
        //intStream();
       // longStream();
        doubleStream();
    }

    private static void doubleStream() {
        List<Double> doubleList = DoubleStream.of(1d,2d,3d)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(doubleList);
    }

    private static void longStream() {
        List<Long>  longList = LongStream.of(1L,2L,3L)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longList);
    }


    private static void intStream() {
        List<Integer> list = IntStream.of(1,2,3,4,5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(list);

        Optional<Integer>  max = IntStream.of(1,2,3,4,5)
                .boxed()
                .max(Integer::compareTo);
        System.out.println(max);

    }
}
