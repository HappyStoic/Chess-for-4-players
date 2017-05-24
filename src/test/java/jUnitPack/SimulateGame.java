package jUnitPack;

import javafx.application.Application;
import pjv.controller.MainGame;

/**
 * @author Martin Řepa
 * @version 1.0
 * Class that runs new default chess game so other unittests might use
 * initialized data in instance of Gameinfo.
 */
public class SimulateGame implements Runnable{

    public void run() {
        try {
            Application.launch(MainGame.class, null);
        } catch (Exception e) {
            System.err.println("Simulate thread ended.");
        }
    }
}
