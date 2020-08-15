package estimation.app;

import estimation.controller.GameController;
import estimation.gui.GameView;
import estimation.model.GameModel;


/**
 * Main class to run and control the Estimation game.
 * Developed by Wan Ding Yao, Brennan, and Jeremy Sun
 * of G1T09, IS442 AY19/20 Semester 1
 */
public class GameApp {

    /**
     * Main method to start the Estimation game.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);

        controller.initView();
    }

}