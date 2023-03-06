import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece extends JPanel {
    private Image image;
    public boolean isWhite;
    public Point currentPosition=new Point();
    public Point realeasedPosition=new Point();
    public boolean hasNotMoved;
    public boolean isKing;
    public boolean isRook;
    public boolean isPawn;
    public boolean isQueen;
    public boolean isBishop;
    public boolean isKnight;


    public Piece(Image image, int x, int y,boolean isWhite,boolean isKing,boolean isRook,boolean isQueen,boolean isBishop,boolean isKnight,boolean isPawn) {
        makeDraggable();


        this.image = image;
        this.isWhite=isWhite;
        Board.grid[y][x]=isWhite?1:2;
        this.setBackground(new Color(0,0,0,0));
        this.setPreferredSize(new Dimension(64,64));
        this.setMinimumSize(new Dimension(64,64));
        this.setMaximumSize(this.getPreferredSize());
        this.setBounds(x*64,y*64,64,64);
        hasNotMoved=true;
        Board.piecePointHashMap.put(new Point(getX(),getY()),this);
        this.isKing=isKing;
        this.isRook=isRook;
        this.isPawn=isPawn;
        this.isQueen=isQueen;
        this.isBishop=isBishop;
        this.isKnight=isKnight;


    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point getRealeasedPosition() {
        return realeasedPosition;
    }

    public void setRealeasedPosition(Point realeasedPosition) {
        this.realeasedPosition = realeasedPosition;
    }


    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public boolean isRook() {
        return isRook;
    }

    public void setRook(boolean rook) {
        isRook = rook;
    }

    public boolean isPawn() {
        return isPawn;
    }

    public void setPawn(boolean pawn) {
        isPawn = pawn;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public boolean isBishop() {
        return isBishop;
    }

    public void setBishop(boolean bishop) {
        isBishop = bishop;
    }

    public boolean isKnight() {
        return isKnight;
    }

    public void setKnight(boolean knight) {
        isKnight = knight;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public Piece getPiece(){
        return this;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image,7,7,50,50,null);

    }
    private void makeDraggable() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                currentPosition=getLocation();

            }

            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                realeasedPosition.setLocation(Math.round((float)getBounds().getX()/(float)64)*64,(int)Math.round((float)getBounds().getY()/(float)64)*64);
                if(move(realeasedPosition)&&inTheWay(realeasedPosition,currentPosition)&&Board.isWhitesTurn==getPiece().isWhite)
                {

                     if(Board.grid[currentPosition.y/64][currentPosition.x/64]==Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]){
                        setLocation(currentPosition);
                    }
                    if(Board.grid[currentPosition.y/64][currentPosition.x/64]!=Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]&&Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]!=0){
                        Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]=Board.grid[currentPosition.y/64][currentPosition.x/64];
                        Board.grid[currentPosition.y/64][currentPosition.x/64]=0;
                        Board.board.remove(Board.piecePointHashMap.get(realeasedPosition));
                        Board.piecePointHashMap.put(realeasedPosition,getPiece());
                        Board.piecePointHashMap.put(currentPosition,null);
                        setLocation(realeasedPosition);
                        currentPosition.setLocation(realeasedPosition);
                        System.out.println(realeasedPosition);
                        Board.isWhitesTurn=!Board.isWhitesTurn;


                    }
                    if(Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]==0){
                        Board.grid[realeasedPosition.y/64][realeasedPosition.x/64]=Board.grid[currentPosition.y/64][currentPosition.x/64];
                        Board.grid[currentPosition.y/64][currentPosition.x/64]=0;
                        Board.piecePointHashMap.put(realeasedPosition,getPiece());
                        Board.piecePointHashMap.put(currentPosition,null);
                        setLocation(realeasedPosition);
                        currentPosition.setLocation(realeasedPosition);
                        System.out.println(realeasedPosition);
                        Board.isWhitesTurn=!Board.isWhitesTurn;
                    }
                }

                else
                    setLocation(currentPosition);


            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                getPiece().getParent().setComponentZOrder(getPiece(),0);
                if(inBounds(e))
                {
                    setLocation(getX() + e.getX() - getWidth() / 2, getY() + e.getY() - getHeight() / 2);

                }



            }
        });

    }
    private boolean inBounds(MouseEvent e){
        return this.getX()+e.getX() >= 5 && this.getX()+e.getX() <= 512-5 && this.getY()+e.getY() >= 5 && this.getY() +e.getY()<= 512-5;

    }
    public abstract boolean move(Point realeasedPosition);
    public boolean inTheWay(Point realeasedPosition,Point currentPosition){
        int dx = Math.abs(realeasedPosition.x - currentPosition.x)/64;
        int dy = Math.abs(realeasedPosition.y - currentPosition.y)/64;
        if(dx==2&&dy==0&&isKing)
            return true;
        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            return true;
        }
        if(currentPosition.x==realeasedPosition.x){
            if(realeasedPosition.y>currentPosition.y){
                for(int i=1;i<Math.abs((currentPosition.y-realeasedPosition.y)/64);i++){
                       if(Board.grid[currentPosition.y/64+i][realeasedPosition.x/64]!=0) return false;

                }
            }
            if(realeasedPosition.y<currentPosition.y){
                for(int i=1;i<Math.abs((currentPosition.y-realeasedPosition.y)/64);i++){
                    if(Board.grid[currentPosition.y/64-i][realeasedPosition.x/64]!=0) return false;

                }
            }
        }
        if (currentPosition.y == realeasedPosition.y) {
            int start = Math.min(currentPosition.x, realeasedPosition.x) / 64;
            int end = Math.max(currentPosition.x, realeasedPosition.x) / 64;
            for (int i = start + 1; i < end; i++) {
                if (Board.grid[currentPosition.y / 64][i] != 0) {
                    return false;
                }
            }
        }
        if(currentPosition.y!=realeasedPosition.y&&currentPosition.x!=realeasedPosition.x){
            if(currentPosition.y> realeasedPosition.y&&currentPosition.x> realeasedPosition.x){
                for(int i=1;i<Math.abs(currentPosition.x- realeasedPosition.x)/64;i++){
                    if(Board.grid[currentPosition.y/64-i][currentPosition.x/64-i]!=0)
                     return false;
                }
            }
            if(currentPosition.y> realeasedPosition.y&&currentPosition.x< realeasedPosition.x){
                for(int i=1;i<Math.abs(currentPosition.x- realeasedPosition.x)/64;i++){
                    if(Board.grid[currentPosition.y/64-i][currentPosition.x/64+i]!=0)
                        return false;
                }
            }
            if(currentPosition.y< realeasedPosition.y&&currentPosition.x< realeasedPosition.x){
                for(int i=1;i<Math.abs(currentPosition.x- realeasedPosition.x)/64;i++){
                    if(Board.grid[currentPosition.y/64+i][currentPosition.x/64+i]!=0)
                        return false;
                }
            }
            if(currentPosition.y< realeasedPosition.y&&currentPosition.x> realeasedPosition.x){
                for(int i=1;i<Math.abs(currentPosition.x- realeasedPosition.x)/64;i++){
                    if(Board.grid[currentPosition.y/64+i][currentPosition.x/64-i]!=0)
                        return false;
                }
            }
        }
        return true;

    }}
   /* public static boolean isKingInCheck(int[][] board, int kingValue) {
        // Find the location of the king
        int[] kingPosition = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == kingValue) {
                    kingPosition = new int[] {i, j};
                    break;
                }
            }
            if (kingPosition != null) {
                break;
            }
        }
        if (kingPosition == null) {
            throw new IllegalArgumentException("King not found on the board.");
        }

        // Check if any other piece is attacking the king
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int pieceValue = board[i][j];
                if (pieceValue == 0 || pieceValue == kingValue) {
                    continue; // skip empty cells and the king's position
                }
                boolean isAttacking = isAttackingKing(board, i, j, kingPosition[0], kingPosition[1]);
                if (isAttacking) {
                    return true; // king is in check
                }
            }
        }

        // King is not in check
        return false;
    }

    private static boolean isAttackingKing(int[][] board, int pieceRow, int pieceCol, int kingRow, int kingCol) {
        // Check if the piece can legally move to the cell occupied by the king
        // This depends on the type of the piece, which can be determined by its value
        int pieceValue = board[pieceRow][pieceCol];
        int dr = kingRow - pieceRow;
        int dc = kingCol - pieceCol;

        if (Math.abs(pieceValue) == 1) { // pawn
            int pawnDir = (pieceValue > 0) ? 1 : -1;
            return (dr == pawnDir && Math.abs(dc) == 1);
        } else if (Math.abs(pieceValue) == 2) { // knight
            return ((Math.abs(dr) == 1 && Math.abs(dc) == 2) ||
                    (Math.abs(dr) == 2 && Math.abs(dc) == 1));
        } else if (Math.abs(pieceValue) == 3) { // bishop
            return (Math.abs(dr) == Math.abs(dc) && isPathClear(board, pieceRow, pieceCol, kingRow, kingCol));
        } else if (Math.abs(pieceValue) == 4) { // rook
            return ((dr == 0 || dc == 0) && isPathClear(board, pieceRow, pieceCol, kingRow, kingCol));
        } else if (Math.abs(pieceValue) == 5) { // queen
            return ((dr == 0 || dc == 0 || Math.abs(dr) == Math.abs(dc)) &&
                    isPathClear(board, pieceRow, pieceCol, kingRow, kingCol));
        } else { // invalid piece value
            throw new IllegalArgumentException("Invalid piece value: " + pieceValue);
        }
    }
    private static boolean isPathClear(int[][] board, int startRow, int startCol, int endRow, int endCol) {
// Check if the path between two cells is clear for a bishop, rook, or queen
        int dr = Integer.compare(endRow, startRow);
        int dc = Integer.compare(endCol, startCol);
        int row = startRow + dr;
        int col = startCol + dc;
        while (row != endRow || col != endCol) {
            if (board[row][col] != 0) {
                return false; // there is a piece in the way
            }
            row += dr;
            col += dc;
        }
        return true; // the path is clear
    }
   private int whoIsIt(){
    if(Board.isWhitesTurn)
        return -6;
    else return 6;
   }}*/


