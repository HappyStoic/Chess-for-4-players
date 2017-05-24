package pjv.pieces;

import pjv.gui.game.ButtonPosition;
import java.util.ArrayList;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 */
public class Rook extends Piece {
    public Rook(ButtonPosition position, Integer playerColor) {
        super(position, playerColor);
        this.ID_piece = "rook";
    }

    public ArrayList<ButtonPosition> findAllAvailableMoves(){
        found.clear();

        lookToRight();
        lookToLeft();
        lookToBottom();
        lookToTop();

        return found;
    }

    public String getPathToIcon(){
        if(getPlayerColor() == 1){
            return "/white/rook.png";
        } else if(getPlayerColor() == 2){
            return "/black/rook.png";
        } else if(getPlayerColor() == 3){
            return "/red/rook.png";
        } else if(getPlayerColor() == 4){
            return "/blue/rook.png";
        }
        return null;
    }
}
