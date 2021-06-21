package info1.game.engine;

import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.listeners.MouseListenerManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameEngine {

    private final int WIDTH = 1280, HEIGHT = 720;
    private final int FPS_LIMIT = 60;
    private final GameCanvas gameCanvas = new GameCanvas(WIDTH, HEIGHT);
    private final JFrame window = new JFrame("BattleShip");
    private final MouseListenerManager mouseListener;

    private boolean running = true;
    private Scene scene = null;

    public GameEngine() {
        window.add(gameCanvas);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation du MouseListenerManager
        mouseListener = new MouseListenerManager(this);
        gameCanvas.addMouseListener(mouseListener);
    }

    public void start(Scene startScene) {
        gameCanvas.init();

        this.scene = startScene;

        double frameCount = 0;
        double firstTime, frameTime = 0;
        double lastTime = System.nanoTime() / 1e9;
        double currentTime = 0.0;

        while(running) {
            firstTime = lastTime;
            lastTime = System.nanoTime() / 1e9;
            frameTime += lastTime - firstTime;
            currentTime += lastTime - firstTime;

            update((lastTime - firstTime) * 1000);

            while (currentTime >= 1.0 / FPS_LIMIT) {
                currentTime -= 1.0 / FPS_LIMIT;
                frameCount++;

                draw();
                mouseListener.updateMousePosition();
            }

            if (frameTime >= 1) {
                window.setTitle("BattleShip - " + frameCount + "FPS");

                frameTime = 0;
                frameCount = 0;
            }
        }
    }

    public void update(double delta) {
        Map<Integer, GameObject> gameObjects = this.scene.getGameObjects();
        gameObjects.forEach((i, go) -> go.update(delta));
    }

    public void draw() {
        Map<Integer, GameObject> gameObjects = this.scene.getGameObjects();

        Graphics2D g2d = gameCanvas.getGraphics2D();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        gameObjects.forEach((i, go) -> go.draw(g2d));

        gameCanvas.getBufferStrategy().show();
    }

    public GameCanvas getGameCanvas() { return gameCanvas; }
    public Scene getCurrentScene() { return scene; }
}
