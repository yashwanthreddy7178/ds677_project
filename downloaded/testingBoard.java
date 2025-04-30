package GUI;

import Board.Move;
import Board.gameGrid;
import Pieces.Piece;
import Utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static Pieces.Alliance.BLACK;
import static Pieces.Alliance.WHITE;

public class testingBoard extends JFrame{
    private static JButton[][] tiles = new JButton[8][8];
    private clickedTile pieceTile;
    protected static Piece pieceAtDestination = null;
    private Color tileShade = new Color(139, 69, 19);
    private gameGrid g = new gameGrid();


    public testingBoard() {
        super("Chess!");
        JPanel contents = new JPanel();
        contents.setLayout(new GridLayout(8, 8));
        ButtonHandler handler = new ButtonHandler();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new JButton();
                if ((i + j) % 2 != 0) {
                    tiles[i][j].setBackground(tileShade);
                }else{
                    tiles[i][j].setBackground(Color.WHITE);
                }
                contents.add(tiles[i][j]);
                tiles[i][j].addActionListener(handler);
            }
        }
        add(contents, BorderLayout.CENTER);
        setUpPieces();
        setSize(1000, 1000);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void setUpPieces(){
        setPiece(35, dataUtil.blackKing);
        setPiece(36, dataUtil.whiteRook);
        setPiece(34, dataUtil.whiteRook);
        setPiece(43, dataUtil.whiteRook);
        setPiece(26, dataUtil.whiteRook);
        setPiece(27, dataUtil.whiteRook);
        setPiece(28, dataUtil.whiteRook);
        setPiece(42, dataUtil.whiteRook);
        setPiece(52, dataUtil.whitePawn);
        setPiece(4, dataUtil.whitePawn);
        setPiece(58, dataUtil.blackPawn);
        setPiece(43, dataUtil.whitePawn);
        setPiece(23, dataUtil.blackQueen);
        gridUtil.printGrid(g);
    }

    private void setPiece(int location, ImageIcon img) {
        ArrayList<Integer> loc = linkEngineToGui.arrayListToArray(location);
        int row = loc.get(0);
        int col = loc.get(1);
        tiles[row][col].setIcon(img);
        pieceUtil.addPiece(g, img, location);
    }

    private boolean isValidMove(int i, int j) {
        int finalPos = linkEngineToGui.arrayToArrayList(i, j);
        int currentPos;
        try {
            currentPos = pieceTile.piece.location;
        }catch (NullPointerException e){
            currentPos = 0;
        }

        return pieceTile.piece.getLegalMoves(g).contains((currentPos - finalPos) * -1);
    }

    private void processClick(int i, int j) {
        if (!isValidMove(i, j)) {
            pieceTile = null;
            return;
        }
        tiles[pieceTile.row][pieceTile.col].setIcon(null);
        tiles[i][j].setIcon(pieceTile.img);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(pieceTile == null) {
                Object source = e.getSource();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (source == tiles[i][j]) {
                            if(g.emptyPiecesGrid.get(linkEngineToGui.arrayToArrayList(i, j)).alliance == BLACK) {
                                pieceTile = clickedTile.assignClickedTile(linkEngineToGui.arrayToArrayList(i, j), tiles[i][j]);
                                try {
                                    highlightValidMove(pieceTile.piece);
                                }catch (Exception ex) {}
                            }else if(g.emptyPiecesGrid.get(linkEngineToGui.arrayToArrayList(i, j)).alliance == WHITE){
                                pieceTile = clickedTile.assignClickedTile(linkEngineToGui.arrayToArrayList(i, j), tiles[i][j]);
                                try {
                                    highlightValidMove(pieceTile.piece);
                                }catch (Exception ex) {}
                            }
                        }
                    }
                }
            }else{
                Object source = e.getSource();
                int iLoc=0, jLoc=0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (source == tiles[i][j]) {
                            pieceAtDestination = gridUtil.findPieceFromLocation(linkEngineToGui.arrayToArrayList(i, j), g);
                            processClick(i, j);
                            iLoc = i;
                            jLoc = j;
                            removeHighlight();
                        }
                    }
                }
                try {
                    Move move = new Move(linkEngineToGui.arrayToArrayList(iLoc, jLoc), pieceTile.piece);
                    g.emptyPiecesGrid = move.normalMove(g);
                    gridUtil.printGrid(g);
                }catch(Exception exc){}
                pieceTile = null;
            }
        }

        private void highlightValidMove(Piece piece){
            for(int currentLegalMove : piece.getLegalMoves(g)){
                int currentLoc = piece.location + currentLegalMove;
                int row = linkEngineToGui.arrayListToArray(currentLoc).get(0);
                int col = linkEngineToGui.arrayListToArray(currentLoc).get(1);
                tiles[row][col].setBackground(Color.GREEN);
            }
        }
        private void removeHighlight(){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 != 0) {
                        tiles[i][j].setBackground(tileShade);
                    }else{
                        tiles[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        }
    }
}
