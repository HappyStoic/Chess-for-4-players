package pjv.controller.initSettings;

import javafx.stage.Stage;
import pjv.controller.GameInfo;
import pjv.controller.Manipulator;
import pjv.gui.game.ButtonPosition;
import pjv.pieces.Piece;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class LoadGame{
    Stage stage;

    /**
     * File where saved game is stored.
     */
    private File file;

    /**
     * Variable to store in read object
     */
    private GameInfo loadedStatus;

    public LoadGame(Stage stage, File file) {
        this.stage = stage;
        this.file = file;
    }

    public void load() throws Exception{
        getGameInfo();
        setLoadedInfo();

        Manipulator.setDefaultGameSettings(false);
    }


    private void getGameInfo() throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        loadedStatus = (GameInfo) in.readObject();
        in.close();
    }

    private void setLoadedInfo(){
        Manipulator.setPlayers(loadedStatus.getPlayers());
        Manipulator.setPlayerOnTurnIndex(loadedStatus.getPlayerOnTurnIndex());

        Manipulator.setTimeElapsed(loadedStatus.getTimeElapsed());

        Manipulator.setButtonClicked(null);

        Manipulator.setPickingPiecePhase(true);
        Manipulator.setMovingPiece(false);
        Manipulator.setReadyToMovePiece(false);
        Manipulator.setGuiDoMove(false);
        Manipulator.setRemovingDone(false);

        Manipulator.setWhitePieces(loadedStatus.getWhitePieces());
        Manipulator.setBlackPieces(loadedStatus.getBlackPieces());
        Manipulator.setRedPieces(loadedStatus.getRedPieces());
        Manipulator.setBluePieces(loadedStatus.getBluePieces());
    }

    /**
     * Static method to set piece icons and piece position (used from GameInfo, because it needs initialised ButtonPositions)
     */
    public static void setPiecesIcons(){
        for(int i = 0; i < Manipulator.getPlayers().size(); i++){
            for(Piece piece : Manipulator.getPlayerPieces(Manipulator.getPlayers().get(i))){
                ButtonPosition oldbtn = piece.getPosition();
                ButtonPosition newbtn = Manipulator.getAllButtonPositions()[oldbtn.getRowIndex()][oldbtn.getColumnIndex()];

                piece.setPosition(newbtn);
                newbtn.setImage(piece.getPathToIcon());
                newbtn.setPlayerColor(piece.getPlayerColor());

            }
        }
    }
}
