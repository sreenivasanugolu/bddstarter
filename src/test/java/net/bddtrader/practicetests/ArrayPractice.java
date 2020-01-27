package net.bddtrader.practicetests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayPractice{
   public static void main(String[] args) {

      ArrayPractice object =  new ArrayPractice();

      object.ddArray();
   }

   private void ddArray() {
      int[] inputArray = new int[]{100,200,300,400,1,11,201,303,403,503,3,304,4};

      int[][] doubleDimensionalArray =
      Arrays.stream(inputArray)
            .boxed()
            .collect(Collectors.groupingBy(this::getKey))
            .entrySet()
            .stream()
            .map(o -> convert(o.getValue()))
            .toArray(int[][]::new);

      Arrays.stream(doubleDimensionalArray)
               .map(o -> Arrays.toString(o))
               .forEach(System.out::println);

   }

   private int[] convert(List<Integer> integerList) {
      return integerList.stream()
                     .mapToInt(x -> x)
                     .toArray();
   }

   private int getKey(int number) {
      return number % 10;
   }
}