package net.bddtrader.practicetests;

import java.util.*;
import java.util.stream.Collectors;

import static org.aspectj.runtime.internal.Conversions.intValue;

public class LambdaIntro {
    public static void main(String[] args) {
        //findDuplicates1();
        findDuplicates2();

    }



    private static void findDuplicates2() {
        Integer[] numbersArray = new Integer[]{1,2,3,1,3,4};
       Set<Integer> allNumbers = new HashSet<>();
       Set<Integer>  duplicates = Arrays.stream(numbersArray)
               .filter(n -> !allNumbers.add(n))
               .collect(Collectors.toSet());
       duplicates.forEach(System.out::println);
       duplicates.forEach(n -> System.out.println(n));

        System.out.println(duplicates.size());
    }

    private static void findDuplicates1() {
       List<Integer> numbers = Arrays.asList(new Integer[]{1,2,1,2,4});
        numbers.stream().filter(i-> Collections.frequency(numbers,i) > 1)
                .collect(Collectors.toSet()).forEach(System.out::println);
    }

    public void compareUtility(){
        List<Employee> employees = Arrays.asList(
                new Employee("David"),
                new Employee("Naveen"),
                new Employee("Richard"),
                new Employee("Alex"));
       /* System.out.println("before sorting :" + Arrays.toString(employees));
       Arrays.sort(employees, Employee::compareNames);
        System.out.println("after sorting : " + Arrays.toString(employees));*/

        Comparator<Employee> employeeComparator = Comparator.comparing(Employee::getName);
        Collections.sort(employees, employeeComparator);
        employees.forEach(System.out::println);
    }
}
