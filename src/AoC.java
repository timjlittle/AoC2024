import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AoC {
    private int dayNo, testNo;
    private boolean debug;
    private File file;

    public AoC(int dayNo, int testNo, boolean debug) {
        this.dayNo = dayNo;
        this.testNo = testNo;
        this.debug = debug;
    }

    protected void openFile () throws IOException {
        String fileName = "./data/day" + dayNo;
        if (debug) {
            fileName += "_test.txt";
        } else {
            fileName += "_real.txt";
            }

        file = new File(fileName);
    }

    public int getResult (){
        int result = -1;
        long longRes = -1;

        try {
            openFile();

            switch (dayNo){
                case 1:
                    Day1 task = new Day1(file);
                    if (testNo == 1)
                        result = task.doTaskOne();
                    else {
                        result = task.doTaskTwo();
                    }
                    break;

                    case 2:
                        Day2 task2 = new Day2 (file);
                        if (testNo == 1)
                            result = task2.doTaskOne();
                        else {
                            result = task2.doTaskTwo();
                        }
                        break;

                case 3:
                    Day3 task3 = new Day3 (file);
                    if (testNo == 1)
                        result = task3.doTaskOne();
                    else {
                        result = task3.doTaskTwo();
                    }
                    break;

                case 4:
                    Day4 task4 = new Day4 (file);
                    if (testNo == 1)
                        result = task4.doTaskOne();
                    else {
                        result = task4.doTaskTwo();
                    }
                    break;

                case 5:
                    Day5 task5 = new Day5 (file);
                    if (testNo == 1)
                        result = task5.doTaskOne();
                    else {
                        result = task5.doTaskTwo();
                    }
                    break;

                case 6:
                    Day6 task6 = new Day6 (file);
                    if (testNo == 1)
                        result = task6.doTaskOne();
                    else {
                        result = task6.doTaskTwo();
                    }
                    break;

                case 7:
                    Day7 task7 = new Day7 (file);
                    if (testNo == 1)
                        task7.doTaskOne();
                    else {
                        result = task7.doTaskTwo();
                    }
                    break;

                case 8:
                    Day8 task8 = new Day8 (file);
                    if (testNo == 1)
                        result = task8.doTaskOne(false);
                    else {
                        result = task8.doTaskOne(true);
                    }
                    break;


                default:
                        System.out.println("Day " + dayNo + " not implemented yet");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }


}
