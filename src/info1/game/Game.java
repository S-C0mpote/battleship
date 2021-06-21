package info1.game;

import info1.game.engine.GameEngine;
import info1.game.engine.Scene;
import info1.game.engine.Scenes;
import info1.game.engine.gameobjects.Button;
import info1.game.engine.gameobjects.Input;
import info1.game.engine.gameobjects.MenuBackground;
import info1.game.engine.gameobjects.TitleMenu;
import info1.game.engine.utils.Vector2D;
import info1.game.resources.Fonts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.getGameCanvas().setFont(Fonts.MAIN);

        Input input = new Input(300, 50);
        input.setPosition(new Vector2D(200, 200));

        Button startButton = new Button(300, 50, "Play", Fonts.MAIN);
        startButton.setPosition(new Vector2D(300, 300));

        MenuBackground menuBackground = new MenuBackground();
        TitleMenu title = new TitleMenu(engine);


        Scene menu = Scenes.MENU.getScene();
        menu.addGameObject(-1, menuBackground);
        menu.addGameObject(startButton);
        menu.addGameObject(input);
        menu.addGameObject(title);
        engine.start(menu);
    }

}
