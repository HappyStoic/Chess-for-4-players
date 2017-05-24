package pjv.controller.initSettings;

import pjv.controller.GameInfo;
import pjv.controller.Manipulator;
import pjv.pieces.*;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Class with public methods to be used while setting default game (creating pieces and setting player color to proper
 * ButtonPositions).
 */
public final class DefaultGame {

    public static void createDefaultWhitePieces(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        ArrayList<Piece> whitePieces = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++) {
            whitePieces.add(new Pawn(gameinfo.getAllButtonPositions()[1][i+3], gameinfo.getWhitePlayerColor()));
        }
        whitePieces.add(new Rook(gameinfo.getAllButtonPositions()[0][3], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new Rook(gameinfo.getAllButtonPositions()[0][10], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new Knight(gameinfo.getAllButtonPositions()[0][4], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new Knight(gameinfo.getAllButtonPositions()[0][9], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new Bishop(gameinfo.getAllButtonPositions()[0][5], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new Bishop(gameinfo.getAllButtonPositions()[0][8], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new Queen(gameinfo.getAllButtonPositions()[0][6], gameinfo.getWhitePlayerColor()));
        whitePieces.add(new King(gameinfo.getAllButtonPositions()[0][7], gameinfo.getWhitePlayerColor()));
        gameinfo.setWhitePieces(whitePieces);
    }
    public static void createDefaultBlackPieces(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        ArrayList<Piece> blackPieces = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++) {
            blackPieces.add(new Pawn(gameinfo.getAllButtonPositions()[12][i+3], gameinfo.getBlackPlayerColor()));
        }
        blackPieces.add(new Rook(gameinfo.getAllButtonPositions()[13][3], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new Rook(gameinfo.getAllButtonPositions()[13][10], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new Knight(gameinfo.getAllButtonPositions()[13][4], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new Knight(gameinfo.getAllButtonPositions()[13][9], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new Bishop(gameinfo.getAllButtonPositions()[13][5], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new Bishop(gameinfo.getAllButtonPositions()[13][8], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new Queen(gameinfo.getAllButtonPositions()[13][6], gameinfo.getBlackPlayerColor()));
        blackPieces.add(new King(gameinfo.getAllButtonPositions()[13][7], gameinfo.getBlackPlayerColor()));
        gameinfo.setBlackPieces(blackPieces);
    }

    public static void createDefaultRedPieces(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        ArrayList<Piece> redPieces = new ArrayList<Piece>();
        for (int i = 0; i < 8; i++) {
            redPieces.add(new Pawn(gameinfo.getAllButtonPositions()[i+3][1], gameinfo.getRedPlayerColor()));
        }
        redPieces.add(new Rook(gameinfo.getAllButtonPositions()[3][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new Rook(gameinfo.getAllButtonPositions()[10][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new Knight(gameinfo.getAllButtonPositions()[4][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new Knight(gameinfo.getAllButtonPositions()[9][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new Bishop(gameinfo.getAllButtonPositions()[5][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new Bishop(gameinfo.getAllButtonPositions()[8][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new Queen(gameinfo.getAllButtonPositions()[6][0], gameinfo.getRedPlayerColor()));
        redPieces.add(new King(gameinfo.getAllButtonPositions()[7][0], gameinfo.getRedPlayerColor()));
        gameinfo.setRedPieces(redPieces);
    }


    public static void createDefaultBluePieces(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        ArrayList<Piece> bluePieces = new ArrayList<Piece>();
        for(int i = 0; i < 8; i++){
            bluePieces.add(new Pawn(gameinfo.getAllButtonPositions()[i+3][12], gameinfo.getBluePlayerColor()));
        }
        bluePieces.add(new Rook(gameinfo.getAllButtonPositions()[3][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new Rook(gameinfo.getAllButtonPositions()[10][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new Knight(gameinfo.getAllButtonPositions()[4][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new Knight(gameinfo.getAllButtonPositions()[9][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new Bishop(gameinfo.getAllButtonPositions()[5][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new Bishop(gameinfo.getAllButtonPositions()[8][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new Queen(gameinfo.getAllButtonPositions()[6][13], gameinfo.getBluePlayerColor()));
        bluePieces.add(new King(gameinfo.getAllButtonPositions()[7][13], gameinfo.getBluePlayerColor()));
        gameinfo.setBluePieces(bluePieces);
    }

    public static void setDefaultWhiteOwnership(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        for (int i = 0; i < 8; i++) {
            gameinfo.getAllButtonPositions()[1][3+i].setPlayerColor(gameinfo.getWhitePlayerColor());
            gameinfo.getAllButtonPositions()[0][3+i].setPlayerColor(gameinfo.getWhitePlayerColor());
        }
    }

    public static void setDefaultBlackOwnership(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        for (int i = 0; i < 8; i++) {
            gameinfo.getAllButtonPositions()[12][3+i].setPlayerColor(gameinfo.getBlackPlayerColor());
            gameinfo.getAllButtonPositions()[13][3+i].setPlayerColor(gameinfo.getBlackPlayerColor());
        }
    }

    public static void setDefaultRedOwnership(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        for (int i = 0; i < 8; i++) {
            gameinfo.getAllButtonPositions()[3+i][1].setPlayerColor(gameinfo.getRedPlayerColor());
            gameinfo.getAllButtonPositions()[3+i][0].setPlayerColor(gameinfo.getRedPlayerColor());
        }
    }

    public static void setDefaultBlueOwnership(){
        GameInfo gameinfo = Manipulator.getGameInfo();
        for(int i = 0; i < 8; i++){
            gameinfo.getAllButtonPositions()[3+i][12].setPlayerColor(gameinfo.getBluePlayerColor());
            gameinfo.getAllButtonPositions()[3+i][13].setPlayerColor(gameinfo.getBluePlayerColor());
        }
    }
}
