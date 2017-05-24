package pjv.gui.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX VBox displaying numbers (column signs)
 */
public class BottomNumbers extends HBox {

    private final int HEIGHT = 50;
    private Integer[] allNumbers = {1, 2, 3, 4 ,5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public BottomNumbers() {
        this.setPrefHeight(HEIGHT);
        this.setPadding(new Insets(5, HEIGHT + ChessBoardGui.DEFAULT_PADDING, 5, ChessBoardGui.DEFAULT_PADDING));
        this.setStyle("-fx-background-color: grey");
        this.setNumbers();
        this.setAlignment(Pos.CENTER);
    }

    private void setNumbers(){
        for(Integer number : allNumbers){
            Label numLbl = new Label(String.valueOf(number));
            numLbl.setPrefWidth(80);
            numLbl.setPrefHeight(80);
            numLbl.setTextFill(Color.BLACK);
            numLbl.setAlignment(Pos.CENTER);
            numLbl.setStyle("-fx-font-size: 20px");
            this.getChildren().add(numLbl);
        }
    }

}
