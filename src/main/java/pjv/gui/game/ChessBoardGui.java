package pjv.gui.game;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pjv.chessboard.*;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author Martin Řepa <repa.martin@hotmail.com>
 * @version 1.0
 * JavaFX GridPane representing chess board made of grid of ButtonPositions
 */
public class ChessBoardGui extends GridPane implements Serializable{
    static final Logger LOG = Logger.getLogger(ChessBoardGui.class.getName());

    public final static int DEFAULT_PADDING = 20;
    private String BACKGROUNDFILENAME = "/chess_game.jpg";

    /**
     * 2D array with all positions on chessboard
     */
    private ButtonPosition[][] allButtonPositions = new ButtonPosition[14][14];

    public ChessBoardGui() {

        this.setPadding(new Insets(DEFAULT_PADDING));
        setBackgroundImage();

        createRows_Columns();
        addButtons();
        new ChessBoardUtils(allButtonPositions).setPositionsSurroundings(); //Setting all parameters to every ButtonPosition instance
    }

    private void setBackgroundImage(){
        try {
            Image image = new Image(getClass().getResourceAsStream(BACKGROUNDFILENAME));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            this.setBackground(new Background(backgroundImage));
        } catch (Exception e){
            LOG.severe("Error! Couldn't load background image.");
        }
    }

    private void createRows_Columns(){
        ColumnConstraints column = new ColumnConstraints();
        this.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        this.getRowConstraints().add(row);
    }

    private void addButtons(){
        int x = -1;
        for(int i = 13; i >= 0; i--){
            x++;
            int y = -1;
            for(int j = 0; j < 14; j++){
                y++;
                if(!properIndexes(i, j)) {
                    continue;
                }

                ButtonPosition btn = new ButtonPosition(x, y);
                allButtonPositions[x][y] = btn;
                this.add(btn, j, i);
            }
        }
    }

    /**
     *
     * @param x row index
     * @param y column index
     * @return false, when coordinates are out of the chessboard. Otherwise true.
     */
    private boolean properIndexes(int x, int y){
        return !((x < 3 && y < 3)||(y<3 && x>10)||(y>10 && x > 10)||(x < 3 && y >10));
    }

    /**
     * Create standard game icons
     */
    public void setIconsNewDefaultGame(){
        createRedDefaultTeam();
        createBlueDefaultTeam();
        createBlackDefaultTeam();
        createWhiteDefaultTeam();

    }

    private void createRedDefaultTeam(){
        for(int i = 0; i < 8; i++){
            allButtonPositions[3+i][1].setImage("/red/pawn.png");
        }
        allButtonPositions[3][0].setImage("/red/rook.png");
        allButtonPositions[10][0].setImage("/red/rook.png");
        allButtonPositions[4][0].setImage("/red/knight.png");
        allButtonPositions[9][0].setImage("/red/knight.png");
        allButtonPositions[5][0].setImage("/red/bishop.png");
        allButtonPositions[8][0].setImage("/red/bishop.png");
        allButtonPositions[6][0].setImage("/red/queen.png");
        allButtonPositions[7][0].setImage("/red/king.png");
    }

    private void createBlueDefaultTeam(){
        for(int i = 0; i < 8; i++){
            allButtonPositions[3+i][12].setImage("/blue/pawn.png");
        }
        allButtonPositions[3][13].setImage("/blue/rook.png");
        allButtonPositions[10][13].setImage("/blue/rook.png");
        allButtonPositions[4][13].setImage("/blue/knight.png");
        allButtonPositions[9][13].setImage("/blue/knight.png");
        allButtonPositions[5][13].setImage("/blue/bishop.png");
        allButtonPositions[8][13].setImage("/blue/bishop.png");
        allButtonPositions[6][13].setImage("/blue/queen.png");
        allButtonPositions[7][13].setImage("/blue/king.png");
    }
    private void createBlackDefaultTeam(){
        for(int i = 0; i < 8; i++){
            allButtonPositions[12][3+i].setImage("/black/pawn.png");
        }
        allButtonPositions[13][3].setImage("/black/rook.png");
        allButtonPositions[13][10].setImage("/black/rook.png");
        allButtonPositions[13][4].setImage("/black/knight.png");
        allButtonPositions[13][9].setImage("/black/knight.png");
        allButtonPositions[13][5].setImage("/black/bishop.png");
        allButtonPositions[13][8].setImage("/black/bishop.png");
        allButtonPositions[13][6].setImage("/black/queen.png");
        allButtonPositions[13][7].setImage("/black/king.png");
    }
    private void createWhiteDefaultTeam(){
        for(int i = 0; i < 8; i++){
            allButtonPositions[1][3+i].setImage("/white/pawn.png");
        }
        allButtonPositions[0][3].setImage("/white/rook.png");
        allButtonPositions[0][10].setImage("/white/rook.png");
        allButtonPositions[0][4].setImage("/white/knight.png");
        allButtonPositions[0][9].setImage("/white/knight.png");
        allButtonPositions[0][5].setImage("/white/bishop.png");
        allButtonPositions[0][8].setImage("/white/bishop.png");
        allButtonPositions[0][6].setImage("/white/queen.png");
        allButtonPositions[0][7].setImage("/white/king.png");
    }
    public ButtonPosition[][] getAllButtonPositions() {
        return allButtonPositions;
    }
}
