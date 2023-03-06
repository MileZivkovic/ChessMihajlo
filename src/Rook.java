import java.awt.*;

public class Rook extends Piece{
    public boolean hasNotMoved=true;

    public Rook(Image image, int x, int y, boolean isWhite, boolean isKing, boolean isRook, boolean isQueen, boolean isBishop, boolean isKnight, boolean isPawn) {
        super(image, x, y, isWhite, isKing, isRook, isQueen, isBishop, isKnight, isPawn);
    }

    public boolean isHasNotMoved() {
        return hasNotMoved;
    }

    public void setHasNotMoved(boolean hasNotMoved) {
        this.hasNotMoved = hasNotMoved;
    }

    @Override
    public boolean move(Point realeasedPosition) {
        int dx = Math.abs(currentPosition.x - realeasedPosition.x);
        int dy = Math.abs(currentPosition.y - realeasedPosition.y);
        if(dx==0||dy==0) {
            hasNotMoved=false;
            return true;
        }
        else
            return false;
    }
}
