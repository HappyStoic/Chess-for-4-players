package pjv.pieces;

import pjv.gui.game.ButtonPosition;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class Pawn extends Piece {
    public Pawn(ButtonPosition position, Integer playerColor) {
        super(position, playerColor);
        this.ID_piece = "pawn";
    }
    public ArrayList<ButtonPosition> findAllAvailableMoves(){
        found.clear();

        switch (this.getPlayerColor()){
            case 1:
                whitePlayerSearchStep();
                whitePlayerSearchAttack();
                break;
            case 2:
                blackPlayerSearchStep();
                blackPlayerSearchAttack();
                break;
            case 3:
                redPlayerSearchStep();
                redPlayerSearchAttack();
                break;
            case 4:
                bluePlayerSearchStep();
                bluePlayerSearchAttack();
                break;
        }

        return found;
    }

    public String getPathToIcon(){
        if(getPlayerColor() == 1){
            return "/white/pawn.png";
        } else if(getPlayerColor() == 2){
            return "/black/pawn.png";
        } else if(getPlayerColor() == 3){
            return "/red/pawn.png";
        } else if(getPlayerColor() == 4){
            return "/blue/pawn.png";
        }
        return null;
    }

    /**
     *
     * @return true when pawn reaches end of its path (cannot move anywhere), otherwise false.
     */
    public boolean isPawnAtTheEnd(){
        switch (this.getPlayerColor()){
            case 1: return isWhiteAtTheEnd();
            case 2: return isBlackAtTheEnd();
            case 3: return isRedAtTheEnd();
            case 4: return isBlueAtTheEnd();
            default: return false;
        }
    }

    //------------------------------WHITE PLAYER ----------------------------------------------------------
    private void whitePlayerSearchStep(){
        ButtonPosition possibleMove = this.getPosition().getOnTop();
        if(possibleMove != null && possibleMove.getTeamNumber() == null){
            found.add(possibleMove);
        }
            if(!this.getAlreadyMoved()){  //If it's pawn's first move, it is allowed to go 2 positions forward.
            possibleMove = possibleMove.getOnTop();
            if(possibleMove != null && possibleMove.getTeamNumber() == null){
                found.add(possibleMove);
            }
        }
    }
    private void whitePlayerSearchAttack(){
        ButtonPosition possibleAttack = this.getPosition().getOnRightTop();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }

        possibleAttack = this.getPosition().getOnLeftTop();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }
    }
    private boolean isWhiteAtTheEnd(){
        return (this.getPosition().getColumnIndex() < 3 && this.getPosition().getRowIndex() == 10) ||
                (this.getPosition().getColumnIndex() > 10 && this.getPosition().getRowIndex() == 10) ||
                this.getPosition().getRowIndex() == 13;
    }
    //------------------------------WHITE PLAYER ----------------------------------------------------------

    //------------------------------BLACK PLAYER ----------------------------------------------------------
    private void blackPlayerSearchStep(){
        ButtonPosition possibleMove = this.getPosition().getOnBottom();
        if(possibleMove != null && possibleMove.getTeamNumber() == null){
            found.add(possibleMove);
        }
        if(!this.getAlreadyMoved()){        //If it's pawn's first move, it is allowed to go 2 positions forward.
            possibleMove = possibleMove.getOnBottom();
            if(possibleMove != null && possibleMove.getTeamNumber() == null){
                found.add(possibleMove);
            }
        }
    }
    private void blackPlayerSearchAttack(){
        ButtonPosition possibleAttack = this.getPosition().getOnRightBottom();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }

        possibleAttack = this.getPosition().getOnLeftBottom();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }
    }
    private boolean isBlackAtTheEnd(){
        return (this.getPosition().getColumnIndex() < 3 && this.getPosition().getRowIndex() == 3) ||
                (this.getPosition().getColumnIndex() > 10 && this.getPosition().getRowIndex() == 3) ||
                this.getPosition().getRowIndex() == 0;
    }
    //------------------------------BLACK PLAYER ----------------------------------------------------------

    //------------------------------RED PLAYER ----------------------------------------------------------
    private void redPlayerSearchStep(){
        ButtonPosition possibleMove = this.getPosition().getOnRight();
        if(possibleMove != null && possibleMove.getTeamNumber() == null){
            found.add(possibleMove);
        }

        if(!this.getAlreadyMoved()){     //If it's pawn's first move, it is allowed to go 2 positions forward.
            possibleMove = possibleMove.getOnRight();
            if(possibleMove != null && possibleMove.getTeamNumber() == null){
                found.add(possibleMove);
            }
        }
    }
    private void redPlayerSearchAttack(){
        ButtonPosition possibleAttack = this.getPosition().getOnRightTop();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }

        possibleAttack = this.getPosition().getOnRightBottom();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }
    }
    private boolean isRedAtTheEnd(){
        return (this.getPosition().getColumnIndex() == 10 && this.getPosition().getRowIndex() < 3) ||
                (this.getPosition().getColumnIndex() == 10 && this.getPosition().getRowIndex() > 10) ||
                this.getPosition().getColumnIndex() == 13;
    }
    //------------------------------RED PLAYER ----------------------------------------------------------

    //------------------------------BLUE PLAYER ----------------------------------------------------------
    private void bluePlayerSearchStep(){
        ButtonPosition possibleMove = this.getPosition().getOnLeft();
        if(possibleMove != null && possibleMove.getTeamNumber() == null){
            found.add(possibleMove);
        }
        if(!this.getAlreadyMoved()){      //If it's pawn's first move, it is allowed to go 2 positions forward.
            possibleMove = possibleMove.getOnLeft();
            if(possibleMove != null && possibleMove.getTeamNumber() == null){
                found.add(possibleMove);
            }
        }
    }
    private void bluePlayerSearchAttack(){
        ButtonPosition possibleAttack = this.getPosition().getOnLeftTop();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }

        possibleAttack = this.getPosition().getOnLeftBottom();
        if(possibleAttack != null && possibleAttack.getTeamNumber() != null &&
                possibleAttack.getTeamNumber().equals(this.getEnemyTeamNumber())){
            found.add(possibleAttack);
        }
    }
    private boolean isBlueAtTheEnd(){
        return (this.getPosition().getColumnIndex() == 3 && this.getPosition().getRowIndex() < 3) ||
                (this.getPosition().getColumnIndex() == 3 && this.getPosition().getRowIndex() > 10) ||
                this.getPosition().getColumnIndex() == 0;
    }
    //------------------------------BLUE PLAYER ----------------------------------------------------------

}
