import java.awt.*;

public class Bishop extends Piece{
    public Bishop(Image image, int x, int y, boolean isWhite, boolean isKing, boolean isRook, boolean isQueen, boolean isBishop, boolean isKnight, boolean isPawn) {
        super(image, x, y, isWhite, isKing, isRook, isQueen, isBishop, isKnight, isPawn);
    }

    @Override
    public boolean move(Point realeasedPosition) {
        int dx = Math.abs(currentPosition.x - realeasedPosition.x);
        int dy = Math.abs(currentPosition.y - realeasedPosition.y);
        return dx==dy;
    }

}
