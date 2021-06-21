package info1;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.Input;

import javax.swing.*;
import java.awt.*;

public class Game {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();

        Scene menu = Scenes.MENU.getScene();


        Input input = new Input(engine, 50, 50, 300, 50);
        Button button = new Button(engine, 200, 200, 100, 30, "GO", new JLabel().getFont());
        menu.addGameObject(input);
        menu.addGameObject(button);


        engine.start(menu);

    }

}
