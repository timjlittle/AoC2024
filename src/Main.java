import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int day =0;
        int test = 0;
        boolean debug = false;
        int result =0;
        String debugStr;

        do {
            System.out.print ("Enter day (1 to 25): ");
            day = inp.nextInt();
        } while (day < 1 || day > 25);

        do {
            System.out.print ("Enter test (1 or 2): ");
            test = inp.nextInt();
        } while (test < 1 || test > 2);


        System.out.print ("Debug [y/n] ");
        debugStr = inp.next();
        debug = debugStr.equalsIgnoreCase("y");

        System.out.println("Calculating day" + day + ", task " + test + " debug ? " + debug);
        AoC aoc = new AoC(day, test, debug);
        result = aoc.getResult();


    System.out.println(result);

    }
}