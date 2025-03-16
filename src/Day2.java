import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    private File dataFile;
    private Scanner input;
    private ArrayList<String> reports = new ArrayList<>();

    public Day2 (File file) throws IOException{
        dataFile = file;

    }

    private void readData() throws IOException {
        input = new Scanner(dataFile);

        while (input.hasNextLine()) {
            reports.add(input.nextLine());
        }

    }

    public int doTaskOne() throws IOException {
        int result = 0;

        readData();

        for (String report : reports) {
            String [] levels = report.split(" ");

            int val1 = Integer.parseInt(levels[0]);
            int val2 = Integer.parseInt(levels[1]);
            int diff = val1 - val2;
            boolean asc = (diff < 0);

            diff = Math.abs(diff);

            boolean safe = (diff >= 1) && (diff <= 3);

            int x = 2;
            while (safe && x < levels.length) {
                val1 = val2;
                val2 = Integer.parseInt(levels[x]);
                diff = val1 - val2;

                if (((diff < 0) && !asc) || ((diff > 0) && asc)) {
                    safe = false;
                } else {
                    diff = Math.abs(diff);
                    safe = (diff >= 1) && (diff <= 3);
                }
                x++;
            }

            if (safe){
                System.out.println("Safe " + report);
                result++;
            }
            //else {
            //    System.out.println("Unsafe " + report);
            //}
        }

        return result;
    }

    public int doTaskTwo() throws IOException {
        int result = 0;

        readData();

        for (String report : reports) {
            String [] levels = report.split(" ");
            ArrayList <Integer> vals = new ArrayList<>();
            String removedItems = "";

            for (int i = 0; i < levels.length; i++) {
                vals.add(Integer.parseInt(levels[i]));
            }

            int tries = 0;
            boolean safe = true;

            //System.out.print (report);

            do {

                int val1 = vals.get(0);
                int val2 = vals.get(1);
                int diff = val1 - val2;
                boolean asc = (diff < 0);


                diff = Math.abs(diff);

                safe = (diff >= 1) && (diff <= 3);



                int x = 2;
                while (x < vals.size() && safe) {
                    val1 = val2;
                    val2 = vals.get(x);
                    diff = val1 - val2;

                    if (((diff < 0) && !asc) || ((diff > 0) && asc)) {
                        safe = false;
                    } else {
                        diff = Math.abs(diff);
                        if (!((diff >= 1) && (diff <= 3))) {
                            safe = false;
                        }
                    }
                    x++;
                }

                if (!safe ) {
                    vals.clear();

                    for (int i = 0; i < levels.length; i++) {
                        if (i != tries) {
                            vals.add(Integer.parseInt(levels[i]));
                        }
                    }

                    tries++;
                }

            } while (!safe && tries <= levels.length);

            if (safe){
                //System.out.println(" safe " + " removed = " + safeCount);
                result++;
            }
            else {
                System.out.println(report + " unsafe " + " removed = " + tries + " (" + removedItems + ")");
            }
        }

        return result;
    }


}
