package pjv.controller;

import javafx.animation.Timeline;
import pjv.gui.game.ButtonPosition;
import pjv.gui.game.ChessBoardGui;
import pjv.pieces.*;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Class Manipulator approaches through static set/get methods to GameInfo.
 */
public final class Manipulator {

    /**
     * Instance of GameInfo containing status of the game.
     */
    private static final GameInfo gameinfo = new GameInfo();

    private Manipulator() {
    }

    synchronized public static void setPlayers(Integer one, Integer two, Integer three, Integer four) {
        ArrayList<Integer> players = new ArrayList<Integer>();
        players.add(one); players.add(two); players.add(three); players.add(four);
        gameinfo.setPlayers(players);
    }

    synchronized public static void setPlayers(ArrayList<Integer> players){
        gameinfo.setPlayers(players);
    }

    synchronized public static GameInfo getGameInfo() { return gameinfo; }

    synchronized public static ArrayList<Integer> getPlayers() {
        return gameinfo.getPlayers();
    }

    synchronized public static Integer getPlayerOnTurn() {return gameinfo.getPlayers().get(gameinfo.getPlayerOnTurnIndex());}

    synchronized public static void incrementPlayerOnTurnIndex() {
        gameinfo.setPlayerOnTurnIndex((gameinfo.getPlayerOnTurnIndex() + 1) % gameinfo.getPlayers().size());
    }

    synchronized public static void setPlayerOnTurnIndex(Integer value) {
        gameinfo.setPlayerOnTurnIndex(value);
    }

    synchronized public static void setMovingPiece(boolean value) {
        gameinfo.setMovingPiece(value);
    }

    synchronized public static void setWhitePieces(ArrayList<Piece> pieces) {
        gameinfo.setWhitePieces(pieces);
    }

    synchronized public static void setBlackPieces(ArrayList<Piece> pieces) {
        gameinfo.setBlackPieces(pieces);
    }

    synchronized public static void setRedPieces(ArrayList<Piece> pieces) {
        gameinfo.setRedPieces(pieces);
    }

    synchronized public static void setBluePieces(ArrayList<Piece> pieces) {
        gameinfo.setBluePieces(pieces);
    }

    synchronized public static boolean getMovingPiece() {
        return gameinfo.getMovingPiece();
    }

    synchronized public static ArrayList<Piece> getWhitePieces() {
        return gameinfo.getWhitePieces();
    }

    synchronized public static ArrayList<Piece> getBlackPieces() {
        return gameinfo.getBlackPieces();
    }

    synchronized public static ArrayList<Piece> getRedPieces() {
        return gameinfo.getRedPieces();
    }

    synchronized public static ArrayList<Piece> getBluePieces() {
        return gameinfo.getBluePieces();
    }

    synchronized public static ButtonPosition[][] getAllButtonPositions() {
        return gameinfo.getAllButtonPositions();
    }

    synchronized public static void setAllButtonPositions(ButtonPosition[][] allButtonPositions) {
        gameinfo.setAllButtonPositions(allButtonPositions);
    }

    synchronized public static ArrayList<Piece> getPlayerPieces(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return Manipulator.getWhitePieces();
            case 2:
                return Manipulator.getBlackPieces();
            case 3:
                return Manipulator.getRedPieces();
            case 4:
                return Manipulator.getBluePieces();
            default:
                return null;
        }
    }

    synchronized public static void setButtonClicked(ButtonPosition clicked) {
        gameinfo.setButtonClicked(clicked);
    }

    synchronized public static ButtonPosition getButtonClicked() {
        return gameinfo.getButtonClicked();
    }

    synchronized public static Piece getPieceClicked() {
        return gameinfo.getPieceClicked();
    }

    synchronized public static void setPieceClicked(Piece piece) {
        gameinfo.setPieceClicked(piece);
    }

    synchronized public static void setPickingPiecePhase(boolean value) {
        gameinfo.setPickingPiecePhase(value);
    }

    synchronized public static boolean getPickingPiecePhase() {
        return gameinfo.getPickingPiecePhase();
    }

    synchronized public static void setReadyToMovePiece(boolean value) {
        gameinfo.setReadyToMovePiece(value);
    }

    synchronized public static boolean getReadyToMovePiece() {
        return gameinfo.getReadyToMovePiece();
    }

    synchronized public static void setLeavingButtonPosition(ButtonPosition bt) {
        gameinfo.setLeavingButtonPosition(bt);
    }

    synchronized public static ButtonPosition getLeavingButtonPosition() {
        return gameinfo.getLeavingButtonPosition();
    }

    synchronized public static void setEnteringButtonPosition(ButtonPosition bt) {
        gameinfo.setEnteringButtonPosition(bt);
    }

    synchronized public static ButtonPosition getEnteringButtonPosition() {
        return gameinfo.getEnteringButtonPosition();
    }

    synchronized public static void setTeamToRemove(Integer num) {
        gameinfo.setTeamToRemove(num);
    }

    synchronized public static Integer getTeamToRemove() {
        return gameinfo.getTeamToRemove();
    }

    synchronized public static void setGuiDoMove(boolean value) {
        gameinfo.setGuiDoMove(value);
    }

    synchronized public static boolean getGuiDoMove() {
        return gameinfo.getGuiDoMove();
    }

    synchronized public static void setRemovingDone(boolean value) {
        gameinfo.setRemovingDone(value);
    }

    synchronized public static boolean getRemovingDone() {
        return gameinfo.getRemovingDone();
    }

    synchronized public static void setTimeElapsed(int value){ gameinfo.setTimeElapsed(value);}

    synchronized public static int getTimeElapsed(){ return gameinfo.getTimeElapsed(); }

    synchronized public static boolean getDefaultGameSettings(){ return gameinfo.getDefaultGameSettings(); }

    synchronized public static void  setDefaultGameSettings(boolean value){ gameinfo.setDefaultGameSettings(value); }

    synchronized public static  Thread getBackendThread() {
        return gameinfo.getBackendThread();
    }

    synchronized public static void setBackendThread(Thread backendThread) {
        gameinfo.setBackendThread(backendThread);
    }

    synchronized public static  Timeline getClockThread() {
        return gameinfo.getClockThread();
    }

    synchronized public static void setClockThread(Timeline clockThread) {
        gameinfo.setClockThread(clockThread);
    }

    synchronized public static  Timeline getMovingIconsThread() {
        return gameinfo.getMovingIconsThread();
    }

    synchronized public static  void setMovingIconsThread(Timeline movingIconsThread) {
        gameinfo.setMovingIconsThread(movingIconsThread);
    }
    synchronized public static boolean getCustomGame(){ return gameinfo.getCustomGame(); }

    synchronized public static void  setCustomGame(boolean value){ gameinfo.setCustomGame(value); }

    synchronized public static ArrayList<String[]> getCreatedPieces() {
        return gameinfo.getCreatedPieces();
    }

    synchronized public static void setCreatedPieces(ArrayList<String[]> createdPieces) {
        gameinfo.setCreatedPieces(createdPieces);
    }

    synchronized public static String getLiteralColorOnTurn(){
        switch (Manipulator.getPlayerOnTurn()){
            case 1: return "white";
            case 2: return "black";
            case 3: return "red";
            case 4: return "blue";
            default: return "moribundus";
        }
    }
    synchronized public static String getEndGameString(){ return gameinfo.getEndGameString(); }

    synchronized public static void setEndGameString(String string){ gameinfo.setEndGameString(string); }
}