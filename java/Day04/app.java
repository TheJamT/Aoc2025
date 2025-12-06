package Day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import Day04.Grid.Cell;
import Day04.Grid.Coordinate;

public class app {
    public static void main(String[] args) {
        try {
            List<String> data = Files.readAllLines(Path.of("./Day04", "real_data.txt"));

            Grid grid = GridFactory.createGrid(data);

            int part1 = part1(grid);
            System.out.println("Part 1: " + part1);

            int part2 = part2(grid);
            System.out.println("Part 2: " + part2);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        } catch (IllegalArgumentException e) {
            System.out.println("The grid had an unusual looking character in it. Oh dear");
        }
    }

    public static int part1(Grid grid) {
        int accessiblePositions = 0;
        Coordinate gridExtremities = grid.getGridSize();

        for (int x = 0; x < gridExtremities.x; x++) {
            for (int y = 0; y < gridExtremities.y; y++) {
                if (grid.getCell(grid.new Coordinate(x, y)) == Cell.PAPER) {
                    Coordinate currentPosition = grid.new Coordinate(x, y);

                    if (Arrays.stream(grid.getCellNeighbours(currentPosition)).filter(c -> c == Cell.PAPER)
                            .count() < 4) {
                        accessiblePositions++;
                    }
                }
            }
        }

        return accessiblePositions;
    }

    public static int part2(Grid grid) {
        int removed = 0;
        int lastRoundRemoved = 1;
        Coordinate gridExtremities = grid.getGridSize();

        while (lastRoundRemoved > 0) {
            lastRoundRemoved = 0;

            for (int x = 0; x < gridExtremities.x; x++) {
                for (int y = 0; y < gridExtremities.y; y++) {
                    if (grid.getCell(grid.new Coordinate(x, y)) == Cell.PAPER) {
                        Coordinate currentPosition = grid.new Coordinate(x, y);

                        if (Arrays.stream(grid.getCellNeighbours(currentPosition)).filter(c -> c == Cell.PAPER)
                                .count() < 4) {
                            grid.changeCell(currentPosition, Cell.EMPTY);
                            removed++;
                            lastRoundRemoved++;
                        }
                    }
                }
            }
        }

        return removed;
    }
}
