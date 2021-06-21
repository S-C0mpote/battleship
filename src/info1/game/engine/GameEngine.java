package info1.game.engine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameEngine {

    private final int WIDTH = 1280, HEIGHT = 720;
    private final GameCanvas gameCanvas = new GameCanvas(WIDTH, HEIGHT);
    private final JFrame window = new JFrame("BattleShip");

    private boolean running = true;
    private Scene scene = null;

    public GameEngine() {
        window.add(gameCanvas);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(Scene startScene) {
        gameCanvas.init();
        this.scene = startScene;

        long current, last = System.currentTimeMillis();
        double delta, accumulator = 0;
        int frames = 0;

        while (running) {
            current = System.currentTimeMillis();
            delta = (current - last) / 1000d;
            last = System.currentTimeMillis();

            accumulator += delta;

            while (accumulator >= 1) {
                accumulator--;
                System.out.println("FPS: " + frames);
                window.setTitle("BattleShip - " + frames + " FPS");
                frames = 0;
            }

            frames++;

            update(delta);
            draw();

            try {
                Thread.sleep(1); // To bypass overheat
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void update(double delta) {
        List<GameObject> gameObjects = this.scene.getGameObjects();

        for (GameObject gameObject : gameObjects) {
            gameObject.update(delta);
        }
    }

    public void draw() {
        List<GameObject> gameObjects = this.scene.getGameObjects();

        Graphics2D g2d = gameCanvas.getGraphics2D();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(g2d);
        }

        gameCanvas.getBufferStrategy().show();
    }

}
