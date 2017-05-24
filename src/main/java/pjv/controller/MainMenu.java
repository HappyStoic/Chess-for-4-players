package pjv.controller;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX Application representing main menu.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pjv.gui.menu.VBoxMenu;

import java.util.logging.Logger;

public class MainMenu extends Application {
    static final Logger LOG = Logger.getLogger(MainMenu.class.getName());

    public static void main(String[] args) { launch(args); }

    /**
     *
     * @param primaryStage , stage where MainMenu application is supposed to be shown
     */
    @Override
    public void start(Stage primaryStage) {
        LOG.info("Main menu is being initialised");
        initUI(primaryStage);
    }


    private void initUI(Stage stage){
        VBoxMenu root = new VBoxMenu(90, stage);


        Scene scene = new Scene(root, 400, 600);

        stage.setTitle("Main menu - 4 player chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
