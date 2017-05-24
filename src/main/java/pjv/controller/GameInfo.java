package pjv.controller;

import javafx.animation.Timeline;
import pjv.gui.game.ButtonPosition;
import pjv.gui.game.ChessBoardGui;
import pjv.pieces.Piece;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Class representing current status of the game. Only Manipulator (final class) approaches and owns instance of this class.
 */
public class GameInfo implements Serializable{

    /**
     * String to be shown at the end of the game.
     */
    private String endGameString = null;

    /**
     * All secondary threads launched by primary thread.
     */
    private transient Thread backendThread;
    private transient Timeline clockThread;
    private transient Timeline movingIconsThread;

    /**
     * This array is created when user wants to play Custom game and contains property of every created piece.
     */
    private transient ArrayList<String[]> createdPieces;

    /**
     * All remaining players (not 'dead')
     */
    private ArrayList<Integer> players = new ArrayList<Integer>();

    /**
     * Index of player on turn in ArrayList players
     */
    private Integer playerOnTurnIndex;

    /**
     * Time counter in seconds
     */
    private int timeElapsed;

    /**
     * Standard new game or custom/loaded game
     */
    private boolean defaultGameSettings = true;

    private boolean customGame = false;

    /**
     * Indicating phase when player chooses from available moves
     */
    private boolean readyToMovePiece;

    /**
     * Indicating phase when player chose final destination
     */
    private boolean movingPiece;

    /**
     * Indicating phase when player picks up his piece
     */
    private boolean pickingPiecePhase;

    /**
     * Tells gui whether it shall does some gui changes
     */
    private boolean guiDoMove;

    /**
     * Tells whether gui finished removing certain team (king was captured)
     */
    private boolean removingDone;

    /**
     * ButtonPosition handler sets this variable when it's clicked.
     */
    private ButtonPosition buttonClicked = null;

    /**
     * Moving piece from this position
     */
    private ButtonPosition leavingButtonPosition = null;

    /**
     * Moving piece to this position
     */
    private ButtonPosition enteringButtonPosition = null;

    /**
     * Moving this piece
     */
    private Piece pieceClicked;

    /**
     * teamToRemove is set after king is captured so gui thread knows, which team is supposed to be removed
     */
    private Integer teamToRemove = null;

    private static final int WHITE_PLAYER_COLOR = 1;
    private static final int BLACK_PLAYER_COLOR = 2;
    private static final int RED_PLAYER_COLOR = 3;
    private static final int BLUE_PLAYER_COLOR = 4;


    private ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    private ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    private ArrayList<Piece> redPieces = new ArrayList<Piece>();
    private ArrayList<Piece> bluePieces = new ArrayList<Piece>();

    private static ButtonPosition[][] allButtonPositions;

    public GameInfo() {
    }

    public ButtonPosition getEnteringButtonPosition() {
        return enteringButtonPosition;
    }

    public void setEnteringButtonPosition(ButtonPosition enteringButtonPosition) {
        this.enteringButtonPosition = enteringButtonPosition;
    }

    public ButtonPosition getLeavingButtonPosition() {
        return leavingButtonPosition;
    }

    public void setLeavingButtonPosition(ButtonPosition leavingButtonPosition) {
        this.leavingButtonPosition = leavingButtonPosition;
    }

    public boolean getReadyToMovePiece() {
        return readyToMovePiece;
    }

    public void setReadyToMovePiece(boolean readyToMovePiece) {
        this.readyToMovePiece = readyToMovePiece;
    }

    public boolean getPickingPiecePhase() {
        return pickingPiecePhase;
    }

    public void setPickingPiecePhase(boolean pickingPiecePhase) {
        this.pickingPiecePhase = pickingPiecePhase;
    }

    public ButtonPosition[][] getAllButtonPositions() {
        return allButtonPositions;
    }

    public void setAllButtonPositions(ButtonPosition[][] allButtonPositions) {
        GameInfo.allButtonPositions = allButtonPositions;
    }

    public ArrayList<Integer> getPlayers() {
        return players;
    }

    public Integer getPlayerOnTurnIndex() {
        return playerOnTurnIndex;
    }

    public  boolean getMovingPiece() {
        return movingPiece;
    }

    public int getWhitePlayerColor() {
        return WHITE_PLAYER_COLOR;
    }

    public int getBlackPlayerColor() {
        return BLACK_PLAYER_COLOR;
    }

    public int getRedPlayerColor() {
        return RED_PLAYER_COLOR;
    }

    public int getBluePlayerColor() {
        return BLUE_PLAYER_COLOR;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public ArrayList<Piece> getRedPieces() {
        return redPieces;
    }

    public ArrayList<Piece> getBluePieces() {
        return bluePieces;
    }

    public void setPlayers(ArrayList<Integer> players) {
        this.players = players;
    }

    public void setPlayerOnTurnIndex(Integer playerOnTurn) {
        this.playerOnTurnIndex = playerOnTurn;
    }

    public void setMovingPiece(boolean movingPiece) {
        this.movingPiece = movingPiece;
    }

    public void setWhitePieces(ArrayList<Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public void setBlackPieces(ArrayList<Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public void setRedPieces(ArrayList<Piece> redPieces) {
        this.redPieces = redPieces;
    }

    public void setBluePieces(ArrayList<Piece> bluePieces) {
        this.bluePieces = bluePieces;
    }

    public ButtonPosition getButtonClicked() { return buttonClicked; }

    public void setButtonClicked(ButtonPosition clicked) { this.buttonClicked = clicked; }

    public Piece getPieceClicked() { return pieceClicked; }

    public void setPieceClicked(Piece pieceClicked) { this.pieceClicked = pieceClicked; }

    public Integer getTeamToRemove() { return teamToRemove; }

    public void setTeamToRemove(Integer teamToRemove) { this.teamToRemove = teamToRemove; }

    public boolean getGuiDoMove() {
        return guiDoMove;
    }

    public void setGuiDoMove(boolean guiDoMove) {
        this.guiDoMove = guiDoMove;
    }

    public boolean getRemovingDone() {
        return removingDone;
    }

    public void setRemovingDone(boolean removingDone) {
        this.removingDone = removingDone;
    }

    public int getTimeElapsed() { return timeElapsed; }

    public void setTimeElapsed(int timeElapsed) { this.timeElapsed = timeElapsed; }

    public boolean getDefaultGameSettings() { return defaultGameSettings; }

    public void setDefaultGameSettings(boolean defaultGameSettings) { this.defaultGameSettings = defaultGameSettings; }

    public Thread getBackendThread() {
        return backendThread;
    }

    public void setBackendThread(Thread backendThread) {
        this.backendThread = backendThread;
    }

    public Timeline getClockThread() {
        return clockThread;
    }

    public void setClockThread(Timeline clockThread) {
        this.clockThread = clockThread;
    }

    public Timeline getMovingIconsThread() {
        return movingIconsThread;
    }

    public void setMovingIconsThread(Timeline movingIconsThread) {
        this.movingIconsThread = movingIconsThread;
    }

    public boolean getCustomGame() {
        return customGame;
    }

    public void setCustomGame(boolean customGame) {
        this.customGame = customGame;
    }

    public ArrayList<String[]> getCreatedPieces() {
        return createdPieces;
    }

    public void setCreatedPieces(ArrayList<String[]> createdPieces) {
        this.createdPieces = createdPieces;
    }

    public String getEndGameString() { return endGameString; }

    public void setEndGameString(String endGameString) { this.endGameString = endGameString; }
}
