package pjv.pieces;

import pjv.gui.game.ButtonPosition;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public abstract class Piece implements Serializable{
    /**
     * Array list with all available moves
     */
    ArrayList<ButtonPosition> found = new ArrayList<ButtonPosition>();

    /**
     * Button position specifying where the piece is located
     */
    private ButtonPosition buttonPosition;

    /**
     * Set to true after first move. (e.g. castling is forbid after king's and rook's first move.)
     */
    private boolean alreadyMoved = false;

    String ID_piece;
    private final Integer teamNumber;
    private final Integer enemyTeamNumber;
    private final Integer playerColor;

    public Piece(ButtonPosition buttonPosition, Integer playerColor){
        this.buttonPosition = buttonPosition;
        this.playerColor = playerColor;
        if(playerColor == 1 || playerColor == 2){
            this.teamNumber = 1;
            this.enemyTeamNumber = 2;
        } else if (playerColor == 3 || playerColor == 4){
            this.teamNumber = 2;
            this.enemyTeamNumber = 1;
        } else {
            this.teamNumber = null;
            this.enemyTeamNumber = null;
        }
    }

    public String getID_piece() { return ID_piece; }

    boolean getAlreadyMoved() { return alreadyMoved; }

    public void setAlreadyMoved(boolean alreadyMoved) { this.alreadyMoved = alreadyMoved; }

    public Integer getPlayerColor() {
        return playerColor;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    Integer getEnemyTeamNumber() {
        return enemyTeamNumber;
    }

    public ButtonPosition getPosition() {
        return this.buttonPosition;
    }

    public void setPosition(ButtonPosition position){
        this.buttonPosition= position;
    }

    @Override
    public String toString() {
        return this.ID_piece + ", player color:" + this.getPlayerColor();
    }

    /**
     * Exploring right top direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToRightTop() {
        ButtonPosition possibleMove = this.getPosition().getOnRightTop();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null){
                break;
            }
            possibleMove = possibleMove.getOnRightTop();
        }
    }


    /**
     * Exploring left top direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToLeftTop(){
        ButtonPosition possibleMove = this.getPosition().getOnLeftTop();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnLeftTop();
        }
    }


    /**
     * Exploring left bottom direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToLeftBottom(){
        ButtonPosition possibleMove = this.getPosition().getOnLeftBottom();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnLeftBottom();
        }
    }


    /**
     * Exploring right bottom direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToRightBottom(){
        ButtonPosition possibleMove = this.getPosition().getOnRightBottom();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnRightBottom();
        }
    }


    /**
     * Exploring top direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToTop(){
        ButtonPosition possibleMove = this.getPosition().getOnTop();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnTop();
        }
    }


    /**
     * Exploring bottom direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToBottom(){
        ButtonPosition possibleMove = this.getPosition().getOnBottom();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnBottom();
        }
    }


    /**
     * Exploring left direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToLeft(){
        ButtonPosition possibleMove = this.getPosition().getOnLeft();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnLeft();
        }
    }


    /**
     * Exploring right direction until enemy/friendly piece is found or there is border of game board.
     */
    void lookToRight(){
        ButtonPosition possibleMove = this.getPosition().getOnRight();
        while(possibleMove != null){
            if((possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            if(possibleMove.getTeamNumber() != null) {
                break;
            }
            possibleMove = possibleMove.getOnRight();
        }
    }

    /**
     *
     * @return path to png icon (color depends on player color)
     */
    public abstract String getPathToIcon();

    /**
     * Abstract method to find all available moves.
     * @return array list with found ButtonPositions
     */
    public abstract ArrayList<ButtonPosition> findAllAvailableMoves();
}

