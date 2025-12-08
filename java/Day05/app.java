package Day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class app {
    public static void main(String[] args) {
        try {
            String[] data = Files.readString(Path.of("./Day05", "real_data.txt")).split("\n\n");

            int part01 = part01(data);
            System.out.println("Part 1: " + part01);

            long part02 = part02(data);
            System.out.println("Part 2: " + part02);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static int part01(String[] data) {
        int numberOfFresh = 0;

        String freshIngredientsString = data[0];
        String availableIngredientsString = data[1];

        List<Range> ranges = Arrays.stream(freshIngredientsString.split("\n")).map(r -> new Range(r)).toList();
        long[] availableIngredients = Arrays.stream(availableIngredientsString.split("\n"))
                .mapToLong(Long::parseLong).toArray();

        for (long ingredient : availableIngredients) {
            for (Range range : ranges) {
                if (range.contains(ingredient)) {
                    numberOfFresh += 1;
                    break;
                }
            }
        }

        return numberOfFresh;
    }

    public static long part02(String[] data) {
        String freshIngredientsString = data[0];
        List<Range> ranges = new ArrayList<>(
                Arrays.stream(freshIngredientsString.split("\n")).map(r -> new Range(r)).toList());

        boolean collapse = true;

        while (collapse) {
            ranges.sort(null);
            collapse = false;

            for (int i = 0; i < ranges.size() - 1; i++) {
                Range rangeOne = ranges.get(i);
                Range rangeTwo = ranges.get(i + 1);

                try {
                    Range combinedRanges = new Range(rangeOne, rangeTwo);
                    ranges.add(combinedRanges);

                    ranges.remove(i);
                    ranges.remove(i);

                    collapse = true;
                    break;

                } catch (RangesDontOverlapException e) {
                }
            }
        }

        long numberOfIngredients = 0;

        for (Range range : ranges) {
            numberOfIngredients += range.totalRangeContents();
        }

        return numberOfIngredients;
    }

}
