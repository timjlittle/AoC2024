import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    private ArrayList <String> wordSeach = new ArrayList<>();
    private final String searchWord = "XMAS";

    public Day4 (File file)  throws IOException {
        Scanner input;

        input = new Scanner(file);

        while (input.hasNextLine()) {
            wordSeach.add(input.nextLine());
        }
    }

    private int directedCheck (int startX, int startY, int count, int xDelta, int yDelta) {
        int found = 0;

        if (count == searchWord.length() ) {
            found = 1;
        } else {

            String line = wordSeach.get(startY);

            if (line.charAt(startX) == searchWord.charAt(count)) {
                count++;
                if (count == searchWord.length() ) {
                    found = 1;
                } else {

                    startX += xDelta;
                    startY += yDelta;

                    if (startX >= 0 && startX < line.length() && startY >= 0 && startY < wordSeach.size()) {

                        found = directedCheck(startX, startY, count, xDelta, yDelta);
                    }
                }
            }
        }

        return found;

    }

    public int doTaskOne()  {
        int XmasCount = 0;

        for (int y = 0; y < wordSeach.size(); y++) {
            String line = wordSeach.get(y);

            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == searchWord.charAt(0)) {
                    int found = 0;
                    found += directedCheck (x,y,0,1,1);
                    found += directedCheck (x,y,0,0,1);
                    found += directedCheck (x,y,0,1,0);

                    found += directedCheck (x,y,0,1,-1);
                    found += directedCheck (x,y,0,-1,1);

                    found += directedCheck (x,y,0,-1,-1);
                    found += directedCheck (x,y,0,0,-1);
                    found += directedCheck (x,y,0,-1,0);

                    if (found > 0){
                        System.out.println("Found " + found + " at " + x + ", " + y);
                    }

                    XmasCount += found;
                }
            }
        }

        return XmasCount;
    }

    int isXmas (int startX, int startY){
        int ret = 0;
        String line1 = wordSeach.get(startY - 1);
        String line2 = wordSeach.get(startY + 1);

        if ( (line1.charAt(startX - 1)  == 'M' && line2.charAt(startX + 1) == 'S' || (line1.charAt(startX - 1)  == 'S' && line2.charAt(startX + 1) == 'M')) &&
                (line1.charAt(startX + 1)  == 'M' && line2.charAt(startX - 1) == 'S' || (line1.charAt(startX + 1)  == 'S' && line2.charAt(startX - 1) == 'M')) ) {
            ret = 1;
        }

        return ret;
    }
    public int doTaskTwo()  {
        int XmasCount = 0;

        for (int y = 1; y < wordSeach.size() - 1; y++) {
            String line = wordSeach.get(y);

            for (int x = 1; x < line.length() - 1; x++) {
                if (line.charAt(x) == 'A'){
                    XmasCount += isXmas (x,y);
                }

            }
        }


        return XmasCount;
    }
}
