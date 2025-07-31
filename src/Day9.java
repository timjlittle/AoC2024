import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9
{
    private ArrayList<Integer> map;


    public Day9(File file)  throws IOException {
        Scanner input;

        map = new ArrayList<>();


        input = new Scanner(file);

        String line = input.nextLine();

        int srcPos = 0;
        int fileNo = 0;

        while (srcPos < line.length()) {
            int count = line.charAt(srcPos) - '0';


            for (int x = 0; x < count; x++) {
                map.add(fileNo);
            }

            srcPos++;
            fileNo++;

            if (srcPos < line.length()) {
                count = line.charAt(srcPos) - '0';

                for (int x = 0; x < count; x++) {
                    map.add(-1);
                }
            }

            srcPos++;
        }
    }

    long calcTotal (){
        long total = 0;
        int x = 0;
        while (map.get(x) != -1){
            total += map.get(x) * x;
            x++;
        }

        return total;
    }

    public void doTaskOne() {
        boolean gapFound = true;

        int destPos = 0;
        int srcPos = map.size() - 1;

        while (gapFound) {

            int fileNo = map.get(srcPos);
            while (map.get(destPos) > -1 && destPos < srcPos) {
                destPos++;
            }

            if (destPos >= srcPos) {
                gapFound = false;
            } else {
                map.set(destPos, fileNo);
                map.set(srcPos, -1);
                srcPos--;
            }
        }

        System.out.println(calcTotal ());

    }

    int getGapPos (int srcPos, int fileNo, int fileLen){
        int gapPos = 1;
        boolean gapFound = false;

        while (!gapFound && gapPos < srcPos){

            if (map.get(gapPos) == -1){
                int gapLen = 1;

                while (gapLen < fileLen
                        && gapPos + gapLen < map.size()
                        && map.get(gapPos + gapLen) == -1 ){
                    gapLen++;
                }

                if (gapLen == fileLen){
                    gapFound = true;
                } else {
                    gapPos += gapLen;
                }

            } else {
                gapPos++;
            }
        }

        if (!gapFound){
            gapPos = -1;
        }

        return gapPos;
    }

    private void printMap() {
        for (int x = 0; x < map.size(); x++) {
            if (map.get(x) != -1) {
                System.out.print(map.get(x) + " ");
            } else {
                System.out.print(".");
            }
        }
        System.out.println();
    }

    public void doTaskTwo() {
        int srcPos = map.size() - 1;

        //printMap();
        while (srcPos > 0) {
            int fileNo = map.get(srcPos);
            if (fileNo != -1){
                int len = 0;
                while (srcPos >= 0 && map.get(srcPos) == fileNo) {
                    len++;
                    srcPos--;
                }

                int gapPos = getGapPos(srcPos, fileNo, len);

                if (gapPos != -1){
                    srcPos++;

                    for (int x = 0; x < len; x++) {
                        map.set(gapPos, fileNo);
                        map.set(srcPos, -1);

                        srcPos++;
                        gapPos++;
                    }
                    srcPos--;
                }

            } else {
                srcPos--;
            }

        }

        //printMap();

        long total = 0;

        for (int x = 0; x < map.size(); x++){
            if (map.get(x) != -1){
                total += x * map.get(x);
            }
        }

        System.out.println(total);
    }

}
