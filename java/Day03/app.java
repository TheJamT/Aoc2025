package Day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class app {
    public static void main(String[] args) {
        try {
            String data = Files.readString(Path.of("./Day03", "real_data.txt"));
            String[] splitData = data.split("\n");

            long part1 = joltageCalculator(splitData, 2);
            System.out.println("Part 1: " + part1);

            long part2 = joltageCalculator(splitData, 12);
            System.out.println("Part 2: " + part2);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static long joltageCalculator(String[] data, int numberOfBatteries) {
        long totalJoltage = 0;

        for (String batteryBank : data) {
            ArrayList<Integer> digits = new ArrayList<Integer>();
            int startPosition = 0;

            List<Integer> batteries = Arrays.stream(batteryBank.split("")).mapToInt(Integer::parseInt).boxed().toList();

            for (int i = numberOfBatteries; i >= 1; i--) {
                List<Integer> range = batteries.subList(startPosition, batteries.size() - (i - 1));

                Integer maxDigit = range.stream().mapToInt(Integer::intValue).max().orElseThrow();
                startPosition = range.indexOf(maxDigit) + 1 + startPosition;

                digits.add(maxDigit);
            }

            long joltage = Long.parseLong(digits.stream().map(String::valueOf).collect(Collectors.joining()));
            totalJoltage += joltage;
        }

        return totalJoltage;
    }
}
