import java.awt.*;

public class Pawn extends Piece{
    public Pawn(Image image, int x, int y, boolean isWhite, boolean isKing, boolean isRook, boolean isQueen, boolean isBishop, boolean isKnight, boolean isPawn) {
        super(image, x, y, isWhite, isKing, isRook, isQueen, isBishop, isKnight, isPawn);
    }

    @Override
    public boolean move(Point realeasedPosition) {
        int dx = Math.abs(currentPosition.x - realeasedPosition.x)/64;
        int dy = Math.abs(currentPosition.y - realeasedPosition.y)/64;
        int dx1=(currentPosition.x - realeasedPosition.x)/64;
        int dy1=(currentPosition.y - realeasedPosition.y)/64;
        if(currentPosition.getY()/64==1&&!isWhite&&dy==2)
            return true;
        if(currentPosition.getY()/64==6&&isWhite&&dy==2)
            return true;
        if(isWhite&&dy1==1&&dx==0&&Board.grid[currentPosition.y/64-1][currentPosition.x/64]!=2)
            return true;
        if(!isWhite&&dy1==-1&&dx==0&&Board.grid[currentPosition.y/64+1][currentPosition.x/64]!=1)
            return true;
        if(!isWhite&&(dy1==-1&&dx1==-1||dy1==-1&&dx1==1)&&Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]==1)
            return true;
        if(isWhite&&(dy1==1&&dx1==-1||dy1==1&&dx1==1)&&Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]==2)
            return true;
        return false;




    }
}
