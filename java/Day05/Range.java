package Day05;

import java.util.ArrayList;
import java.util.List;

public class Range implements Comparable<Range> {
    private long startNumber;
    private long endNumber;

    Range(long start, long end) {
        startNumber = start;
        endNumber = end;
    }

    Range(String range) throws IllegalArgumentException {
        if (!range.trim().matches("[0-9]*-[0-9]*")) {
            throw new IllegalArgumentException("Range does not match the correct format: " + range);
        }

        String[] rangeSplit = range.split("-");

        startNumber = Long.parseLong(rangeSplit[0]);
        endNumber = Long.parseLong(rangeSplit[1]);
    }

    public Range(Range rangeOne, Range rangeTwo) throws RangesDontOverlapException {
        List<Range> ranges = new ArrayList<>();

        ranges.add(rangeOne);
        ranges.add(rangeTwo);
        ranges.sort(null);

        if (ranges.get(0).getEnd() < ranges.get(1).getStart()) {
            throw new RangesDontOverlapException();
        } else {
            startNumber = Math.min(ranges.get(0).startNumber, ranges.get(1).startNumber);
            endNumber = Math.max(ranges.get(0).endNumber, ranges.get(1).endNumber);
        }
    }

    public boolean contains(Long candidate) {
        if (candidate >= startNumber && candidate <= endNumber) {
            return true;
        } else {
            return false;
        }
    }

    public Long getStart() {
        return startNumber;
    }

    public Long getEnd() {
        return endNumber;
    }

    public Long totalRangeContents() {
        return (endNumber - startNumber) + 1;
    }

    @Override
    public int compareTo(Range o) {
        return Long.compare(startNumber, o.startNumber);
    }
}

class RangesDontOverlapException extends Exception {
    public RangesDontOverlapException() {
    }
}