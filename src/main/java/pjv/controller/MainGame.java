package pjv.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pjv.controller.initSettings.CustomGame;
import pjv.controller.initSettings.DefaultGame;
import pjv.controller.initSettings.LoadGame;
import pjv.gui.game.*;
import pjv.pieces.Piece;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX application with game phase.
 */
public class MainGame extends Application {
    private static final Logger LOG = Logger.getLogger(MainMenu.class.getName());

    private BorderPane root;
    private ChessBoardGui center;
    private TopToolBar topToolBar;


    @Override
    public void start(Stage stage) {
        LOG.info("Game board is being initialised.");

        initUI(stage);
    }


    private void initUI(Stage stage) {

        root = new BorderPane();
        center = new ChessBoardGui();
        topToolBar = new TopToolBar(stage);

        root.setTop(topToolBar);
        root.setCenter(center);
        root.setBottom(new BottomNumbers());
        root.setRight(new RightLetters());

        Scene scene = new Scene(root, 14 * ButtonPosition.HEIGHT_BUTTON + 2 * ChessBoardGui.DEFAULT_PADDING + 50,
                14 * ButtonPosition.WIDTH_BUTTON + 2 * ChessBoardGui.DEFAULT_PADDING + 110);
        // 50 represents width of right Panel | 110 represents sum of height of top toolbar and bottom numbers.

        stage.setTitle("4 players chess");
        stage.setResizable(false);


        Manipulator.setAllButtonPositions(center.getAllButtonPositions());

        if(Manipulator.getDefaultGameSettings()) {       //NEW DEFAULT GAME
            initNewDefaultGame();
        } else if (Manipulator.getCustomGame()){        //CUSTOM GAME
            CustomGame.createPieces();
            CustomGame.setPiecesIcons();
        } else {                        //LOADED GAME
            LoadGame.setPiecesIcons(); //
        }
        stage.setScene(scene);
        stage.show();

        startClockTimer();
        startBackendThread();
        startTaskToMoveIcons();
        LOG.info("All threads launched.");
    }



    private void startBackendThread() {
        BackendThread backendThread = new BackendThread(Thread.currentThread());
        Thread thread = new Thread(backendThread);

        Manipulator.setBackendThread(thread);

        thread.setDaemon(true);
        thread.start();
    }


    /**
     * Timer dealing with updating gui interface. It's launched every 50 ms.
     */
    private void startTaskToMoveIcons(){
        final Timeline threadMovingIcons = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                //End game
                if(!Manipulator.getBackendThread().isAlive() && Manipulator.getEndGameString() != null){
                    topToolBar.getOnTurnLabel().setText(Manipulator.getEndGameString());
                    Manipulator.getClockThread().stop();
                    Manipulator.getMovingIconsThread().stop();
                //Not end game. Updating who's on turn.
                } else {
                    topToolBar.getOnTurnLabel().setText("On turn: " + Manipulator.getLiteralColorOnTurn());
                }

                //Gui needs update
                if(Manipulator.getGuiDoMove()){

                    Manipulator.getLeavingButtonPosition().setGraphic(null);
                    Manipulator.getEnteringButtonPosition().setImage(Manipulator.getPieceClicked().getPathToIcon());

                    Manipulator.setGuiDoMove(false);
                }

                //There is team to be removed.
                if(Manipulator.getTeamToRemove() != null){

                    ArrayList<Piece> piecesToRemove = Manipulator.getPlayerPieces(Manipulator.getTeamToRemove());
                    for(Piece piece : piecesToRemove){
                        piece.getPosition().setGraphic(null);
                        piece.getPosition().setPlayerColor(null);
                    }
                    piecesToRemove.clear();
                    Manipulator.getPlayers().remove(Manipulator.getTeamToRemove());

                    Manipulator.setRemovingDone(true);
                    Manipulator.setTeamToRemove(null);
                }

            }
        }));
        threadMovingIcons.setCycleCount(Timeline.INDEFINITE);       //There is infinite number of cycles
        threadMovingIcons.play();         //Starting timer

        Manipulator.setMovingIconsThread(threadMovingIcons);
    }

    /**
     * Timer dealing with updating clock. It's launched every second.
     */
    private void startClockTimer(){
        Timeline clockThread = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {


                int elapsed = Manipulator.getTimeElapsed();
                int hours = elapsed / 3600;
                int minutes = (elapsed - hours*3600) / 60;
                int seconds = elapsed % 60;

                String result = String.format("%d:%02d:%02d", hours, minutes, seconds);

                topToolBar.getClockLabel().setText(result);

                elapsed++;
                Manipulator.setTimeElapsed(elapsed);
            }
        }));
        clockThread.setCycleCount(Timeline.INDEFINITE);     //There is infinite number of cycles
        clockThread.play();                 //Starting timer

        Manipulator.setClockThread(clockThread);
    }

    private void initNewDefaultGame() {
        center.setIconsNewDefaultGame();

        Manipulator.setPlayers(1, 3, 2, 4);
        Manipulator.setPlayerOnTurnIndex(0);

        Manipulator.setTimeElapsed(0);

        Manipulator.setButtonClicked(null);

        Manipulator.setPickingPiecePhase(true);
        Manipulator.setMovingPiece(false);
        Manipulator.setReadyToMovePiece(false);
        Manipulator.setGuiDoMove(false);
        Manipulator.setRemovingDone(false);

        DefaultGame.createDefaultWhitePieces();
        DefaultGame.createDefaultBlackPieces();
        DefaultGame.createDefaultRedPieces();
        DefaultGame.createDefaultBluePieces();

        DefaultGame.setDefaultWhiteOwnership();
        DefaultGame.setDefaultBlackOwnership();
        DefaultGame.setDefaultRedOwnership();
        DefaultGame.setDefaultBlueOwnership();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
