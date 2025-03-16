import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1{
    private File dataFile;
    private Scanner input;
    int taskNo;
    private ArrayList<Integer> list1 = new ArrayList<>();
    private ArrayList<Integer> list2 = new ArrayList<>();

    public Day1 (File file) throws IOException{
        dataFile = file;

        input = new Scanner(file);
    }

    private void readData() throws IOException {
        while (input.hasNext()) {
            String line = input.nextLine();
            String [] vals = line.split("   ");
            list1.add(Integer.parseInt(vals[0]));
            list2.add(Integer.parseInt(vals[1]));
        }

        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);

    }

    public int doTaskOne() throws IOException {
        int result = 0;

        readData();
        for (int x = 0; x < list1.size(); x++) {
            result += Math.abs(list1.get(x) - list2.get(x));
        }

        return result;
    }

    public int doTaskTwo() throws IOException {
        int result = 0;
        readData();

        for (int x = 0; x < list1.size(); x++) {
            int val = list1.get(x);

            for (int y = 0; y < list2.size(); y++) {
                int count = 0;
                if (val == list2.get(y)) {
                    count++;
                }

                result += val * count;
            }

        }

        return result;
    }

}
