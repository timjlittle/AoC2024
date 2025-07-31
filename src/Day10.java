import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10 {
    private int [][] map;
    private int WIDTH = -1;
    private int DEPTH = -1;

    private int pathCount = 0;


    public Day10(File file)  throws IOException {
        Scanner input =new Scanner(file);
        ArrayList <String> vals = new ArrayList<>();


        while (input.hasNext()){
            String line = input.nextLine();
            vals.add(line);
        }

        WIDTH = vals.getFirst().length();
        DEPTH = vals.size();

        map = new int [WIDTH][DEPTH];

        for (int y = 0; y < DEPTH; y ++){
            String line = vals.get(y);

            for (int x = 0; x < line.length(); x++){
                int alt = (int) line.charAt(x) - '0';

                map [x][y] = alt;
            }
        }
    }

    private boolean isValidPos (int x, int y){
        return (x >= 0 && x < WIDTH && y >= 0 && y < DEPTH);
    }

    private void resetMap (){
        for (int y = 0; y < DEPTH; y ++){
            for (int x = 0; x < WIDTH; x++) {
                if (map [x][y] == -1){
                    map [x][y] = 9;
                }
            }
        }
    }

    private void countPaths (int x, int y, boolean paths) {
        int curVal = map [x][y];

        if (curVal == 9) {
            pathCount++;

            if (!paths)
                map [x][y] = -1;
        } else {
            //left
            if (isValidPos(x - 1, y)) {
                if (map[x - 1][y] == curVal + 1) {
                    countPaths (x - 1, y, paths);
                }
            }

            //right
            if (isValidPos(x + 1, y)) {
                if (map[x + 1][y] == curVal + 1) {
                    countPaths (x + 1, y, paths);
                }
            }

            //up
            if (isValidPos(x, y - 1)) {
                if (map[x][y - 1] == curVal + 1) {
                    countPaths (x, y - 1, paths);
                }
            }

            //down
            if (isValidPos(x, y + 1)) {
                if (map[x][y + 1] == curVal + 1) {
                    countPaths (x, y + 1 , paths);
                }
            }
        }

    }

    public int doTaskOne() {

        for (int y = 0; y < DEPTH; y++){
            for (int x = 0; x < WIDTH; x++){
                if (map[x][y] == 0){
                    countPaths (x,y, false);
                    resetMap ();
                }
            }
        }

        return pathCount;
    }

    public int doTaskTwo() {
        for (int y = 0; y < DEPTH; y++){
            for (int x = 0; x < WIDTH; x++){
                if (map[x][y] == 0){
                    countPaths (x,y, true);
                    resetMap ();
                }
            }
        }

        return pathCount;
    }

}
