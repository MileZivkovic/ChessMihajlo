import java.awt.*;

public class King extends Piece{
    public boolean hasNotMoved=true;

    public King(Image image, int x, int y, boolean isWhite, boolean isKing, boolean isRook, boolean isQueen, boolean isBishop, boolean isKnight, boolean isPawn) {
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
        int dx=(currentPosition.x- realeasedPosition.x)/64;
        int dy=(currentPosition.y- realeasedPosition.y)/64;
        int x=currentPosition.x/64;
        int y=currentPosition.y/64;
        Piece whiteRightRook=Board.piecePointHashMap.get(new Point(448,448));
        Piece whiteLeftRook=Board.piecePointHashMap.get(new Point(0,448));
        Piece blackRightRook=Board.piecePointHashMap.get(new Point(448,0));
        Piece blackLeftRook=Board.piecePointHashMap.get(new Point(0,0));
        if(dx==-2&&dy==0&&hasNotMoved){
            if(whiteRightRook!=null&&whiteRightRook.isRook&&whiteRightRook.hasNotMoved&&inTheWay(currentPosition,new Point(currentPosition.x+128, currentPosition.y))){
                       if(Board.grid[y][x+1]==0&&Board.grid[y][x+2]==0){
                           Board.grid[whiteRightRook.getY()/64][whiteRightRook.getX()/64]=0;
                           Board.grid[whiteRightRook.getY()/64][whiteRightRook.getX()/64-2]=1;
                           Board.piecePointHashMap.put(whiteRightRook.getLocation(),null);
                           Board.piecePointHashMap.put(new Point(currentPosition.x+64, currentPosition.y),whiteRightRook);
                           whiteRightRook.setLocation(currentPosition.x+64, currentPosition.y);
                           hasNotMoved=false;
                           return true;

                       }
            }
            if(blackRightRook!=null&&blackRightRook.isRook&& blackRightRook.hasNotMoved&&inTheWay(currentPosition,new Point(currentPosition.x+128, currentPosition.y))){
                if(Board.grid[y][x+1]==0&&Board.grid[y][x+2]==0){
                    Board.grid[blackRightRook.getY()/64][blackRightRook.getX()/64]=0;
                    Board.grid[blackRightRook.getY()/64][blackRightRook.getX()/64-2]=2;
                    Board.piecePointHashMap.put(blackRightRook.getLocation(),null);
                    Board.piecePointHashMap.put(new Point(currentPosition.x+64, currentPosition.y),blackRightRook);
                    blackRightRook.setLocation(currentPosition.x+64, currentPosition.y);
                    hasNotMoved=false;
                    return true;
                }
            }



        }
        if(currentPosition.getX() == realeasedPosition.getX() && Math.abs(currentPosition.getY() - realeasedPosition.getY()) == 64 ||
                currentPosition.getY() == realeasedPosition.getY() && Math.abs(currentPosition.getX() -
                        realeasedPosition.getX()) == 64 || Math.abs(currentPosition.getY() -
                realeasedPosition.getY()) == 64 && Math.abs(currentPosition.getX() - realeasedPosition.getX()) == 64)
        {
            hasNotMoved=false;
            return true;

        }
        return false;

    }
}
