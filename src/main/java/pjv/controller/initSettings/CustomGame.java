package pjv.controller.initSettings;

import javafx.stage.Stage;
import pjv.controller.MainGame;
import pjv.controller.Manipulator;
import pjv.pieces.*;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Class with constructor, which redirects user to game, and static public methods to be used from MainGame to create
 * and set pieces.
 */
public class CustomGame {
    private Stage stage;

    public CustomGame(Stage stage) {
        this.stage = stage;
    }

    public void doCustomGame(){
        Manipulator.setPlayers(1, 3, 2, 4);
        Manipulator.setPlayerOnTurnIndex(0);

        Manipulator.setTimeElapsed(0);

        Manipulator.setButtonClicked(null);

        Manipulator.setPickingPiecePhase(true);
        Manipulator.setMovingPiece(false);
        Manipulator.setReadyToMovePiece(false);
        Manipulator.setGuiDoMove(false);
        Manipulator.setRemovingDone(false);

        goToGame();
    }

    /**
     * Redirecting to game board.
     */
    private void goToGame(){
        Manipulator.setDefaultGameSettings(false);
        Manipulator.setCustomGame(true);

        MainGame game = new MainGame();
        game.start(stage);
    }


    public static void createPieces(){
        ArrayList<Piece> whitePieces = new ArrayList<Piece>();
        ArrayList<Piece> blackPieces = new ArrayList<Piece>();
        ArrayList<Piece> redPieces = new ArrayList<Piece>();
        ArrayList<Piece> bluePieces = new ArrayList<Piece>();

        Piece piece = null;


        for(String[] pieceInfo : Manipulator.getCreatedPieces()){
            if(pieceInfo[0].equals("pawn")){
                piece = new Pawn(Manipulator.getAllButtonPositions()[Integer.valueOf(pieceInfo[4])][Integer.valueOf(pieceInfo[5])],
                        Integer.valueOf(pieceInfo[1]));
            } else if(pieceInfo[0].equals("rook")){
                piece = new Rook(Manipulator.getAllButtonPositions()[Integer.valueOf(pieceInfo[4])][Integer.valueOf(pieceInfo[5])],
                        Integer.valueOf(pieceInfo[1]));
            } else if(pieceInfo[0].equals("knight")){
                piece = new Knight(Manipulator.getAllButtonPositions()[Integer.valueOf(pieceInfo[4])][Integer.valueOf(pieceInfo[5])],
                        Integer.valueOf(pieceInfo[1]));
            } else if(pieceInfo[0].equals("bishop")){
                piece = new Bishop(Manipulator.getAllButtonPositions()[Integer.valueOf(pieceInfo[4])][Integer.valueOf(pieceInfo[5])],
                        Integer.valueOf(pieceInfo[1]));
            } else if(pieceInfo[0].equals("queen")){
                piece = new Queen(Manipulator.getAllButtonPositions()[Integer.valueOf(pieceInfo[4])][Integer.valueOf(pieceInfo[5])],
                        Integer.valueOf(pieceInfo[1]));
            } else if(pieceInfo[0].equals("king")) {
                piece = new King(Manipulator.getAllButtonPositions()[Integer.valueOf(pieceInfo[4])][Integer.valueOf(pieceInfo[5])],
                        Integer.valueOf(pieceInfo[1]));
            }

            switch (piece.getPlayerColor()){
                case 1: whitePieces.add(piece);
                    break;
                case 2: blackPieces.add(piece);
                    break;
                case 3: redPieces.add(piece);
                    break;
                case 4: bluePieces.add(piece);
                    break;
            }
        }
        Manipulator.setWhitePieces(whitePieces);
        Manipulator.setBlackPieces(blackPieces);
        Manipulator.setRedPieces(redPieces);
        Manipulator.setBluePieces(bluePieces);
    }

    public static void setPiecesIcons(){
        for(int i = 0; i < Manipulator.getPlayers().size(); i++){
            for(Piece piece : Manipulator.getPlayerPieces(Manipulator.getPlayers().get(i))){
                piece.getPosition().setPlayerColor(piece.getPlayerColor());
                piece.getPosition().setImage(piece.getPathToIcon());
            }
        }
    }
}
