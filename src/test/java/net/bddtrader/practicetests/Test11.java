package net.bddtrader.practicetests;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Test11 {

    public static void main(String[] args) {
        //new Test11().countOccurrences();
        int[] givenArray = {10,101,202,303,400,501,602,703};
        int rowCount = getRowCount(givenArray);
        int finalArray[][] = new int[rowCount+1][givenArray.length];
        System.out.println("row count : " + rowCount + "  " + "col count :" + givenArray.length);
        populateFinalArrayAndPrint(finalArray,givenArray);

    }

    private static void populateFinalArrayAndPrint(int[][] finalArray, int[] givenArray) {
        int tempIndexForColumn0 =-1;
        int tempIndexForColumn1 =-1;
        int tempIndexForColumn2 =-1;
        int tempIndexForColumn3 =-1;
        int tempIndexForColumn4 =-1;
        int tempIndexForColumn5 =-1;
        int tempIndexForColumn6 =-1;
        int tempIndexForColumn7 =-1;
        int tempIndexForColumn8 =-1;
        int tempIndexForColumn9 =-1;

        for (int number : givenArray) {
            int lastDigit = number%10;
            switch (lastDigit){
                case 0:
                    finalArray[lastDigit][++tempIndexForColumn0] = number;
                    break;
                case 1:
                    finalArray[lastDigit][++tempIndexForColumn1] = number;
                    break;
                case 2:
                    finalArray[lastDigit][++tempIndexForColumn2] = number;
                    break;
                case 3:
                    finalArray[lastDigit][++tempIndexForColumn3] = number;
                    break;
                case 4:
                    finalArray[lastDigit][++tempIndexForColumn4] = number;
                    break;
                case 5:
                    finalArray[lastDigit][++tempIndexForColumn5] = number;
                    break;
                case 6:
                    finalArray[lastDigit][++tempIndexForColumn6] = number;
                    break;
                case 7:
                    finalArray[lastDigit][++tempIndexForColumn7] = number;
                    break;
                case 8:
                    finalArray[lastDigit][++tempIndexForColumn8] = number;
                    break;
                case 9:
                    finalArray[lastDigit][++tempIndexForColumn9] = number;
                    break;
                default:
                    break;
            }
        }



        for(int[] finalNumber : finalArray) {
            for (int finalElement : finalNumber) {
                System.out.print(finalElement + "  ");
            }
        }

    }

    private static int getRowCount(int[] givenArray) {
        int rowCount = -1;
        for (int element : givenArray) {
          int temp = element%10;
          if (temp > rowCount)
          rowCount = temp;
        }
        return rowCount;
    }

    private void countOccurrences(){
        ArrayList<String> animals = new ArrayList<>();
        animals.add("bat");
        animals.add("owl");
        animals.add("owl");
        animals.add("bat");
        animals.add("bat");

        Map<String,Long> counts =
                animals.stream().collect(Collectors.groupingBy(e->e, Collectors.counting()));
        System.out.println(counts);
    }
}
