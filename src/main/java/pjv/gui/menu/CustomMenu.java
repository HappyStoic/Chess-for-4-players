package pjv.gui.menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import pjv.controller.MainMenu;
import pjv.controller.Manipulator;
import pjv.controller.initSettings.CustomGame;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CustomMenu {
    static final Logger LOG = Logger.getLogger(CustomMenu.class.getName());

    /**
     * All letters on chessboard to specify certain row
     */
    private final String[] allLetterSigns = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};

    /**
     * All numbers on chessboard to specify certain column
     */
    private final String[] allNumberSigns = {"1", "2", "3", "4" ,"5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};

    /**
     * Index of chosen letter in array allLetterSigns
     */
    private int letterIndex;

    /**
     * Index of chosen number in array allNumberSigns
     */
    private int numberIndex;

    /**
     * All pieces available to choose
     */
    private final String[] allPieces = {"pawn", "knight", "bishop", "queen", "king", "rook"};

    /**
     * All teams available to choose
     */
    private final String[] allTeams = {"White", "Black", "Red", "Blue"};

    /**
     * Sum of pieces already created
     */
    private int numberPiecesInListView = 0;

    /**
     * ArrayList with created pieces. Variable "created" will be passed to Manipulator and later pieces will be
     * created from this data
     */
    private ArrayList<String[]> created = new ArrayList<String[]>();


    private String selectedNumber = null;
    private String selectedLetter = null;
    private String pieceSelected = "pawn";
    private String teamSelected = "1";

    private Stage stage;

    /**
     * Top toolbar where user chooses parameters of new pieces
     */
    private HBox topHBox;

    /**
     * List view where all already created pieces are displayed.
     */
    private ListView middleList;

    /**
     * Bottom toolbar with two buttons - main menu ; create custom game
     */
    private HBox botHBox;

    /**
     * Top toolbar Button to create pieces with selected parameters
     */
    private Button confirmButton;

    /**
     * Bottom toolbar Button to start new custom game
     */
    private Button proceedButton;

    /**
     *
     * @param stage where Custom menu gui is supposed to be shown.
     */
    public CustomMenu(Stage stage) {
        this.stage = stage;
    }

    public void initUI(){

        BorderPane root = new BorderPane();

        createTopHbox();
        createMiddleList();
        createBotHBox();

        root.setTop(topHBox);
        root.setCenter(middleList);
        root.setBottom(botHBox);

        Scene scene = new Scene(root, 750, 700);

        stage.setScene(scene);
        stage.setTitle("Creating custom game");
        stage.show();

    }
    private void createBotHBox(){
        botHBox = new HBox(15);
        botHBox.setPrefHeight(70);
        botHBox.setAlignment(Pos.CENTER_LEFT);
        botHBox.setPadding(new Insets(0, 25,0, 25));

        //-----------------------------------Creating goBackButton and setting listener----------------------------
        Button goBackBtn = new Button("Go back");
        goBackBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                LOG.info("Going back to main menu.");
                MainMenu menu = new MainMenu();
                menu.start(stage);
            }
        });
        botHBox.getChildren().add(goBackBtn);
        //---------------------------------------------------------------------------------------------------------

        //-----------------Creating spacer between bottom menu button and bottom start game button-----------------
        Region reg = new Region();
        HBox.setHgrow(reg, Priority.ALWAYS);
        botHBox.getChildren().add(reg);
        //---------------------------------------------------------------------------------------------------------

        createProceedButton();
        botHBox.getChildren().add(proceedButton);
    }

    private void createProceedButton(){
        proceedButton = new Button("Start game");
        proceedButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                if(everyTeamHasKing()){
                    LOG.info("Starting new custom game.");

                    Manipulator.setCreatedPieces(created); //MainGui thread needs this to create later pieces from this data

                    CustomGame cg = new CustomGame(stage);
                    cg.doCustomGame();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Not every team has king", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });

    }

    private boolean everyTeamHasKing(){
        byte numberOfKings = 0;
        for(String[] pieceInfo : created){
            if(pieceInfo[0].equals("king")){
                numberOfKings++;
            }
        }
        return numberOfKings == 4;
    }

    private void createMiddleList(){
        middleList = new ListView();
    }

    private void createTopHbox(){
        topHBox = new HBox(15);
        topHBox.setPrefHeight(70);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setPadding(new Insets(0, 25,0, 25));

        topHBox.getChildren().add(getPieceChoice());


        Label label1 = new Label("Team\nnumber:");
        topHBox.getChildren().add(label1);
        topHBox.getChildren().add(getTeamChoice());


        Label label2 = new Label("Letter\nposition:");
        topHBox.getChildren().add(label2);
        topHBox.getChildren().add(getLetterChoice());


        Label label3 = new Label("Number\nposition:");
        topHBox.getChildren().add(label3);
        topHBox.getChildren().add(getNumberChoice());

        topHBox.getChildren().add(createCreateButton());

    }
    private ChoiceBox getPieceChoice(){
        final ChoiceBox<String> chbox = new ChoiceBox<String>(FXCollections.observableArrayList(
                allPieces[0], allPieces[1], allPieces[2], allPieces[3], allPieces[4], allPieces[5]));
        chbox.setStyle("-fx-font-size: 18px");
        chbox.getSelectionModel().selectFirst(); // allpieces[0] ("pawn") is shown by default


        chbox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {  //Return value is index of chosen piece
                        pieceSelected = allPieces[new_value.intValue()];

                    }
                });
        return chbox;
    }

    private ChoiceBox getTeamChoice(){
        final ChoiceBox<String> chbox = new ChoiceBox<String>(FXCollections.observableArrayList(
                allTeams[0], allTeams[1], allTeams[2], allTeams[3]));
        chbox.setStyle("-fx-font-size: 18px");
        chbox.getSelectionModel().selectFirst(); // allTeams[0] ("White") is shown by default


        chbox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) { //Return value is index of chosen piece
                        teamSelected = String.valueOf(new_value.intValue() + 1);
                        //Adding 1, because TeamNumbers are white-1, black-2, red-3, blue-4
                    }
                });
        return chbox;

    }
    private ChoiceBox getNumberChoice(){
        final ChoiceBox<String> chbox = new ChoiceBox<String>(FXCollections.observableArrayList(
                allNumberSigns[0], allNumberSigns[1],allNumberSigns[2],allNumberSigns[3],allNumberSigns[4],allNumberSigns[5],allNumberSigns[6],
                allNumberSigns[7],allNumberSigns[8],allNumberSigns[9],allNumberSigns[10],allNumberSigns[11],allNumberSigns[12],allNumberSigns[13]));
        chbox.setStyle("-fx-font-size: 18px");


        chbox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) { //Return value is index of chosen number
                        numberIndex = new_value.intValue();
                        selectedNumber = allNumberSigns[numberIndex];

                    }
                });
        return chbox;

    }
    private ChoiceBox getLetterChoice(){
        final ChoiceBox<String> chbox = new ChoiceBox<String>(FXCollections.observableArrayList(
                allLetterSigns[0], allLetterSigns[1],allLetterSigns[2],allLetterSigns[3],allLetterSigns[4],allLetterSigns[5],allLetterSigns[6],
                allLetterSigns[7],allLetterSigns[8],allLetterSigns[9],allLetterSigns[10],allLetterSigns[11],allLetterSigns[12],allLetterSigns[13]));
        chbox.setStyle("-fx-font-size: 18px");


        chbox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {  //Return value is index of chosen letter
                        letterIndex = new_value.intValue();
                        selectedLetter = allLetterSigns[letterIndex];
                    }
                });
        return chbox;

    }

    private Button createCreateButton(){
        confirmButton = new Button("create");
        confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                addPiece();
            }
        });

        return confirmButton;
    }

    /**
     * Creating string array with all properties of created piece and then passing this array to ArrayList with all
     * created pieces. Also showPieceInListView() method is called and new piece is displayed in ListView.
     */
    private void addPiece(){
        if(rightInput()){
            String[] pieceInfo = {pieceSelected, teamSelected, selectedNumber, selectedLetter,
                                        String.valueOf(letterIndex), String.valueOf(numberIndex)};
            created.add(pieceInfo);

            showPieceInListView();
        }
    }

    private void showPieceInListView(){
        Label label = new Label("Piece: " + created.get(numberPiecesInListView)[0] + "             "
                                + "Team: "  + allTeams[Integer.valueOf(created.get(numberPiecesInListView)[1])-1] + "             "
                                + "Letter position: " + created.get(numberPiecesInListView)[3] + "             "
                                + "Number position:" + created.get(numberPiecesInListView)[2] + "             ");

        middleList.getItems().add(label);
        numberPiecesInListView++;
    }

    /**
     * rightInput() method checks, whether:
     *      1) Every property was selected.
     *      2) Selected coordinates aren't already taken.
     *      3) Every team owns only one king.
     *      4) Selected coordinates are valid. (Corner coordinates, e.g. 1A, are not valid positions on chess board)
     * @return true if selected properties of new piece are OK. Otherwise false.
     */
    private boolean rightInput(){
        if(selectedNumber == null || selectedLetter == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Not selected number or letter", ButtonType.OK);
            alert.showAndWait();
            return false;
        }

        //Check whether the position isn't already taken
        for(String[] pieceInfo : created){
            if(selectedLetter.equals(pieceInfo[3]) && selectedNumber.equals(pieceInfo[2])){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Position already taken", ButtonType.OK);
                alert.showAndWait();
                return false;
            }
        }

        if(pieceSelected.equals("king")){
            for(String[] pieceInfo : created) {
                if (pieceInfo[0].equals("king") && pieceInfo[1].equals(teamSelected)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Only one king per team is permitted", ButtonType.OK);
                    alert.showAndWait();
                    return false;
                }
            }
        }

        if(indexesOutsideChessboard(numberIndex, letterIndex)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Corners aren't proper positions. Wrong coordniates.", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean indexesOutsideChessboard(int x, int y){
        return ((x < 3 && y < 3)||(y<3 && x>10)||(y>10 && x > 10)||(x < 3 && y >10)||(x<0)||(y<0)||(x>13)||(y>13));

    }
}
