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
        final String startTimeStr = "9:00 am";
        Time startTime = Time.fromString(startTimeStr);
        TimeLine timeLine = new TimeLine(startTime);

        Integer assemblyLineCount = 1;
        System.out.println("Linha de montagem " + assemblyLineCount);

        while (!tasks.isEmpty()) {
            for (int i=0,len=tasks.size(); i<len;i++) {
                Task task = tasks.get(i);

                if (timeLine.executeTask(task)) {
                    tasks.remove(task);
                    len--;
                } else {
                    assemblyLineCount += 1;
                    System.out.println("Linha de montagem " + assemblyLineCount);
                    startTime = Time.fromString(startTimeStr);
                    timeLine.setCurrentTime(startTime);
                }
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
    }

    public static void main( String[] args ) {
        final App app = new App();
        app.start();
    }
}
