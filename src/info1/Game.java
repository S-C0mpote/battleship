package info1;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.Input;
import info1.game.engine.gameobjects.MenuBackground;
import info1.game.engine.utils.Vector2D;

import javax.swing.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        GameEngine engine = new GameEngine();
        Scene menu = Scenes.MENU.getScene();

        Input input = new Input(300, 50);
        input.setPosition(new Vector2D(200, 200));

        Button startButton = new Button(300, 50, "Play", engine.getGameCanvas().getFont());
        Button startButton2 = new Button(300, 50, "Play", engine.getGameCanvas().getFont());
        startButton.setPosition(new Vector2D(300, 300));
        startButton2.setPosition(new Vector2D(350, 320));

        MenuBackground menuBackground = new MenuBackground();

        menu.addGameObject(-1, menuBackground);
        menu.addGameObject(startButton);
        menu.addGameObject(startButton2);
        menu.addGameObject(input);

        engine.start(menu);
    }

}
