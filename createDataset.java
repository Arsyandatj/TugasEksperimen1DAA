import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class createDataset {

    public static void generateDataset(int num) {
        Random random = new Random();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dataset_" + num + ".txt"));

            for (int i = 0; i < num; i++) {
                int val = random.nextInt(num + 1);
                writer.write(String.valueOf(val));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Masukan jumlah angka dalam dataset: ");
        int num = inp.nextInt();
        generateDataset(num);
        inp.close();
    }
}
