package info1.game.engine;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private String name;
    private List<GameObject> gameObjects = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public List<GameObject> getGameObjects(){
        return gameObjects;
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects.removeIf(go -> go.equals(gameObject));
    }
}
