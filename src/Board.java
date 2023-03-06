import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private String FEN;
    public static int [][] grid=new int[8][8];
    public static int [][] kingCheckBoard=new int[8][8];
    public static HashMap<Point,Piece> piecePointHashMap=new HashMap<>();
    public static boolean isWhitesTurn=true;
    JFrame frame=new JFrame();
    public static JPanel board=new JPanel();
    ArrayList<Image> photos=new ArrayList<>();

    public Board(String FEN) {
        this.FEN = FEN;
        makePhotos(photos);
        frameSettings(frame);
        board=makeBoard(frame,board);
        readFEN();
        board.setLayout(null);
    }
    public void readFEN(){
        int rank = 0;
        int file = 0;
        for(int i = 0; i < FEN.length(); ++i) {
            char c = FEN.charAt(i);
            if (c == '/') {
                ++rank;
                file = -1;
            } else if (Character.isDigit(c)) {
                int emptySquares = Character.getNumericValue(c);
                file += emptySquares - 1;
            }

            boolean isWhite = Character.isUpperCase(c);
            c = Character.toLowerCase(c);
            switch (c) {
                case 'b' -> board.add(new Bishop(photos.get(isWhite ? 3 : 9), file, rank, isWhite,false,false,false,true,false,false));
                case 'k' -> board.add(new King(photos.get(isWhite ? 0 : 6), file, rank, isWhite,true,false,false,false,false,false));
                case 'n' -> board.add(new Knight(photos.get(isWhite ? 4 : 10), file, rank, isWhite,false,false,false,false,true,false));
                case 'p' -> board.add(new Pawn(photos.get(isWhite ? 5 : 11), file, rank, isWhite,false,false,false,false,false,true));
                case 'q' -> board.add(new Queen(photos.get(isWhite ? 1 : 7), file, rank, isWhite,false,false,true,false,false,false));
                case 'r' -> board.add(new Rook(photos.get(isWhite ? 2 : 8), file, rank, isWhite,false,true,false,false,false,false));
            }

            ++file;
        }

    }

    public void makePhotos(ArrayList<Image> photos){
        String [] links={"src/kralj.png","src/kraljica.png","src/top.png","src/lovac.png","src/konj.png","src/pijun.png","src/crnikralj.png","src/crnakraljica.png",
                "src/crnitop.png","src/crnilovac.png",
                "src/crnikonj.png","src/crnipijun.png"};
        for (String link:links) {
            {
                try{
                    Image i= ImageIO.read(new File(link));
                    photos.add(i);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }


            }

        }
    }
    public JPanel makeBoard(JFrame frame, JPanel panel){
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                boolean white = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (white) {
                            g.setColor(Color.white.darker());
                        } else {
                            g.setColor(Color.darkGray);
                        }
                        g.fillRect(i * 64, j * 64, 64, 64);
                        white = !white;

                    }
                    white = !white;
                }
            }
        };
        panel.setPreferredSize(frame.getSize());
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        return panel;
    }
    public void frameSettings(JFrame frame){


        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setBounds(10,10,512,512);
        frame.setMinimumSize(new Dimension(512,512));
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }}
    /*public static void createMatrix(int[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece point = piecePointHashMap.get(new Point(i * 64, j * 64));
                if (point == null) {
                    board[i][j] = 0; // empty cell
                } else {
                    boolean isWhite = point.isWhite;
                    int color = isWhite ? 1 : -1;
                    if (point.isPawn) {
                        board[i][j] = color; // pawn
                    } else if (point.isKnight) {
                        board[i][j] = color * 2; // knight
                    } else if (point.isBishop) {
                        board[i][j] = color * 3; // bishop
                    } else if (point.isRook) {
                        board[i][j] = color * 4; // rook
                    } else if (point.isQueen) {
                        board[i][j] = color * 5; // queen
                    } else if (point.isKing) {
                        board[i][j] = color * 6; // king
                    }
                }
            }
        }
    }}*/
