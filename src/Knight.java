import java.awt.*;

public class Knight extends Piece{
    public Knight(Image image, int x, int y, boolean isWhite, boolean isKing, boolean isRook, boolean isQueen, boolean isBishop, boolean isKnight, boolean isPawn) {
        super(image, x, y, isWhite, isKing, isRook, isQueen, isBishop, isKnight, isPawn);
    }

    @Override
    public boolean move(Point realeasedPosition) {
        int dx = Math.abs(currentPosition.x - realeasedPosition.x)/64;
        int dy = Math.abs(currentPosition.y - realeasedPosition.y)/64;
        if (!((dx == 1 && dy == 2) || (dx == 2 && dy == 1))) {
            return false;
        }
        return true;
    }
}
