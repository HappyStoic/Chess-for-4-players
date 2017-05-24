package pjv.gui.menu;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ECMAException;
import pjv.controller.MainGame;
import pjv.controller.Manipulator;
import pjv.controller.initSettings.LoadGame;
import pjv.pieces.Piece;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Main menu wrote in javaFX. It consists of 4 buttons - New default game, Custom game, Load game, Quit.
 */
public class VBoxMenu extends VBox {
    static final Logger LOG = Logger.getLogger(Piece.class.getName());
    private final int FONT_SIZE = 22;  //Size of font in menu buttons
    private final String BACKGROUNDFILENAME = "/chess_menu.jpg";
    private final Stage stage;

    /**
     *
     * @param spacing defines in pixels spacing between menu buttons.
     * @param stage stage where main menu is supposed to be shown.
     */
    public VBoxMenu(double spacing, Stage stage) {
        super(spacing);
        this.stage = stage;

        setBackground();

        this.getChildren().addAll(setNewDefaultGame(), setNewCustomGame(), setLoadGame(), setQuit());
        this.setAlignment(Pos.CENTER); // Set buttons in the center of window.
    }

    /**
     * After click user is redirected to new default game (4 players, standard piece setup)
     * @return Button with text "new default game"
     */
    private Button setNewDefaultGame(){
        Button newDefaultGame = new Button();
        newDefaultGame.setText("New default game");
        newDefaultGame.setStyle("-fx-font-size: " + FONT_SIZE + "px");

        newDefaultGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info("User clicked on new default game.");
                Manipulator.setDefaultGameSettings(true);   //Getting know to MainGame which setting is chosen.
                Manipulator.setCustomGame(false);

                MainGame main = new MainGame();
                main.start(stage);
            }
        });
        return newDefaultGame;
    }

    /**
     * After click the current stage is passed to CustomMenu
     * @return Button with text "New custom game"
     */
    private Button setNewCustomGame(){
        Button newCustomGame = new Button();
        newCustomGame.setText("New custom game");
        newCustomGame.setStyle("-fx-font-size: " + FONT_SIZE + "px");

        newCustomGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info("User clicked on new custom game.");

                CustomMenu custom = new CustomMenu(stage);
                custom.initUI();
            }
        });
        return newCustomGame;
    }


    /**
     * After click file chooser is shown and user might load saved game.
     * @return Button with text "Load game"
     */
    private Button setLoadGame(){
        Button loadGame = new Button();
        loadGame.setText("Load game");
        loadGame.setStyle("-fx-font-size: " + FONT_SIZE + "px");


        loadGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info("User clicked on load game.");

                Manipulator.setDefaultGameSettings(false);  //Getting know to MainGame which setting is chosen.
                Manipulator.setCustomGame(false);

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Game");            //Dialog to choose from which file the game will be loaded.
                File file = fileChooser.showOpenDialog(stage);

                try{
                    LoadGame loader = new LoadGame(stage, file);
                    loader.load();

                    MainGame main = new MainGame();
                    main.start(stage);
                } catch (Exception e){
                    LOG.severe("Error! Could not load saved game.");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong file chosen.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });
        return loadGame;
    }

    /**
     * After click the current stage is passed to CustomMenu
     * @return Button with new text "New custom game"
     */
    private Button setQuit(){
        Button quit = new Button();
        quit.setText("Quit menu");
        quit.setStyle("-fx-font-size: " + FONT_SIZE + "px");
        quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info("User clicked on quit. Exiting the application.");
                Platform.exit();
            }
        });
        return quit;
    }

    private void setBackground() {
        try {
            Image image = new Image(getClass().getResourceAsStream(BACKGROUNDFILENAME));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            this.setBackground(new Background(backgroundImage));
        } catch (Exception e){
            LOG.severe("Error! Could not load background image.");
        }
    }


}
