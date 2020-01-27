package net.bddtrader.practicetests;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Locale;

public class DateTimeTests {
    public static void main(String[] args) {
        //dateTimeUtility();
        findLocale();


    }

    private static void findLocale() {
        HttpServletRequest request = null;
        Locale locale = Locale.getDefault();
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getCountry());
        System.out.println(System.getProperty("user.country"));
    }

    private static void dateTimeUtility() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek().toString().toLowerCase());

        LocalDate date = LocalDate.of(2019, Month.OCTOBER, 01);
        System.out.println(date);

        LocalDate DOB = LocalDate.of(1975, Month.JULY,15);
        LocalDate today = LocalDate.now();
        Period age = Period.between(DOB, today);
        Period age2 = Period.between(today, DOB);
        System.out.printf("Age is %d years, %d months, and %d days  " ,    age.getYears() , age.getMonths() , age.getDays());
        System.out.println(age);
        System.out.println("Age2 is : "  + age2);
    }


}
