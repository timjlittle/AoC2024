import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day3 {
    private File dataFile;
    private Scanner input;
    private String commands;

    public Day3 (File file) throws IOException {
        dataFile = file;
        input = new Scanner(file);
        commands = "";
    }

    private void readData() throws IOException {
        while (input.hasNext()) {
            commands += input.nextLine();
        }
    }

    int doTaskOne() throws IOException {
        int total = 0;
        int sum;
        int mulPos = -1, val1, val2;
        String valOneStr, valTwoStr;

        readData();

        mulPos = commands.indexOf("mul", mulPos + 1);
        while (mulPos != -1) {
            val1 = 0;
            val2 = 0;
            valOneStr = "";
            valTwoStr = "";


            mulPos += 3;
            if (commands.charAt(mulPos ) == '(') {
                mulPos++;

                int digitCount = 0;

                while (digitCount <= 3 && Character.isDigit(commands.charAt(mulPos))) {
                    digitCount++;
                    valOneStr += commands.charAt(mulPos++);
                }

                if (digitCount <= 3 && commands.charAt(mulPos) == ',') {
                    val1 = Integer.parseInt(valOneStr);
                    mulPos++;

                    digitCount = 0;
                    while (digitCount <= 3 && Character.isDigit(commands.charAt(mulPos))) {
                        digitCount++;
                        valTwoStr += commands.charAt(mulPos++);
                    }

                    if (digitCount <= 3 && commands.charAt(mulPos) == ')') {
                        val2 = Integer.parseInt(valTwoStr);



                            sum = val1 * val2;

                            total += sum;
                            System.out.println(valOneStr + " * " + valTwoStr + " = " + sum + ", total = " + total);

                    }

                }


            }

            mulPos = commands.indexOf("mul", mulPos);
        }


        return total;
    }

    int doTaskTwo() throws IOException {
        int total = 0;
        int sum;
        int mulPos = 0, val1, val2;
        String valOneStr, valTwoStr;

        readData();

        while (mulPos < commands.length()) {
            valOneStr = "";
            valTwoStr = "";

            if (commands.startsWith("mul", mulPos)) {
                mulPos += 3;

                if (commands.charAt(mulPos++) == '(') {
                int digitCount = 0;

                while (digitCount <= 3 && Character.isDigit(commands.charAt(mulPos))) {
                    digitCount++;
                    valOneStr += commands.charAt(mulPos++);
                }

                if (digitCount <= 3 && commands.charAt(mulPos) == ',') {
                    val1 = Integer.parseInt(valOneStr);
                    mulPos++;

                    digitCount = 0;
                    while (digitCount <= 3 && Character.isDigit(commands.charAt(mulPos))) {
                        digitCount++;
                        valTwoStr += commands.charAt(mulPos++);
                    }

                    if (digitCount <= 3 && commands.charAt(mulPos) == ')') {
                        val2 = Integer.parseInt(valTwoStr);


                        sum = val1 * val2;

                        total += sum;
                        System.out.println(valOneStr + " * " + valTwoStr + " = " + sum + ", total = " + total);

                    }
                }
                }
            } else if (commands.startsWith("don't", mulPos)) {
                mulPos = commands.indexOf("do", mulPos + 1);

                if (mulPos == -1){
                    mulPos = commands.length() + 1;
                }
            }
            else {
                mulPos++;
            }
        }

        return total;
    }
}
