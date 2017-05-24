
package jUnitPack;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pjv.controller.Manipulator;
import pjv.pieces.Piece;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

import static java.lang.Thread.sleep;

/**
 *  @author Martin Řepa
 *  @version 1.0
 *  Junit test testing setup of new default game - number of pieces, kind of pieces
 *  and number of players.
 */
public class DefaultSettingsTest {
    static private ArrayList<Piece> whitePlayerPieces;
    static private ArrayList<Piece> blackPlayerPieces;
    static private ArrayList<Piece> redPlayerPieces;
    static private ArrayList<Piece> bluePlayerPieces;

    static private ArrayList<Integer> allPlayers;

    static private Thread thread;

    @BeforeClass
    public static void init() {
        //---------------------Starting and stopping game thread just to get initialized data--------------------------
        SimulateGame sim = new SimulateGame();
        thread = new Thread(sim);
        thread.start();
        try {
            sleep(3000); // Wait until game thread gets initialized
        } catch (Exception e) {
            System.err.println("Thread got interrupted during sleep. " + e.getMessage());
        }
        //-------------------------------------------------------------------------------------------------------------

        whitePlayerPieces = Manipulator.getWhitePieces();
        blackPlayerPieces = Manipulator.getBlackPieces();
        redPlayerPieces = Manipulator.getRedPieces();
        bluePlayerPieces = Manipulator.getBluePieces();

        allPlayers = Manipulator.getPlayers();
    }

    @Test
    public void testSumOfPlayer(){
        assertTrue("There is " + allPlayers.size() + " players, not 4.", allPlayers.size() == 4);
    }

    @Test
    public void testFirstPlayer(){
        boolean contains = false;
        for(int player : allPlayers){
            if(player == 1) { contains = true; }
        }
        assertTrue("There is not player with number one.", contains);
    }
    @Test
    public void testSecondPlayer(){
        boolean contains = false;
        for(int player : allPlayers){
            if(player == 2) { contains = true; }
        }
        assertTrue("There is not player with number two.", contains);
    }
    @Test
    public void testThirdPlayer(){
        boolean contains = false;
        for(int player : allPlayers){
            if(player == 3) { contains = true; }
        }
        assertTrue("There is not player with number three.", contains);
    }
    @Test
    public void testFourthPlayer(){
        boolean contains = false;
        for(int player : allPlayers){
            if(player == 4) { contains = true; }
        }
        assertTrue("There is not player with number four.", contains);
    }

    @Test
    public void testWhitePieces(){
        int pawns = 0, rooks = 0, knights = 0, bishops = 0, queen = 0, king = 0;
        for(Piece piece : whitePlayerPieces){
            if (piece.getID_piece().equals("pawn")) { pawns++; }
            if (piece.getID_piece().equals("knight")) { knights++; }
            if (piece.getID_piece().equals("bishop")) { bishops++; }
            if (piece.getID_piece().equals("rook")) { rooks++; }
            if (piece.getID_piece().equals("queen")) { queen++; }
            if (piece.getID_piece().equals("king")) { king++; }
        }
        assertTrue("There is not proper default white setup", pawns == 8 && rooks == 2
                    && knights == 2 && bishops == 2 && queen ==1 && king == 1);
    }

    @Test
    public void testBlackPieces(){
        int pawns = 0, rooks = 0, knights = 0, bishops = 0, queen = 0, king = 0;
        for(Piece piece : blackPlayerPieces){
            if (piece.getID_piece().equals("pawn")) { pawns++;}
            if (piece.getID_piece().equals("knight")) { knights++; }
            if (piece.getID_piece().equals("rook")) { rooks++; }
            if (piece.getID_piece().equals("bishop")) { bishops++; }
            if (piece.getID_piece().equals("king")) { king++; }
            if (piece.getID_piece().equals("queen")) { queen++; }
        }
        assertTrue("There is not proper default black setup", pawns == 8 && rooks == 2
                && knights == 2 && bishops == 2 && queen ==1 && king == 1);
    }
    @Test
    public void testRedPieces(){
        int pawns = 0, rooks = 0, knights = 0, bishops = 0, queen = 0, king = 0;
        for(Piece piece : redPlayerPieces){
            if (piece.getID_piece().equals("pawn")) { pawns++;}
            if (piece.getID_piece().equals("knight")) { knights++; }
            if (piece.getID_piece().equals("bishop")) { bishops++; }
            if (piece.getID_piece().equals("queen")) { queen++; }
            if (piece.getID_piece().equals("rook")) { rooks++; }
            if (piece.getID_piece().equals("king")) { king++; }
        }
        assertTrue("There is not proper default red setup", pawns == 8 && rooks == 2
                && knights == 2 && bishops == 2 && queen ==1 && king == 1);
    }
    @Test
    public void testBluePieces(){
        int pawns = 0, rooks = 0, knights = 0, bishops = 0, queen = 0, king = 0;
        for(Piece piece : bluePlayerPieces){
            if (piece.getID_piece().equals("pawn")) { pawns++;}
            if (piece.getID_piece().equals("knight")) { knights++; }
            if (piece.getID_piece().equals("bishop")) { bishops++; }
            if (piece.getID_piece().equals("king")) { king++; }
            if (piece.getID_piece().equals("rook")) { rooks++; }
            if (piece.getID_piece().equals("queen")) { queen++; }
        }
        assertTrue("There is not proper default blue setup", pawns == 8 && rooks == 2
                && knights == 2 && bishops == 2 && queen ==1 && king == 1);
    }
    @AfterClass
    public static void end(){
        thread.interrupt();
    }
}
