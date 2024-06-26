package com.seong.chess.pieces;

public class Bishop extends Piece {

    private static final char REPRESENTATION = 'b';
    private static final double DEFAULT_POINT = 3.0;

    private Bishop(Color color) {
        super(Type.BISHOP, color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Bishop createBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop createWhite() {
        return new Bishop(Color.WHITE);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }
}
