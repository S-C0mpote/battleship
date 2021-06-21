package info1;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.*;

public class Game {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();

        Scene menu = Scenes.MENU.getScene();


        Input input = new Input(engine, 50, 50, 300, 50);
        menu.addGameObject(input);
        Label label1 = new Label(engine, 1150, 20, 220, 50);
        menu.addGameObject(label1);
        Label label2 = new Label(engine, 1150, 700, 220, 50);
        menu.addGameObject(label2);

        engine.start(menu);
    }

}
