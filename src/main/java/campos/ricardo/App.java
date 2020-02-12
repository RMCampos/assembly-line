package campos.ricardo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
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

            return Arrays.asList(sb.toString().split(LINE_DIVISOR));
        } catch (IOException ioe) {
            System.out.println("Sorry! IOexception captured: " + ioe.getLocalizedMessage());
            ioe.printStackTrace();
        }

        return Arrays.asList("");
    }

    private List<Task> preProcessing(List<String> lines) {
        List<Task> tasks = new ArrayList<>();

        for (String assembly : lines) {
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
    }

    private void processTaks(List<Task> tasks) {
        final String startTime = "9:00 am";
        TimeLine timeLine = new TimeLine(startTime);

        for (Task task : tasks) {
            try {
                timeLine.addTimeInMinutes(task.getDurationInMinutes());
            } catch (Exception e) {
                // !?
            }
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

        // process here
        //String currentTime = MORNING_START;
        //int count = 1;
        //boolean isGymLaborTime = false;

        //final String MSG_TEMPLATE = "Linha de montagem %d:";
        //System.out.println(String.format(MSG_TEMPLATE, count));

        for (String assembly : lines) {
            currentTime = TimeUtil.addMinutosToTime(currentTime, durationInMinutes);

            if (TimeUtil.isBeforeOrEqualsTo(currentTime, MORNING_END)) {
                String moment = TimeUtil.formatCurrentTime(currentTime);
                System.out.println(moment + " " + assembly);
            } else {
                System.out.println("Almoço");
            }

             */

            //if (isGymLaborTime) {
            //    System.out.println(String.format(MSG_TEMPLATE, ++count));
            //}
        }
    }

    public static void main( String[] args ) {
        final App app = new App();
        app.start();
    }
}
