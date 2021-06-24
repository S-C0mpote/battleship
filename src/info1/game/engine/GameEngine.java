package info1.game.engine;

import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.listeners.MouseListenerManager;
import info1.game.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class GameEngine {

    private final int WIDTH = 1280, HEIGHT = 720;
    private final int FPS_LIMIT = 60;
    private final GameCanvas gameCanvas = new GameCanvas(WIDTH, HEIGHT);
    private final JFrame window = new JFrame("BattleShip");
    private final MouseListenerManager mouseListener;
    private NetworkManager network;

    private boolean running = true;
    private Scene scene = null;
    private Point mousePosition = gameCanvas.getMousePosition();

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
        setScene(startScene);

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

            if (currentTime >= 1.0 / FPS_LIMIT) {
                currentTime %= 1.0 / FPS_LIMIT;
                frameCount++;

                mousePosition = gameCanvas.getMousePosition();
                mouseListener.updateMousePosition();

                draw();
            }

            if (frameTime >= 1) {
                window.setTitle("BattleShip - " + frameCount + "FPS");

                frameTime = 0;
                frameCount = 0;
            }

            try { Thread.sleep(1); } catch (Exception ignored) {}
        }
    }

    synchronized public void update(double delta)  {
        if(network != null) network.update();

        for (GameObject gameObject : new ArrayList<>(scene.getGameObjects().values()))
            gameObject.update(delta);
    }

    synchronized public void draw() {
        Graphics2D g2d = gameCanvas.getGraphics2D();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        for (GameObject gameObject : new ArrayList<>(scene.getGameObjects().values()))
            gameObject.draw(g2d);

        gameCanvas.getBufferStrategy().show();
    }

    public void setScene(Scene scene) {
        if(this.scene != null) this.scene.disableListeners();
        this.scene = scene;
        this.scene.enableListeners();
    }

    public void setNetwork(NetworkManager network) { this.network = network; }
    public NetworkManager getNetwork() { return network; }
    public GameCanvas getGameCanvas() { return gameCanvas; }
    public Scene getCurrentScene() { return scene; }
    public Point getMousePosition() { return mousePosition; }
}
