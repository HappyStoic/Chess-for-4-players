package pjv.pieces;

import pjv.gui.game.ButtonPosition;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class King extends Piece {
    public King(ButtonPosition position, Integer playerColor) {
        super(position, playerColor);
        this.ID_piece = "king";
    }

    public ArrayList<ButtonPosition> findAllAvailableMoves(){
        found.clear();

        stepLeftRight();
        stepTopBottom();

        stepLeftTop_LeftBottom();
        stepRightTop_RightBottom();
        return found;

    }

    public String getPathToIcon(){
        if(getPlayerColor() == 1){
            return "/white/king.png";
        } else if(getPlayerColor() == 2){
            return "/black/king.png";
        } else if(getPlayerColor() == 3){
            return "/red/king.png";
        } else if(getPlayerColor() == 4){
            return "/blue/king.png";
        }
        return null;
    }

    private void stepLeftRight(){
        ButtonPosition possibleMove = this.getPosition().getOnLeft();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){

            found.add(possibleMove);
        }
        possibleMove = this.getPosition().getOnRight();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
    }
    private void stepTopBottom(){
        ButtonPosition possibleMove = this.getPosition().getOnTop();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
        possibleMove = this.getPosition().getOnBottom();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
    }
    private void stepLeftTop_LeftBottom(){
        ButtonPosition possibleMove = this.getPosition().getOnLeftTop();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
        possibleMove = this.getPosition().getOnLeftBottom();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
    }
    private void stepRightTop_RightBottom(){
        ButtonPosition possibleMove = this.getPosition().getOnRightTop();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
        possibleMove = this.getPosition().getOnRightBottom();
        if(possibleMove != null &&
                (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
            found.add(possibleMove);
        }
    }
}
