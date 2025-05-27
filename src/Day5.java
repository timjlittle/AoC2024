import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {
    private class RulePair {
        int first, second;
        public RulePair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    private class Rules {
        ArrayList<RulePair> rulesList;

        public Rules() {
            rulesList = new ArrayList<>();
        }

        public void addRule(RulePair rule) {
            rulesList.add(rule);
        }

        private boolean checkRule (RulePair rule, ArrayList<Integer> update) {
            boolean valid = true;
            boolean foundSecond = false;
            int x = 0;

            while (valid && x < update.size()) {
                if (update.get(x) == rule.first) {
                    if (foundSecond) {
                        valid = false;
                    }
                }

                if (update.get(x) == rule.second) {
                    foundSecond = true;
                }

                x++;
            }

            return valid;
        }

        private boolean isValid(ArrayList<Integer> update) {
            boolean correct = true;
            int x = 0;

            while (correct && x < rulesList.size()) {
                correct = checkRule(rulesList.get(x), update);

                x++;
            }

            return correct;
        }
    }

    private Rules rules = new Rules();
    private ArrayList<ArrayList<Integer>> updates = new ArrayList<>();

    public Day5 (File file)  throws IOException {
        Scanner input;
        boolean loadingRules = true;

        input = new Scanner(file);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (loadingRules) {
                String [] vals = line.split("\\|");
                if (vals.length > 1) {
                    RulePair newRule = new RulePair(Integer.parseInt(vals[0]), Integer.parseInt(vals[1]));
                    rules.addRule(newRule);

                } else {
                    loadingRules = false;
                }

            } else {
                String [] vals = line.split(",");
                ArrayList<Integer> update = new ArrayList<>();

                for (String val : vals) {
                    update.add(Integer.parseInt(val));
                }

                updates.add(update);

            }

        }
    }

    public int doTaskOne()  {
        int ret = -1;
        int midTotals = 0;


        for (ArrayList<Integer> update: updates) {
            if (rules.isValid(update)) {
                int midPoint = update.size() / 2;
                System.out.println (update.get(midPoint));
                midTotals += update.get(midPoint);
            }
        }

        return midTotals;
    }

    private ArrayList<Integer> fixUpdate (ArrayList<Integer> update) {
        ArrayList<Integer> fixedUpdate = new ArrayList<>();

        //for each number in the update
        for (int x = 0; x < update.size(); x++) {
            int val = update.get(x);
            int y = 0;

            //if it is a second number in the rules list
            for (int ruleNum = 0; ruleNum < rules.rulesList.size(); ruleNum++) {
                if (val == rules.rulesList.get(ruleNum).second) {
                    //check forwards for the first
                    y = x + 1;
                    int first = rules.rulesList.get(ruleNum).first;

                    while (y < update.size() && update.get(y) != first) {
                        y++;
                    }
                    //If first is found ahead
                    if (y < update.size()) {
                        //copy that first
                        if (!fixedUpdate.contains(update.get(y))) {
                            fixedUpdate.add(update.get(y));
                        }
                    }
                }

            }
            //copy into new list
            if (!fixedUpdate.contains(update.get(x))) {
                fixedUpdate.add(update.get(x));
            }
        }

        return fixedUpdate;
    }

    public int doTaskTwo()  {
        int ret = -1;
        int midTotals = 0;
        int count = 0;

        for (int x = 0; x < updates.size(); x++) {
            ArrayList<Integer> update = updates.get(x);
            if (!rules.isValid(update)) {
                System.out.print (update + " -> ");

                ArrayList<Integer> corrected = fixUpdate (update);

                count = 0;
                while (!rules.isValid(corrected)) {
                    corrected = fixUpdate (corrected);
                    count++;
                }

                System.out.println (corrected + "(" + count + ")");

                int midPoint = corrected.size() / 2;

                midTotals += corrected.get(midPoint);
            }
        }

        return midTotals;
    }
}
