
package pjv.gui.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pjv.controller.Manipulator;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX Button representing one field on chess board.
 */
public class ButtonPosition extends Button implements Serializable {
    static final Logger LOG = Logger.getLogger(ButtonPosition.class.getName());

    /**
     * All letters on chessboard to specify certain row
     */
    private final Character[] allLetterSigns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'};

    /**
     * All numbers on chessboard to specify certain column
     */
    private final Integer[] allNumberSigns = {1, 2, 3, 4 ,5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public final static int WIDTH_BUTTON = 60;
    public final static int HEIGHT_BUTTON = 60;

    /**
     * Index of letter sign in array allLetterSigns
     */
    private final int rowIndex;

    /**
     * Index of number sign in array allNummberSigns
     */
    private final int columnIndex;

    private int numberSign;
    private char letterSign;

    private Integer playerColor = null;
    private Integer teamNumber = null;

    //Surrounding of ButtonPosition
    private ButtonPosition onTop = null;
    private ButtonPosition onLeftTop = null;
    private ButtonPosition onRightTop = null;
    private ButtonPosition onLeft = null;
    private ButtonPosition onRight = null;
    private ButtonPosition onLeftBottom = null;
    private ButtonPosition onBottom = null;
    private ButtonPosition onRightBottom = null;

    /**
     *
     * @param i row index
     * @param j column index
     */
    public ButtonPosition(int i, int j) {
        this.rowIndex = i;
        this.columnIndex = j;

        setNumber_and_LetterSign();
        setDefaultStyle();
        this.setDisable(true);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info(event.getSource().toString() + " was clicked.");
                Manipulator.setButtonClicked((ButtonPosition)event.getSource());
            }
        });
    }

    /**
     * Setting number and letter representation according to row and column index
     */
    private void setNumber_and_LetterSign(){
        this.letterSign = allLetterSigns[rowIndex];
        this.numberSign = allNumberSigns[columnIndex];
    }

    /**
     *  Setting width, height and white/black color according to its position
     */
    private void setDefaultStyle(){
        this.setPrefWidth(WIDTH_BUTTON);
        this.setPrefHeight(HEIGHT_BUTTON);
        this.setStyle((rowIndex + columnIndex)%2 == 0 ?  "-fx-background-color: #ffffff" : "-fx-background-color: #000000");
    }

    /**
     *
     * @param path path to icon to be displayed on Button
     */
    public void setImage(String path){
        try {
            ImageView iv = new ImageView(new Image(path));
            iv.setFitHeight(ButtonPosition.HEIGHT_BUTTON - 20);
            iv.setFitWidth(ButtonPosition.WIDTH_BUTTON - 20);
            this.setGraphic(iv);
        } catch (Exception e){
            LOG.severe("Error! Could not set image to " + this.toString());
            this.setGraphic(null);
        }
    }

    /**
     *  Setting white/black button according to its position
     */
    public void resetStyle(){
        this.setStyle((rowIndex + columnIndex)%2 == 0 ?  "-fx-background-color: #ffffff" : "-fx-background-color: #000000");
    }

    /**
     * Setting button background to green color (used to show available moves)
     */
    public void setGreenButton(){
        this.setStyle("-fx-background-color: greenyellow");
    }

    @Override
    public String toString() {
        return "ButtonPosition with rowIndex: " + this.rowIndex + " and columnIndex: " + this.columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Integer getPlayerColor() { return playerColor; }

    public void setPlayerColor(Integer playerColor) {
        this.playerColor = playerColor;
        this.playerColor = playerColor;
        if(playerColor == null) {
            this.teamNumber = null;
        } else if (playerColor == 1 || playerColor == 2) {
            this.teamNumber = 1;
        } else if (playerColor == 3 || playerColor == 4) {
            this.teamNumber = 2;
        } else {
            this.teamNumber = null;
        }
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public ButtonPosition getOnTop() {
        return onTop;
    }

    public void setOnTop(ButtonPosition onTop) {
        this.onTop = onTop;
    }

    public ButtonPosition getOnLeftTop() {
        return onLeftTop;
    }

    public void setOnLeftTop(ButtonPosition onLeftTop) {
        this.onLeftTop = onLeftTop;
    }

    public ButtonPosition getOnRightTop() {
        return onRightTop;
    }

    public void setOnRightTop(ButtonPosition onRightTop) {
        this.onRightTop = onRightTop;
    }

    public ButtonPosition getOnLeft() {
        return onLeft;
    }

    public void setOnLeft(ButtonPosition onLeft) {
        this.onLeft = onLeft;
    }

    public ButtonPosition getOnRight() {
        return onRight;
    }

    public void setOnRight(ButtonPosition onRight) {
        this.onRight = onRight;
    }

    public ButtonPosition getOnLeftBottom() {
        return onLeftBottom;
    }

    public void setOnLeftBottom(ButtonPosition onLeftBottom) {
        this.onLeftBottom = onLeftBottom;
    }

    public ButtonPosition getOnBottom() {
        return onBottom;
    }

    public void setOnBottom(ButtonPosition onBottom) {
        this.onBottom = onBottom;
    }

    public ButtonPosition getOnRightBottom() {
        return onRightBottom;
    }

    public void setOnRightBottom(ButtonPosition onRightBottom) {
        this.onRightBottom = onRightBottom;
    }

    public int getNumberSign() {
        return numberSign;
    }

    public void setNumberSign(int numberSign) {
        this.numberSign = numberSign;
    }

    public char getLetterSign() {
        return letterSign;
    }

    public void setLetterSign(char letterSign) {
        this.letterSign = letterSign;
    }
}
