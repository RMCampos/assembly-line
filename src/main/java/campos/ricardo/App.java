package campos.ricardo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private List<String> readFromInputFile() {
        try {
            final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            final StringBuilder sb = new StringBuilder();
            final String LINE_DIVISOR = "|";

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line.trim()).append(LINE_DIVISOR);
            }

            bufferedReader.close();
            inputStreamReader.close();

            return Arrays.asList(sb.toString().split("\\" + LINE_DIVISOR));
        } catch (IOException ioe) {
            System.out.println("Sorry! IOexception captured: " + ioe.getLocalizedMessage());
            ioe.printStackTrace();
        }

        return Arrays.asList("");
    }

    private List<Task> preProcessing(List<String> lines) {
        List<Task> tasks = new ArrayList<>();

        for (String assembly : lines) {
            if (assembly.toLowerCase().contains("maintenance")) {
                tasks.add(new Task(assembly.replace(" - maintenance", ""), 5));
                continue;
            }

            if (!assembly.toLowerCase().endsWith("min")) {
                System.out.println("Line doesn't match the expected format (ends with min expression): " + assembly);
                continue;
            }

            int idxLastSpace = assembly.lastIndexOf(' ');
            if (idxLastSpace < 0) {
                System.out.println("Line doesn't match the expected format (space before min expression): " + assembly);
                continue;
            }

            String durationExpression = assembly.substring(idxLastSpace).trim();
            Integer durationInMinutes = Integer.parseInt(durationExpression.replace("min", ""));

            tasks.add(new Task(assembly, durationInMinutes));
        }

        return tasks;
    }

    private void processTaks(List<Task> tasks) {
        Integer assemblyLineCount = 0;
        while (!tasks.isEmpty()) {
            assemblyLineCount++;
            System.out.println(String.format("Linha de montagem %d:", assemblyLineCount));

            final TimeLine timeLine = new TimeLine();

            /* Morning assembly steps: 3 hours */
            Integer morningAmount = 0;
            final List<Task> morningList = new ArrayList<>();
            for (int i=0,len=tasks.size(); i<len;i++) {
                Task task = tasks.get(i);

                Integer test = morningAmount + task.getDurationInMinutes();

                if (test.compareTo(Constraints.MORNING_MAX_MINUTES) <= 0) {
                    morningList.add(task);
                    morningAmount += task.getDurationInMinutes();
                }

                if (morningAmount.equals(Constraints.MORNING_MAX_MINUTES)) {
                    break;
                }
            }

            tasks.removeAll(morningList);
            morningList.forEach(task -> timeLine.printTask(task));

            /* Lunch time */
            final Task lunch = new Task("Almoço", Constraints.LUNCH_INTERVAL_MINUTES);
            timeLine.printTask(lunch);

            /* Afternoon assembly steps: 4 hours minumun or less than 5 hours */
            Integer afternoonAmount = 0;
            List<Task> afternoonList = new ArrayList<>();
            for (int i=0,len=tasks.size(); i<len;i++) {
                Task task = tasks.get(i);

                Integer test = afternoonAmount + task.getDurationInMinutes();

                if (test.compareTo(Constraints.AFTERNOON_MAX_MINUTES) <= 0) {
                    afternoonList.add(task);
                    afternoonAmount += task.getDurationInMinutes();
                }
            }

            tasks.removeAll(afternoonList);
            afternoonList.forEach(task -> timeLine.printTask(task));

            /* Labor gymnastics activities */
            final Task laborGymn = new Task("Ginástica laboral", 0);
            timeLine.printTask(laborGymn);

            System.out.println();
        }
    }

    private void start() {
        final List<String> lines = readFromInputFile();
        if (lines.isEmpty()) {
            System.out.println("Nothing to do!");
            return;
        }

        final List<Task> tasks = preProcessing(lines);

        processTaks(tasks);
    }

    public static void main( String[] args ) {
        final App app = new App();
        app.start();
    }
}
