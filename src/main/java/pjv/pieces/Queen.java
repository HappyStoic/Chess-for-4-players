package pjv.pieces;

import pjv.gui.game.ButtonPosition;
import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class Queen extends Piece {
    public Queen(ButtonPosition position, Integer playerColor) {
        super(position, playerColor);
        this.ID_piece = "queen";
    }

    public ArrayList<ButtonPosition> findAllAvailableMoves(){
        found.clear();

        lookToLeft();
        lookToTop();
        lookToRight();
        lookToBottom();
        lookToLeftBottom();
        lookToRightBottom();
        lookToLeftTop();
        lookToRightTop();

        return found;
    }

    public String getPathToIcon(){
        if(getPlayerColor() == 1){
            return "/white/queen.png";
        } else if(getPlayerColor() == 2){
            return "/black/queen.png";
        } else if(getPlayerColor() == 3){
            return "/red/queen.png";
        } else if(getPlayerColor() == 4){
            return "/blue/queen.png";
        }
        return null;
    }
}
