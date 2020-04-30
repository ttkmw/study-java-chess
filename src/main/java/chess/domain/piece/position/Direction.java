package chess.domain.piece.position;

import java.util.Arrays;

public enum Direction {
    STAY(0, 0, new StayIdentifier()),
    NORTH(0, 1, new NorthIdentifier()),
    SOUTH(0, -1, new SouthIdentifier()),
    EAST(1, 0, new EastIdentifier()),
    WEST(-1, 0, new WestIdentifier()),
    NORTH_EAST(1, 1, new NorthEastIdentifier()),
    NORTH_WEST(-1, 1, new NorthWestIdentifier()),
    SOUTH_EAST(1, -1, new SouthEastIdentifier()),
    SOUTH_WEST(-1, -1, new SouthWestIdentifier());

    private final int horizontal;
    private final int vertical;
    private final DirectionIdentifier directionIdentifier;

    Direction(int horizontal, int vertical, DirectionIdentifier directionIdentifier) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.directionIdentifier = directionIdentifier;
    }

    static Direction calculate(Position from, Position to) {
        //todo: add test, check throw
        return Arrays.stream(values())
                .filter(direction -> direction.is(from, to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s에서 %s로의 방향을 알 수 없습니다.", from, to)));
    }

    int getHorizontal() {
        return horizontal;
    }

    int getVertical() {
        return vertical;
    }

    boolean isPerpendicular() {
        return isVertical() || isHorizontal();
    }

    boolean isNotPerpendicular() {
        return !isVertical() && !isHorizontal();
    }

    boolean isVertical() {
        return this == NORTH || this == SOUTH;
    }

    boolean isDiagonal() {
        return this == NORTH_WEST
                || this == NORTH_EAST
                || this == SOUTH_WEST
                || this == SOUTH_EAST;
    }

    private boolean isHorizontal() {
        return this == EAST || this == WEST;
    }

    private boolean is(Position from, Position to) {
        return directionIdentifier.identify(from, to);
    }

    private interface DirectionIdentifier {
        boolean identify(Position from, Position to);
    }

    private static class StayIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return from.equals(to);
        }

    }
    private static class NorthIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (from.getX() == to.getX()) && (from.getY() < to.getY());
        }

    }
    private static class SouthIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (from.getX() == to.getX()) && (to.getY() < from.getY());
        }

    }
    private static class EastIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (from.getX() < to.getX()) && (to.getY() == from.getY());
        }

    }
    private static class WestIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (to.getX() < from.getX()) && (to.getY() == from.getY());
        }

    }
    private static class NorthEastIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (from.getX() < to.getX()) && (from.getY() < to.getY());
        }

    }
    private static class NorthWestIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (to.getX()) < from.getX() && (from.getY() < to.getY());
        }

    }
    private static class SouthEastIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (from.getX() < to.getX()) && (to.getY() < from.getY());
        }

    }
    private static class SouthWestIdentifier implements DirectionIdentifier {
        @Override
        public boolean identify(Position from, Position to) {
            return (to.getX() < from.getX()) && (to.getY() < from.getY());
        }

    }
}
