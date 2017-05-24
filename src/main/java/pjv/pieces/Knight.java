package pjv.pieces;

import pjv.gui.game.ButtonPosition;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class Knight extends Piece {

    public Knight(ButtonPosition position, Integer playerColor) {
        super(position, playerColor);
        this.ID_piece = "knight";
    }

    public ArrayList<ButtonPosition> findAllAvailableMoves(){
        found.clear();

        findTopRightKnightMoves();
        findTopLeftKnightMoves();
        findBottomRightKnightMoves();
        findBottomLeftKnightMoves();

        return found;
    }


    public String getPathToIcon(){
        if(getPlayerColor() == 1){
            return "/white/knight.png";
        } else if(getPlayerColor() == 2){
            return "/black/knight.png";
        } else if(getPlayerColor() == 3){
            return "/red/knight.png";
        } else if(getPlayerColor() == 4){
            return "/blue/knight.png";
        }
        return null;
    }

    private void findTopRightKnightMoves(){
        ButtonPosition tmp = this.getPosition().getOnRightTop();
        if(tmp != null){
            ButtonPosition possibleMove = tmp.getOnTop();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            possibleMove = tmp.getOnRight();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
        }
    }

    private void findTopLeftKnightMoves(){
        ButtonPosition tmp = this.getPosition().getOnLeftTop();
        if(tmp != null){
            ButtonPosition possibleMove = tmp.getOnTop();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            possibleMove = tmp.getOnLeft();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
        }
    }

    private void findBottomLeftKnightMoves(){
        ButtonPosition tmp = this.getPosition().getOnLeftBottom();
        if(tmp != null){
            ButtonPosition possibleMove = tmp.getOnLeft();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            possibleMove = tmp.getOnBottom();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
        }

    }
    private void findBottomRightKnightMoves(){
        ButtonPosition tmp = this.getPosition().getOnRightBottom();
        if(tmp != null){
            ButtonPosition possibleMove = tmp.getOnRight();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
            possibleMove = tmp.getOnBottom();
            if(possibleMove != null &&
                    (possibleMove.getTeamNumber() == null || possibleMove.getTeamNumber().equals(this.getEnemyTeamNumber()))){
                found.add(possibleMove);
            }
        }
    }
}
