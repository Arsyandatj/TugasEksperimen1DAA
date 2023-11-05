import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingSort {

    public static void countingSort(int[] array) {

        int max = array[0];
        int min = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        int range = max - min + 1;

        int[] count = new int[range];

        for (int i = 0; i < array.length; i++) {
            count[array[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
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

        int[] arr= readDataset(fileName);

        long startTime = System.nanoTime();
        countingSort(arr);
        long endTime = System.nanoTime();

        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        long elapsedTime = endTime - startTime;

        System.out.println("Used Memory: " + usedMemory + " bytes");
        System.out.println("Elapsed time (in nanoseconds): " + elapsedTime + " ns");
        inp.close();
    
    }
}
