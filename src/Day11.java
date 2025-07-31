import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day11 {
    ArrayList <String> pebbles;
    Map <String, Long> valsCache = new HashMap<>();


    public Day11(File file)  throws IOException {
        Scanner input = new Scanner(file);

        String line = input.nextLine();

        pebbles = new ArrayList<>(Arrays.asList(line.split(" ")));
    }

    public void doBlinks (int numBlinks){
        for (int count = 1; count <= numBlinks; count++){
            int pos = 0;

            while (pos < pebbles.size()){
                if (pebbles.get(pos).equals("0")){
                    pebbles.set(pos, "1");
                } else if (pebbles.get(pos).length() % 2 == 0){
                    String right = pebbles.get(pos).substring(0, pebbles.get(pos).length() / 2);
                    String left = pebbles.get(pos).substring(pebbles.get(pos).length() / 2);

                    pebbles.set(pos, right);

                    long val = Long.parseLong(left);
                    left = "" + val;

                    pebbles.add(pos + 1, left);
                    pos++;

                } else {

                    long val = Long.parseLong(pebbles.get(pos));
                    val *= 2024;

                    pebbles.set(pos, "" + val);

                }

                pos++;
            }

            System.out.println("After blink " + count + " pebbles: " + pebbles.size());
        }

    }

    private long countPebbles (long pebbleVal, int blinksLeft, long currTotal){
        if (blinksLeft == 0) {
            return currTotal;
        }

        long extra = 0;
        long oriTotal = currTotal;

        String key = "" + pebbleVal + " " + blinksLeft;

        if (valsCache.containsKey(key)){
            return currTotal + valsCache.get(key);
        }

        if (pebbleVal == 0){
            currTotal = countPebbles (1, blinksLeft - 1, currTotal);
            extra = currTotal - oriTotal;
            valsCache.put(key, extra);
        } else {
            double digitCount = Math.floor(Math.log10(pebbleVal)) + 1;

            if (digitCount % 2 == 0){
                int split = (int)Math.pow(10, digitCount /2);
                long left = pebbleVal / split;
                long right = pebbleVal % split;

                currTotal += countPebbles(left, blinksLeft - 1, 0) + countPebbles(right, blinksLeft - 1, 1);
                extra = currTotal - oriTotal;
                valsCache.put(key, extra);
            } else {
                currTotal = countPebbles (pebbleVal * 2024, blinksLeft - 1, currTotal);
                extra = currTotal - oriTotal;
                valsCache.put(key, extra);
            }
        }

        return currTotal;
    }

    public long doBlinks2 (int numBlinks){
        long pebbleCount = 0;

        for (int count = 0; count < pebbles.size(); count++){
            System.out.println("Processing pebble: " + count);
            pebbleCount += countPebbles(Long.parseLong(pebbles.get(count)), numBlinks, 1);
        }

        System.out.println();

        return pebbleCount;
    }

    public int doTaskOne() {
        //doBlinks (25);

        return (int)doBlinks2 (25);

        //return pebbles.size();
    }

    public int doTaskTwo() {
        long pebbleCount = 0;

        pebbleCount = doBlinks2 (75);

        System.out.println("Pebble count = " + pebbleCount);
        System.out.println("Cache size: " + valsCache.size());

        return pebbles.size();
    }
}
