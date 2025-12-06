package Day04;

import java.util.Arrays;
import java.util.List;

import Day04.Grid.Cell;

public class GridFactory {
    public static Grid createGrid(List<String> data) {
        return new Grid(data.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Cell::fromString)
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new));
    }
}
