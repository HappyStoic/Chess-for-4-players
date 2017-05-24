package pjv.controller;

import pjv.gui.game.ButtonPosition;
import pjv.pieces.Pawn;
import pjv.pieces.Piece;
import pjv.pieces.Queen;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Runnable class doing all the changes in the background.
 */
public class BackendThread implements Runnable {
    static final Logger LOG = Logger.getLogger(BackendThread.class.getName());

    private ArrayList<ButtonPosition> availableMoves;

    private ButtonPosition tmpClick;
    private Thread parentThread;

    /**
     *
     * @param parent Thread from which this class is launched.
     */
    public BackendThread(Thread parent) {
        parentThread = parent;
    }

    public void run() {
        game();
    }

    private void game() {
        while (true) {
            if(endGame()){
                setEndGameString();
                break;
            }
            if (!parentThread.isAlive()){   //Sudden issues
                break;
            }

            //Enabling player's on turn positions.
            if (Manipulator.getPickingPiecePhase()){
                disableGuysOnTurnPosition(false);
                Manipulator.setPickingPiecePhase(false);
                Manipulator.setReadyToMovePiece(true);
            }

            //ReadyToMovePiece phase - player decides which piece will move
            if (Manipulator.getButtonClicked() != null && Manipulator.getReadyToMovePiece()) {

                tmpClick = Manipulator.getButtonClicked();
                Manipulator.setLeavingButtonPosition(tmpClick);

                showAndEnableAvailableMoves();

                Manipulator.setButtonClicked(null);
                Manipulator.setReadyToMovePiece(false);
                Manipulator.setMovingPiece(true);
            }

            //User chose where to move
            if (Manipulator.getButtonClicked() != null && Manipulator.getMovingPiece()) {
                disableAvailableButtons();

                if (Manipulator.getButtonClicked() == tmpClick) { //The same position was clicked
                    returnPickingPhase();
                } else if (Manipulator.getButtonClicked().getPlayerColor() != null &&   //Other user's on turn piece was clicked
                        Manipulator.getButtonClicked().getPlayerColor().equals(tmpClick.getPlayerColor())){
                    Manipulator.setMovingPiece(false);
                    Manipulator.setReadyToMovePiece(true);
                } else {            //On available move was clicked
                    movePiece();
                    pawnSwitchedByQueen();
                    Manipulator.setGuiDoMove(true);
                }

            }
        }
    }

    /**
     * Last player is remaining or two friendy players are remaining.
     * @return
     */
    private boolean endGame(){
        return (Manipulator.getPlayers().size() == 1 ||
             (Manipulator.getPlayers().size() == 2 && Manipulator.getPlayers().get(0) == 1 && Manipulator.getPlayers().get(1) == 2) ||
                (Manipulator.getPlayers().size() == 2 && Manipulator.getPlayers().get(0) == 3 && Manipulator.getPlayers().get(1) == 4));
    }
    private void setEndGameString(){
        if(Manipulator.getPlayers().get(0) == 1 || Manipulator.getPlayers().get(0) == 2){
            Manipulator.setEndGameString("White/black team wins!");
        } else {
            Manipulator.setEndGameString("Red/blue team wins!");
        }
    }

    /**
     * If pawn was moved and his current position is at the end of its path (cannot move again), it needs to be changed
     * for queen.
     */
    private void pawnSwitchedByQueen(){
        Piece movedPiece = Manipulator.getPieceClicked();
        if(movedPiece.getID_piece().equals("pawn") && ((Pawn) movedPiece).isPawnAtTheEnd()){

            ButtonPosition dest = Manipulator.getEnteringButtonPosition();
            Piece replacementQueen = new Queen(dest, dest.getPlayerColor());

            Manipulator.getPlayerPieces(movedPiece.getPlayerColor()).remove(movedPiece);
            Manipulator.getPlayerPieces(movedPiece.getPlayerColor()).add(replacementQueen);

            Manipulator.setPieceClicked(replacementQueen);
        }
    }

    private void movePiece(){
        disableGuysOnTurnPosition(true);

        ButtonPosition destination = Manipulator.getButtonClicked();
        Manipulator.setEnteringButtonPosition(destination);

        LOG.info(Manipulator.getPieceClicked().toString() + " is being moved to " + destination.toString());
        if(destination.getPlayerColor() != null){  //If player captures enemy piece
            removeEnemyPiece();
        }

        tmpClick.setPlayerColor(null);
        destination.setPlayerColor(Manipulator.getPieceClicked().getPlayerColor());

        Manipulator.getPieceClicked().setPosition(destination);
        Manipulator.getPieceClicked().setAlreadyMoved(true);

        Manipulator.incrementPlayerOnTurnIndex();
        returnPickingPhase();
    }

    private void removeEnemyPiece(){
        ArrayList<Piece> enemyPieces = Manipulator.getPlayerPieces(Manipulator.getButtonClicked().getPlayerColor());
        for(Piece pieceToRemove : enemyPieces ){
            if(pieceToRemove.getPosition() == Manipulator.getButtonClicked()){
                enemyPieces.remove(pieceToRemove);

                if(pieceToRemove.getID_piece().equals("king")) {
                    Manipulator.setTeamToRemove(pieceToRemove.getPlayerColor());
                    while(true){
                        if(Manipulator.getRemovingDone()){
                            Manipulator.setRemovingDone(false);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    private void disableGuysOnTurnPosition(boolean value){
        for(Piece piece : Manipulator.getPlayerPieces(Manipulator.getPlayerOnTurn())){
            piece.getPosition().setDisable(value);
        }
    }

    private void showAndEnableAvailableMoves(){
        for(Piece piece : Manipulator.getPlayerPieces(Manipulator.getPlayerOnTurn())){
            if(piece.getPosition() == tmpClick){
                Manipulator.setPieceClicked(piece);
                availableMoves = piece.findAllAvailableMoves();
                for(ButtonPosition availableMove : availableMoves){

                    availableMove.setDisable(false);
                    availableMove.setGreenButton();

                }
                break;
            }
        }
        tmpClick.setDisable(false);
    }

    private void disableAvailableButtons(){
        for(ButtonPosition btn : availableMoves){
            btn.setDisable(true);
            btn.resetStyle();
        }
    }

    private void returnPickingPhase(){
        Manipulator.setButtonClicked(null);
        Manipulator.setPickingPiecePhase(true);
        Manipulator.setMovingPiece(false);
    }
}