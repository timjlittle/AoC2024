import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {

    private class Equation {
        private String raw;
        private long target = 0;
        ArrayList<Integer> values = new ArrayList<>();

        public long getTarget() {
            return target;
        }

        public Equation(String raw) {
            this.raw = raw;

            String [] parts = raw.split(":");
            target = Long.parseLong(parts[0]);

            String [] valStrings = parts[1].split(" ");


            for (String val : valStrings) {
                if (!val.trim().isEmpty()) {
                    values.add(Integer.parseInt(val));
                }
            }
        }

        public boolean isPossible(long curTotal, int pos, boolean task2) {
            boolean possible = false;

            if (pos < values.size()) {

                if (task2 && curTotal > 0) {
                    String strVal = "" + curTotal + "" + values.get(pos);

                    long newVal = Long.parseLong(strVal);
                    possible = isPossible(newVal, pos + 1, task2);

                }

                if (!possible) {
                    possible = isPossible(curTotal + values.get(pos), pos + 1, task2);
                }

                if (!possible) {
                    if (curTotal == 0) {
                        curTotal = 1;
                    }

                    possible = isPossible(curTotal * values.get(pos), pos + 1, task2);
                }

            } else {
                return (curTotal == target);
            }

            return possible;
        }
    }

    ArrayList<Equation> equations = new ArrayList<>();

    public Day7(File file)  throws IOException {
        Scanner input;

        input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            equations.add(new Equation(line));
        }
    }

    public long doTaskOne()  {
        long total = 0;

        for (Equation equation : equations) {
            if (equation.isPossible(0,0, false)) {
                total += equation.getTarget();
            }
        }

        System.out.println(total);

        return total;
    }

    public int doTaskTwo()  {
        long total = 0;

        for (Equation equation : equations) {
            if (equation.isPossible(0,0, true)) {
                System.out.println(equation.raw);
                total += equation.getTarget();
            }
        }

        System.out.println(total);


        return -1;
    }
}
