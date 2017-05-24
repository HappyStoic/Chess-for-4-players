package pjv.gui.game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pjv.controller.GameInfo;
import pjv.controller.MainMenu;
import pjv.controller.Manipulator;
import pjv.gui.menu.CustomMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX ToolBar. It contains Label with player on turn, Label representing clock, Button to save game, Button to go to
 * main menu and Button to quit the application.
 */
public class TopToolBar extends ToolBar {
    static final Logger LOG = Logger.getLogger(TopToolBar.class.getName());

    /**
     * Label showing which player is on turn
     */
     private Label onTurnLabel;
    /**
     * Label representing clock timer
     */
    private Label clockLabel;

    private Stage stage;

    /**
     *
     * @param stage where the whole game appliaction is shown
     */
    public TopToolBar(Stage stage) {
        this.stage = stage;
        BorderPane root = new BorderPane();

        this.setStyle("-fx-background-color: #000000"); //Setting black background of toolbar
        this.setPrefHeight(60);

        setupClockLabel();
        createOnTurnLabel();

        root.setLeft(onTurnLabel);
        root.setCenter(clockLabel);
        root.setRight(createRightPart());

        this.getItems().add(root);
    }

    private void createOnTurnLabel(){
        onTurnLabel= new Label();
        onTurnLabel.setStyle("-fx-font-size: 18px");
        onTurnLabel.setTextFill(Color.WHITE);
        onTurnLabel.setPadding(new Insets(10, 0, 0, 30));
        onTurnLabel.setPrefWidth(280);
    }

    /**
     *
     * @return Hbox containing all Buttons (save game, main menu, quit)
     */
    private HBox createRightPart(){
        HBox hbox = new HBox(5);
        hbox.setPadding(new Insets(10, 0, 0, 0));
        hbox.setPrefWidth(350);

        Region reg = new Region(); //Spacing between clock and control buttons
        HBox.setHgrow(reg, Priority.ALWAYS);

        Button saveButton = createSaveButton();
        Button returnToMenu = createReturnToMenu();
        Button quitButton = createQuitButton();

        hbox.getChildren().addAll(reg, saveButton, returnToMenu, quitButton);
        return hbox;
    }

    private Button createQuitButton(){
        Button quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info("User clicked on quit. Exiting the application.");
                Platform.exit();
            }
        });
        return quitButton;

    }

    private Button createReturnToMenu(){
        Button returnToMenu = new Button();
        returnToMenu.setText("Main menu");

        returnToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try{
                    //Stopping all secondary threads before returning to main menu
                    Manipulator.getBackendThread().stop(); //Backend thread doesn't do anything, what could be corrupted using stop method.
                    Manipulator.getMovingIconsThread().stop();
                    Manipulator.getClockThread().stop();
                    LOG.info("Support threads stopped properly.");
                } catch (Exception e){
                    LOG.severe("ERROR while stopping other threads. " + e.getMessage());
                }

                LOG.info("Going back to main menu.");
                MainMenu menu = new MainMenu();
                menu.start(stage);
            }
        });
        return returnToMenu;
    }

    private Button createSaveButton(){
        Button saveButton = new Button();
        saveButton.setText("Save game");
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save game");             //File dialog to let user choose, where the game is supposed to be saved
                File file = fileChooser.showSaveDialog(stage);  //Showing file chooser window
                try {
                    ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file.getPath(), false));

                    GameInfo gameInfo = Manipulator.getGameInfo();
                    out.writeObject(gameInfo);  //Writing gameinfo object, where complete status of game is stored.

                } catch (Exception ex) {
                    LOG.severe("ERROR while trying to save the game. " + ex.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error occurred. Game is not saved.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });
        return saveButton;
    }

    private void setupClockLabel(){
        clockLabel = new Label("0:00:00");
        clockLabel.setPadding(new Insets(0, 0, 0, 100));
        clockLabel.setTextFill(Color.WHITE);
        clockLabel.setStyle("-fx-font-size: 25px");
    }

    public Label getClockLabel() {
        return clockLabel;
    }

    public Label getOnTurnLabel(){ return onTurnLabel; }
}
