import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    private char [][] antennaMap;
    private int [][] antinodeMap;
    private int width = -1;
    private int depth = -1;

    public Day8(File file)  throws IOException {
        Scanner input;

        ArrayList<String> lines = new ArrayList<>();

        input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();

            lines.add(line);
        }

        width = lines.getFirst().length();
        depth = lines.getLast().length();
        antennaMap = new char[depth][width];
        antinodeMap = new int[depth][width];
        for (int y = 0; y < width; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                antennaMap[y][x] = line.charAt(x);
                antinodeMap[y][x] = 0;
            }
        }
    }

    private void tagAntinodes (int x, int y, boolean dayTwo) {
        char antenna =  antennaMap[y][x];
        int diffX = x * -1;;
        int diffY = y * -1;
        int newX, newY;

        antinodeMap[y][x] = 1;

        while (y + diffY < depth) {
            while (x + diffX < width) {

                if (antennaMap[diffY + y][x + diffX] == antenna) {
                    int counter = 1;
                    int factor = 2;
                    if (dayTwo) {
                        factor = 1;
                    }

                    if (diffX != 0 || diffY != 0) {
                        do {
                            newX = x + diffX * counter * factor;
                            newY = y + diffY * counter * factor;


                            if (newY >= 0 && newY < depth && newX >= 0 && newX < width) {
                                antinodeMap[newY][newX] = 1;
                            }


                            //System.out.println("Counter: " + counter + " newX: " + newX + " newY: " + newY);

                            counter++;
                        } while (dayTwo && newY >= 0 && newY < depth && newX >= 0 && newX < width);
                    }
                }
                diffX++;
            }
            diffX = x * -1;
            diffY++;
        }
    }

    public int doTaskOne (boolean dayTwo) {
        int total = 0;

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                if (antennaMap[y][x] != '.') {
                    tagAntinodes(x,y, dayTwo);
                }
            }
        }

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                total = total + antinodeMap[y][x];
            }
        }

        //printAntinodes();

        return total;
    }

    private void printAntinodes () {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                if (antinodeMap[y][x] == 1) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

}
