package chess.domain.piece.position;

import java.util.Objects;

public class Distance {
    private final double value;

    private Distance(double value) {
        this.value = Math.abs(value);
    }


    static Distance of(double value) {
        return new Distance(value);
    }

    static Distance of(double xDifference, double yDifference) {
        return new Distance(Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2)));
    }

    static Distance calculate(Position from, Position to) {
        int xDifference = from.getX() - to.getX();
        int yDifference = from.getY() - to.getY();
        double calculated = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
        return new Distance(calculated);
    }

    static Distance calculateVertical(Position from, Position to) {
        int calculated = Math.abs(from.getY() - to.getY());
        return new Distance(calculated);
    }

    static Distance calculateHorizontal(Position from, Position to) {
        int calculated = Math.abs(from.getX() - to.getX());
        return new Distance(calculated);
    }

    public double getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Double.compare(distance.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isBiggerThan(double value) {
        return value < this.value;
    }
}
