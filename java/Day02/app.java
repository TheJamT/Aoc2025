package Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class app {
    public static void main(String[] args) {
        try {
            String data = Files.readString(Path.of("./Day02", "real_data.txt"));
            String[] splitData = data.split(",");

            long part1 = part1(splitData);
            System.out.println("Part 1: " + part1);

            long part2 = part2(splitData);
            System.out.println("Part 2:" + part2);

        } catch (IOException e) {
            System.out.println("Exception: " + e);

        }
    }

    public static long part1(String[] data) {
        long total = 0;

        for (String string : data) {
            String[] dataSplit = string.split("-");

            long lowerBound = Long.parseLong(dataSplit[0]);
            long upperBound = Long.parseLong(dataSplit[1]);

            for (long i = lowerBound; i <= upperBound; i++) {
                String stringRepresentation = String.valueOf(i);

                if (stringRepresentation.length() % 2 != 0) {
                    continue;
                }

                String start = stringRepresentation.substring(0, (stringRepresentation.length() / 2));
                String end = stringRepresentation.substring(stringRepresentation.length() / 2);

                if (start.equals(end)) {
                    total += i;
                }

            }
        }

        return total;
    }

    public static long part2(String[] data) {
        long total = 0;

        for (String string : data) {
            String[] dataSplit = string.split("-");

            long lowerBound = Long.parseLong(dataSplit[0]);
            long upperBound = Long.parseLong(dataSplit[1]);

            for (long i = lowerBound; i <= upperBound; i++) {
                String stringRepresentation = String.valueOf(i);

                for (int j = 1; j <= stringRepresentation.length(); j++) {
                    if (stringRepresentation.length() % j == 0) {
                        String base = null;
                        boolean match = false;

                        for (int k = 0; k <= stringRepresentation.length() - j; k += j) {
                            String instance = stringRepresentation.substring(k, k + j);

                            if (base == null) {
                                base = instance;
                            } else if (!base.equals(instance)) {
                                match = false;
                                break;
                            } else {
                                match = true;
                            }
                        }

                        if (match) {
                            total += i;
                            break;
                        }
                    }
                }
            }
        }

        return total;
    }
}
