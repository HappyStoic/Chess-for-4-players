package pjv.pieces;

import pjv.gui.game.ButtonPosition;

import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class Bishop extends Piece {
    public Bishop(ButtonPosition position, Integer playerColor) {
        super(position, playerColor);
        this.ID_piece = "bishop";
    }

    public ArrayList<ButtonPosition> findAllAvailableMoves(){
        found.clear();

        lookToRightBottom();
        lookToLeftBottom();
        lookToRightTop();
        lookToLeftTop();

        return found;
    }

    public String getPathToIcon(){
        if(getPlayerColor() == 1){
            return "/white/bishop.png";
        } else if(getPlayerColor() == 2){
            return "/black/bishop.png";
        } else if(getPlayerColor() == 3){
            return "/red/bishop.png";
        } else if(getPlayerColor() == 4){
            return "/blue/bishop.png";
        }
        return null;
    }
}
