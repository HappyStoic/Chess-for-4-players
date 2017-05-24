package pjv.gui.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX VBox displaying letters (row signs)
 */
public class RightLetters extends VBox {
    private final int WIDTH = 50;
    private Character[] allLetters = {'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'};

    public RightLetters() {
        this.setPrefWidth(WIDTH);
        this.setPadding(new Insets(ChessBoardGui.DEFAULT_PADDING, 5, ChessBoardGui.DEFAULT_PADDING, 5));
        this.setStyle("-fx-background-color: grey");
        this.setLetters();
        this.setAlignment(Pos.CENTER);
    }

    private void setLetters(){
        for(Character letter : allLetters){
            Label numLbl = new Label(String.valueOf(letter));
            numLbl.setTextFill(Color.BLACK);
            numLbl.setPrefWidth(80);
            numLbl.setPrefHeight(80);
            numLbl.setAlignment(Pos.CENTER);
            numLbl.setStyle("-fx-font-size: 20px");
            this.getChildren().add(numLbl);
        }
    }
}
