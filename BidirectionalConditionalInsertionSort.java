import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BidirectionalConditionalInsertionSort {
    public static void bidirectionalConditionalInsertionSort(int[] array, int left, int right) {
        int SR = right;
        int SL = left;
        while (SL < SR) {

            swap(array, SR, SL + (SR - SL) / 2);

            if (array[SL] == array[SR]) {
                int isEqualResult = isEqual(array, SL, SR);
                if (isEqualResult == -1) {
                    return; 
                }
            }

            if (array[SL] > array[SR]) {
                swap(array, SL, SR);
            }
            int i;

            if (SR - SL >= 100) {
                for (i = SL + 1; i <= Math.sqrt(SR - SL); i++) {
                    if (array[SR] < array[i]) {
                        swap(array, SR, i);
                    } else if (array[SL] > array[i]) {
                        swap(array, SL, i);
                    }
                }
            } else {
                i = SL+1;
            }
            int LC = array[SL];
            int RC = array[SR];
            while (i < SR) {
                int currItem = array[i];
                if (currItem >= RC) {
                    array[i] = array[SR - 1];
                    insRight(array, currItem, SR, right);
                    SR--;
                } else if (currItem <= LC) {
                    array[i] = array[SL + 1];
                    insLeft(array, currItem, SL, left);
                    SL++;
                    i++;
                } else {
                    i++;
                }
            }

            SL++; 
            SR--; 
        
    }
}

    public static int isEqual(int[] array, int SL, int SR) {
        for (int k = SL + 1; k < SR; k++) {
            if (array[k] != array[SL]) {
                swap(array, k, SL);
                return k;
            }
        }
        return -1; 
    }

    public static void insRight(int[] array, int currItem, int SR, int right) {
        int j = SR;
        while (j <= right && currItem > array[j]) {
            array[j - 1] = array[j];
            j++;
        }
        array[j - 1] = currItem;
    }

    public static void insLeft(int[] array, int currItem, int SL, int left) {
        int j = SL;
        while (j >= left && currItem < array[j]) {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = currItem;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static int[] readDataset(String fileName) {
        int[] arr = {0};
        try {
            List<Integer> numberList = new ArrayList<>();
            
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                numberList.add(number);
            }
            
            arr = new int[numberList.size()];
            for (int i = 0; i < numberList.size(); i++) {
                arr[i] = numberList.get(i);
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory(); 

        Scanner inp = new Scanner(System.in);
        System.out.println("Enter a filename: ");
        String fileName = inp.next();

        int[] arr = readDataset(fileName);

        int left = 0;
        int right = arr.length-1;

        long startTime = System.nanoTime();
        bidirectionalConditionalInsertionSort(arr, left, right);
        long endTime = System.nanoTime();

        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        long runningTime = endTime - startTime;
        System.out.println("Used Memory: " + usedMemory + " bytes");
        System.out.println("Running time (in nanoseconds): " + runningTime + " ns");

        inp.close();

    }
}