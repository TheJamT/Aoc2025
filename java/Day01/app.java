package Day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class app {
    public static void main(String[] args) {
        try {
            String data = Files.readString(Path.of("./Day01", "real_data.txt"));
            String[] split_data = data.split("\n");

            int part1 = part1(split_data);
            System.out.println("Part 1: " + part1);

            int part2 = part2(split_data);
            System.out.println("Part 2: " + part2);

        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static int part1(String[] data) {
        int position = 50;
        int zeros = 0;

        for (String string : data) {
            String direction = string.substring(0, 1);
            int distance = Integer.parseInt(string.substring(1));

            if (direction.equals("L")) {
                while (distance != 0) {
                    position -= 1;
                    distance -= 1;

                    if (position == -1) {
                        position = 99;
                    }
                }
            } else if (direction.equals("R")) {
                while (distance != 0) {
                    position += 1;
                    distance -= 1;

                    if (position == 100) {
                        position = 0;
                    }
                }
            }

            if (position == 0) {
                zeros += 1;
            }
        }

        return zeros;
    }

    public static int part2(String[] data) {
        int position = 50;
        int zeros = 0;

        for (String string : data) {
            String direction = string.substring(0, 1);
            int distance = Integer.parseInt(string.substring(1));

            if (direction.equals("L")) {
                while (distance != 0) {
                    position -= 1;
                    distance -= 1;

                    if (position == -1) {
                        position = 99;
                    }

                    if (position == 0) {
                        zeros += 1;
                    }
                }
            } else if (direction.equals("R")) {
                while (distance != 0) {
                    position += 1;
                    distance -= 1;

                    if (position == 100) {
                        position = 0;
                    }

                    if (position == 0) {
                        zeros += 1;
                    }
                }
            }
        }

        return zeros;
    }
}
