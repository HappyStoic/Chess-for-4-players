package pjv.chessboard;
import pjv.gui.game.ButtonPosition;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * Class just to set surrounding of every ButtonPosition (representing one field on button board)
 */
public class ChessBoardUtils {
    private final int rows = 14;
    private final int columns = 14;

    private ButtonPosition[][] allButtonPositions;

    public ChessBoardUtils(ButtonPosition[][] allButtonPositions) {
        this.allButtonPositions = allButtonPositions;
    }

    /**
     * Set surrounding in every direction to all ButtonPositions
     */
    public void setPositionsSurroundings(){
        setOnLeft();
        setOnRight();

        setOnTop();
        setOnBottom();

        setOnLeftTop();
        setOnRightTop();

        setOnLeftBottom();
        setOnRightBottom();
    }

    /**
     *
     * @param x row index
     * @param y column index
     * @return false, when coordinates are out of the chessboard. Otherwise true.
     */
    private boolean properIndexes(int x, int y){
        return !((x < 3 && y < 3)||(y<3 && x>10)||(y>10 && x > 10)||(x < 3 && y >10)||(x<0)||(y<0)||(x>13)||(y>13));
    }

    private void setOnLeft(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i, j-1) && properIndexes(i, j)){
                    allButtonPositions[i][j].setOnLeft(allButtonPositions[i][j-1]);
                }
            }
        }
    }

    private void setOnRight(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i, j+1) && properIndexes(i, j)){
                    allButtonPositions[i][j].setOnRight(allButtonPositions[i][j+1]);
                }
            }
        }
    }

    private void setOnTop(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i+1, j)&& properIndexes(i, j)){
                    allButtonPositions[i][j].setOnTop(allButtonPositions[i+1][j]);
                }
            }
        }
    }

    private void setOnBottom(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i-1, j)&& properIndexes(i, j)){
                    allButtonPositions[i][j].setOnBottom(allButtonPositions[i-1][j]);
                }
            }
        }
    }

    private void setOnLeftTop(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i+1, j-1) && properIndexes(i, j)){
                    allButtonPositions[i][j].setOnLeftTop(allButtonPositions[i+1][j-1]);
                }
            }
        }
    }


    private void setOnRightTop(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i+1, j+1) && properIndexes(i, j)){
                    allButtonPositions[i][j].setOnRightTop(allButtonPositions[i+1][j+1]);
                }
            }
        }
    }

    private void setOnLeftBottom(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i-1, j-1) && properIndexes(i, j)){
                    allButtonPositions[i][j].setOnLeftBottom(allButtonPositions[i-1][j-1]);
                }
            }
        }
    }

    private void setOnRightBottom(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(properIndexes(i-1, j+1) && properIndexes(i, j)){
                    allButtonPositions[i][j].setOnRightBottom(allButtonPositions[i-1][j+1]);
                }
            }
        }
    }
}
