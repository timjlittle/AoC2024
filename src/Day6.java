import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    private ArrayList<String> loadMap;
    private char[][] map;
    private char[][] oriMap;
    private int[] guardPos = {-1, -1};
    private int[] startPos = {-1, -1};
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private int direction = UP;
    private int width = -1;
    private int depth = -1;
    private enum WalkState {ONMAP, OFFMAP, LOOP};
    WalkState curState = WalkState.ONMAP;


    private void copyMap (char[][] src, char[][] dest) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    private boolean isLoop (int dir1, int dir2) {
        if (dir1 == dir2)
            return true;

        if (dir1 == UP && dir2 == DOWN)
            return true;

        if (dir1 == RIGHT && dir2 == LEFT)
            return true;

        if (dir1 == DOWN && dir2 == UP)
            return true;

        if (dir1 == LEFT && dir2 == RIGHT)
            return true;

        return false;
    }

    public Day6(File file)  throws IOException {
        Scanner input;
        boolean loadingRules = true;
        int y = 0, x = 0;

        loadMap = new ArrayList<>();

        input = new Scanner(file);
        String line = "";

        while (input.hasNextLine()) {
            line = input.nextLine();
            loadMap.add(line);

            x = line.indexOf('^');

            if (x > -1) {
                guardPos[0] = x;
                guardPos[1] = y;

                startPos[0] = x;
                startPos[1] = y;
            }

            y++;
        }

        depth = y;
        width  = line.length();

        map = new char[width][depth];
        oriMap = new char[width][depth];

        for (y = 0; y < depth; y++) {
            line = loadMap.get(y);
            for (x = 0; x < width; x++) {
                map[x][y] = line.charAt(x);
                oriMap[x][y] = map[x][y];
            }
        }
    }

    private void walk () {

        int nextX = guardPos[0];
        int nextY = guardPos[1];

        switch (direction) {
            case UP:
                nextY--;
            break;

            case RIGHT:
                nextX++;
            break;

            case DOWN:
                nextY++;
            break;

            case LEFT:
                nextX--;
            break;
        }

        if (nextY < 0 || nextX < 0 || nextY >= depth || nextX >= width) {
            curState = WalkState.OFFMAP;
        }

        if (curState == WalkState.ONMAP) {

            if (map[nextX][nextY] == '#' || map[nextX][nextY] == 'O') {
                direction = (direction + 1) % 4;
            }
            else {
                guardPos[0] = nextX;
                guardPos[1] = nextY;
            }

            //if (  map[nextX][nextY] == (char)('0' + direction) ){
            if (isLoop(direction, (int) (map[nextX][nextY] - '0'))){
                curState = WalkState.LOOP;
            }
        }

    }

    int countX(){
        int count = 0;

        for (int y = 0; y < depth; y++) {
            for (int x = 0; x < width; x++) {
                if (isDirectionChar(map[x][y])) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isDirectionChar(char val) {
        if (val >= '0' && val <= '3'){
            return true;
        } else {
            return false;
        }
    }

    public int doTaskOne()  {
        int ret = -1;

        do {
            map[guardPos[0]][guardPos[1]] = (char) ('0' + direction);
            walk();
        } while (curState == WalkState.ONMAP);

        if (curState == WalkState.OFFMAP) {
            System.out.println("Off Map");
        } else {
            System.out.println("Loop");
        }



        ret = countX();

        return ret;
    }

    private void printMap() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < depth; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }

    public int doTaskTwo()  {
        int loopCount = 0;
        int noLoop = 0;

        for (int y = 0; y < depth; y++) {
            for (int x = 0; x < width; x++) {
                copyMap(oriMap, map);
                curState = WalkState.ONMAP;
                guardPos[0] = startPos[0];
                guardPos[1] = startPos[1];
                direction = UP;

                if ((x != startPos[0] || y != startPos[1]) && map[x][y] != '#') {
                    map[x][y] = 'O';
                }

                do {
                    if (x == 10 && y == 36) {
                        System.out.println("Guard Pos = " + guardPos[0] + ", " + guardPos[1]);
                    }
                    map[guardPos[0]][guardPos[1]] = (char) ('0' + direction);
                    walk();
                } while (curState == WalkState.ONMAP);

                if (curState == WalkState.LOOP) {
                    loopCount++;
                } else {
                    noLoop++;
                }

                if (curState == WalkState.OFFMAP) {
                    System.out.print("Off Map (" + noLoop + ")");
                } else {
                    System.out.print("Loop (" + loopCount + ")");
                }

                System.out.println(" obstacle at y = " + y + ", x = " + x);
//                printMap();
            }

        }

        return loopCount;
    }
}
