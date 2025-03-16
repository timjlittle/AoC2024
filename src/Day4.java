import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    private ArrayList <String> wordSeach = new ArrayList<>();
    private String searchWord = "XMAS";

    public Day4 (File file)  throws IOException {
        Scanner input;

        input = new Scanner(file);

        while (input.hasNextLine()) {
            wordSeach.add(input.nextLine());
        }
    }

    private int directedCheck (int startX, int startY, int count, int xDelta, int yDelta) {
        int found = 0;
        String line = wordSeach.get(startY);

        if ()

    }

    public int doTaskOne()  {
        int XmasCount = 0;

        for (int y = 0; y < wordSeach.size(); y++) {
            String line = wordSeach.get(y);

            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == searchWord.charAt(0)) {

                }
            }
        }

        return XmasCount;
    }

    public int doTaskTwo()  {
        int XmasCount = 0;

        return XmasCount;
    }
}
