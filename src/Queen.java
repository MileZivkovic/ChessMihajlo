import java.awt.*;

public class Queen extends Piece{
    public Queen(Image image, int x, int y, boolean isWhite, boolean isKing, boolean isRook, boolean isQueen, boolean isBishop, boolean isKnight, boolean isPawn) {
        super(image, x, y, isWhite, isKing, isRook, isQueen, isBishop, isKnight, isPawn);
    }

    @Override
    public boolean move(Point realeasedPosition) {
        int dx = Math.abs(currentPosition.x - realeasedPosition.x);
        int dy = Math.abs(currentPosition.y - realeasedPosition.y);
        if (dx == 0 || dy == 0) {
            return true;
        }
        return dx == dy;
    }

}
