package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    protected final Color color;
    protected final char representation;
    protected final double defaultPoint;

    public Piece(Color color, char representation, double defaultPoint) {
        this.color = color;
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }

    public char getRepresentation() {
        return isWhite() ? representation : Character.toUpperCase(representation);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public abstract boolean isNotBlank();

    public boolean isEqual(Color color) {
        return this.color == color;
    }

    public List<Position> findMovablePosition(String sourcePosition) {
        Position position = Position.convert(sourcePosition);
        List<Position> positions = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (!isPiecesDirection(direction)) {
                continue;
            }
            findNextPositions(position, direction, positions);
        }
        return positions;
    }

    protected abstract boolean isPiecesDirection(Direction direction);

    protected void findNextPositions(Position prevPosition, Direction direction, List<Position> positions) {
        if (Position.canNotMove(prevPosition.col() + direction.col, prevPosition.row() + direction.row)) {
            return;
        }
        Position nextPosition = new Position(prevPosition.col() + direction.col, prevPosition.row() + direction.row);
        positions.add(nextPosition);
        findNextPositions(nextPosition, direction, positions);
    }

    public void checkSameColor(Piece targetPositionPiece) {
        if (color != targetPositionPiece.color) {
            return;
        }
        throw new IllegalArgumentException("현재 위치와 이동 위치의 기물이 같은 편입니다.");
    }

    public final boolean isPawn(Color color) {
        return isPawn() && this.color == color;
    }

    public final boolean isPawn() {
        return this instanceof Pawn;
    }

    public final boolean isKnight() {
        return this instanceof Knight;
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }

    public String getColor() {
        return color.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return representation == piece.representation && Double.compare(defaultPoint, piece.defaultPoint) == 0
                && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation, defaultPoint);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", representation=" + representation +
                ", defaultPoint=" + defaultPoint +
                '}';
    }
}
