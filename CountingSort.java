import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountingSort {
    public static void countingSort(int[] arr) {
        int n = arr.length;
        int max = getMax(arr, n);

        int[] output = new int[n];
        int[] count = new int[max + 1];

        for (int i = 0; i <= max; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
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
