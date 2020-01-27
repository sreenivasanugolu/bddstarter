package net.bddtrader.practicetests;

import java.util.Comparator;

public class Employee   {
    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public static int compareNames(Employee a1, Employee a2){
        return a1.name.compareToIgnoreCase(a2.name);
    }

    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }
}
