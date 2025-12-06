package Day04;

import java.util.ArrayList;

public class Grid {
    enum Cell {
        EMPTY,
        PAPER;

        public static Cell fromString(String cell) throws IllegalArgumentException {
            if (cell.equals("@")) {
                return Cell.PAPER;
            } else if (cell.equals(".")) {
                return Cell.EMPTY;
            } else {
                throw new IllegalArgumentException("Invalid cell type");
            }
        }
    }

    class Coordinate {
        int x;
        int y;

        Coordinate(int xCoordinate, int yCoordinate) {
            x = xCoordinate;
            y = yCoordinate;
        }
    }

    Cell[][] gridInner;

    Grid(Cell[][] grid) {
        gridInner = grid;
    }

    public Coordinate getGridSize() {
        int y = gridInner.length;
        int x = gridInner[0].length;

        return new Coordinate(x, y);
    }

    public Cell getCell(Coordinate coordinate) {
        return gridInner[coordinate.y][coordinate.x];
    }

    public void changeCell(Coordinate coordinate, Cell newCell) {
        gridInner[coordinate.y][coordinate.x] = newCell;
    }

    public Cell[] getCellNeighbours(Coordinate cell) {
        ArrayList<Cell> neighbours = new ArrayList<Cell>();

        // Above Left
        if (cell.y > 0 && cell.x > 0) {
            neighbours.add(getCell(new Coordinate(cell.x - 1, cell.y - 1)));
        }

        // Above Middle
        if (cell.y > 0) {
            neighbours.add(getCell(new Coordinate(cell.x, cell.y - 1)));
        }

        // Above Right
        if (cell.y > 0 && cell.x < getGridSize().x - 1) {
            neighbours.add(getCell(new Coordinate(cell.x + 1, cell.y - 1)));
        }

        // Right
        if (cell.x < getGridSize().x - 1) {
            neighbours.add(getCell(new Coordinate(cell.x + 1, cell.y)));
        }

        // Bottom Right
        if (cell.x < getGridSize().x - 1 && cell.y < getGridSize().y - 1) {
            neighbours.add(getCell(new Coordinate(cell.x + 1, cell.y + 1)));
        }

        // Bottom
        if (cell.y < getGridSize().y - 1) {
            neighbours.add(getCell(new Coordinate(cell.x, cell.y + 1)));
        }

        // Bottom Left
        if (cell.y < getGridSize().y - 1 && cell.x > 0) {
            neighbours.add(getCell(new Coordinate(cell.x - 1, cell.y + 1)));
        }

        // Left
        if (cell.x > 0) {
            neighbours.add(getCell(new Coordinate(cell.x - 1, cell.y)));
        }

        return neighbours.toArray(Cell[]::new);
    }

}
