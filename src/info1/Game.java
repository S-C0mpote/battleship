package info1;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import javax.swing.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        GameEngine engine = new GameEngine();

        Scene menu = Scenes.MENU.getScene();



        //Leo Tests
        Button button = new Button(engine, 50, 50, 300, 50, "GO", new JLabel().getFont());
        menu.addGameObject(button);
        engine.getGameCanvas().addMouseListener(button);

        engine.start(menu);
    }

}
