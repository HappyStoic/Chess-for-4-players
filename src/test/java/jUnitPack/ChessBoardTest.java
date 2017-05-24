package jUnitPack;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pjv.controller.Manipulator;
import pjv.gui.game.ButtonPosition;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
/**
 * @author Martin Řepa
 * @version 1.0
 * Junit test testing if all attributes of ButtonPositions
 * on chessboard are correct (onTop, onLeft, onLefttop,...).
 *
 */
public class ChessBoardTest {
    static private ButtonPosition ButtonPosition1;
    static private ButtonPosition ButtonPosition2;
    static private ButtonPosition ButtonPosition3;
    static private ButtonPosition ButtonPosition4;
    static private ButtonPosition ButtonPosition5;
    static private ButtonPosition ButtonPosition6;

    static private Thread thread;

    @BeforeClass
    public static void init() {
        //---------------------Starting and stopping game thread just to get initialised data--------------------------
        SimulateGame sim = new SimulateGame();
        thread = new Thread(sim);
        thread.start();
        try {
            sleep(3000);  // Wait until game thread gets initialised
        } catch (Exception e){
            System.err.println("Thread got interrupted during sleep. " + e.getMessage());
        }
        //-------------------------------------------------------------------------------------------------------------
        ButtonPosition[][] allButtonPositions = Manipulator.getAllButtonPositions();
        ButtonPosition1 = allButtonPositions[2][3];
        ButtonPosition2 = allButtonPositions[7][10];
        ButtonPosition3 = allButtonPositions[4][2];
        ButtonPosition4 = allButtonPositions[5][11];
        ButtonPosition5 = allButtonPositions[11][5];
        ButtonPosition6 = allButtonPositions[0][10];
    }

    private static boolean properIndexes(int x, int y){
        return !((x < 3 && y < 3)||(y<3 && x>10)||(y>10 && x > 10)||(x < 3 && y >10));
    }

    //Start testing first case
    //--------------------------------------------------------------------------------------------------------------
    @Test
    public void testButtonPosition1OnLeft(){
        ButtonPosition output = ButtonPosition1.getOnLeft();
        assertNull("Error, testing " + ButtonPosition1.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }

    @Test
    public void testBtuttonPosition1OnLeftBottom(){
        ButtonPosition output = ButtonPosition1.getOnLeftBottom();
        assertNull("Error, testing " + ButtonPosition1.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }

    @Test
    public void testButtonPosition1OnBottom(){
        Integer okNumber = 4;
        Character okLetter = 'b';

        ButtonPosition output = ButtonPosition1.getOnBottom();

        String error = "Error, testing " + ButtonPosition1.toString() + ". OnBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }

    @Test
    public void testButtonPosition1OnRightBottom(){
        Integer okNumber = 5;
        Character okLetter = 'b';

        ButtonPosition output = ButtonPosition1.getOnRightBottom();

        String error = "Error, testing " + ButtonPosition1.toString() + ". OnRightBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }

    @Test
    public void testButtonPosition1OnRight(){
        Integer okNumber = 5;
        Character okLetter = 'c';

        ButtonPosition output = ButtonPosition1.getOnRight();

        String error = "Error, testing " + ButtonPosition1.toString() + ". OnRight output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }

    @Test
    public void testButtonPosition1OnRightTop(){
        Integer okNumber = 5;
        Character okLetter = 'd';

        ButtonPosition output = ButtonPosition1.getOnRightTop();

        String error = "Error, testing " + ButtonPosition1.toString() + ". OnRightTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }

    @Test
    public void testButtonPosition1OnTop(){
        Integer okNumber = 4;
        Character okLetter = 'd';

        ButtonPosition output = ButtonPosition1.getOnTop();

        String error = "Error, testing " + ButtonPosition1.toString() + ". OnTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }

    @Test
    public void testButtonPosition1OnLeftTop(){
        Integer okNumber = 3;
        Character okLetter = 'd';

        ButtonPosition output = ButtonPosition1.getOnLeftTop();

        String error = "Error, testing " + ButtonPosition1.toString() + ". OnLeftTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    //End of testing first case
    //---------------------------------------------------------------------------------------------------------------
    //Start testing second case
    @Test
    public void test2positionOnLeft(){
        Integer okNumber = 10;
        Character okLetter = 'h';

        ButtonPosition output = ButtonPosition2.getOnLeft();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnLeft output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnLeftBottom(){
        Integer okNumber = 10;
        Character okLetter = 'g';

        ButtonPosition output = ButtonPosition2.getOnLeftBottom();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnLeftBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnBottom(){
        Integer okNumber = 11;
        Character okLetter = 'g';

        ButtonPosition output = ButtonPosition2.getOnBottom();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnRightBottom(){
        Integer okNumber = 12;
        Character okLetter = 'g';

        ButtonPosition output = ButtonPosition2.getOnRightBottom();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnRightBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnRight(){
        Integer okNumber = 12;
        Character okLetter = 'h';

        ButtonPosition output = ButtonPosition2.getOnRight();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnRight output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnRightTop(){
        Integer okNumber = 12;
        Character okLetter = 'i';

        ButtonPosition output = ButtonPosition2.getOnRightTop();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnRightTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnTop(){
        Integer okNumber = 11;
        Character okLetter = 'i';

        ButtonPosition output = ButtonPosition2.getOnTop();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test2positionOnLeftTop(){
        Integer okNumber = 10;
        Character okLetter = 'i';

        ButtonPosition output = ButtonPosition2.getOnLeftTop();

        String error = "Error, testing " + ButtonPosition2.toString() + ". OnLeftTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    //End of testing second case
    //---------------------------------------------------------------------------------------------------------------
    //Start testing third case
    @Test
    public void test3positionOnLeft(){
        Integer okNumber = 2;
        Character okLetter = 'e';

        ButtonPosition output = ButtonPosition3.getOnLeft();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnLeft output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnLeftBottom(){
        Integer okNumber = 2;
        Character okLetter = 'd';

        ButtonPosition output = ButtonPosition3.getOnLeftBottom();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnLeftBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnBottom(){
        Integer okNumber = 3;
        Character okLetter = 'd';

        ButtonPosition output = ButtonPosition3.getOnBottom();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnRightBottom(){
        Integer okNumber = 4;
        Character okLetter = 'd';

        ButtonPosition output = ButtonPosition3.getOnRightBottom();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnRightBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnRight(){
        Integer okNumber = 4;
        Character okLetter = 'e';

        ButtonPosition output = ButtonPosition3.getOnRight();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnRight output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnRightTop(){
        Integer okNumber = 4;
        Character okLetter = 'f';

        ButtonPosition output = ButtonPosition3.getOnRightTop();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnRightTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnTop(){
        Integer okNumber = 3;
        Character okLetter = 'f';

        ButtonPosition output = ButtonPosition3.getOnTop();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void test3positionOnLeftTop(){
        Integer okNumber = 2;
        Character okLetter = 'f';

        ButtonPosition output = ButtonPosition3.getOnLeftTop();

        String error = "Error, testing " + ButtonPosition3.toString() + ". OnLeftTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    //End of testing third case
    //---------------------------------------------------------------------------------------------------------------
    //Start testing fourth case
    @Test
    public void tes4positionOnLeft(){
        Integer okNumber = 11;
        Character okLetter = 'f';

        ButtonPosition output = ButtonPosition4.getOnLeft();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnLeft output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnLeftBottom(){
        Integer okNumber = 11;
        Character okLetter = 'e';

        ButtonPosition output = ButtonPosition4.getOnLeftBottom();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnLeftBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnBottom(){
        Integer okNumber = 12;
        Character okLetter = 'e';

        ButtonPosition output = ButtonPosition4.getOnBottom();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnRightBottom(){
        Integer okNumber = 13;
        Character okLetter = 'e';

        ButtonPosition output = ButtonPosition4.getOnRightBottom();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnRightBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnRight(){
        Integer okNumber = 13;
        Character okLetter = 'f';

        ButtonPosition output = ButtonPosition4.getOnRight();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnRight output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnRightTop(){
        Integer okNumber = 13;
        Character okLetter = 'g';

        ButtonPosition output = ButtonPosition4.getOnRightTop();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnRightTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnTop(){
        Integer okNumber = 12;
        Character okLetter = 'g';

        ButtonPosition output = ButtonPosition4.getOnTop();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes4positionOnLeftTop(){
        Integer okNumber = 11;
        Character okLetter = 'g';

        ButtonPosition output = ButtonPosition4.getOnLeftTop();

        String error = "Error, testing " + ButtonPosition4.toString() + ". OnLeftTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    //End of testing fourth case
    //---------------------------------------------------------------------------------------------------------------
    //Start testing fifth case
    @Test
    public void tes5positionOnLeft(){
        Integer okNumber = 5;
        Character okLetter = 'l';

        ButtonPosition output = ButtonPosition5.getOnLeft();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnLeft output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionOnLeftBottom(){
        Integer okNumber = 5;
        Character okLetter = 'k';

        ButtonPosition output = ButtonPosition5.getOnLeftBottom();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnLeftBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionOnBottom(){
        Integer okNumber = 6;
        Character okLetter = 'k';

        ButtonPosition output = ButtonPosition5.getOnBottom();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionRightBottom(){
        Integer okNumber = 7;
        Character okLetter = 'k';

        ButtonPosition output = ButtonPosition5.getOnRightBottom();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnRightBottom output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionOnRight(){
        Integer okNumber = 7;
        Character okLetter = 'l';

        ButtonPosition output = ButtonPosition5.getOnRight();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnRight output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionOnRightTop(){
        Integer okNumber = 7;
        Character okLetter = 'm';

        ButtonPosition output = ButtonPosition5.getOnRightTop();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnRightTopoutput is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionOnTop(){
        Integer okNumber = 6;
        Character okLetter = 'm';

        ButtonPosition output = ButtonPosition5.getOnTop();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes5positionOnLeftTop(){
        Integer okNumber = 5;
        Character okLetter = 'm';

        ButtonPosition output = ButtonPosition5.getOnLeftTop();

        String error = "Error, testing " + ButtonPosition5.toString() + ". OnLeftTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    //End of testing fifth case
    //---------------------------------------------------------------------------------------------------------------
    //Start testing sixth case
    @Test
    public void tes6positionOnLeft(){
        Integer okNumber = 10;
        Character okLetter = 'a';

        ButtonPosition output = ButtonPosition6.getOnLeft();

        String error = "Error, testing " + ButtonPosition6.toString() + ". OnLeft output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes6positionOnLeftBottom(){
        ButtonPosition output = ButtonPosition6.getOnLeftBottom();
        assertNull("Error, testing " + ButtonPosition6.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }
    @Test
    public void tes6positionOnBottom(){
        ButtonPosition output = ButtonPosition6.getOnBottom();
        assertNull("Error, testing " + ButtonPosition6.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }
    @Test
    public void tes6positionOnRightBottom(){
        ButtonPosition output = ButtonPosition6.getOnRightBottom();
        assertNull("Error, testing " + ButtonPosition6.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }
    @Test
    public void tes6positionOnRight(){
        ButtonPosition output = ButtonPosition6.getOnRight();
        assertNull("Error, testing " + ButtonPosition6.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }
    @Test
    public void tes6positionOnRightTop(){
        ButtonPosition output = ButtonPosition6.getOnRightTop();
        assertNull("Error, testing " + ButtonPosition6.toString() + ". OnLeft output is supposed to be null," +
                " but it is not.\n", output);
    }
    @Test
    public void tes6positionOnTop(){
        Integer okNumber = 11;
        Character okLetter = 'b';

        ButtonPosition output = ButtonPosition6.getOnTop();

        String error = "Error, testing " + ButtonPosition6.toString() + ". OnTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    @Test
    public void tes6positionOnLeftTop(){
        Integer okNumber = 10;
        Character okLetter = 'b';

        ButtonPosition output = ButtonPosition6.getOnLeftTop();

        String error = "Error, testing " + ButtonPosition6.toString() + ". OnLeftTop output is supposed to be " + okNumber +
                okLetter + ", not " + output.toString() + "\n";
        assertTrue(error, okNumber == output.getNumberSign() && okLetter == output.getLetterSign());
    }
    //End of testing sixth case
    //---------------------------------------------------------------------------------------------------------------
    @AfterClass
    public static void end(){
        thread.interrupt();
    }

}
