package info1.game.engine;

import java.util.Map;
import java.util.TreeMap;

public class Scene {


    private final String name;
    private final Map<Integer, GameObject> gameObjects = new TreeMap<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.put(gameObjects.size(), gameObject);
    }

    public void addGameObject(int zIndex, GameObject gameObject) {
        gameObjects.put(zIndex, gameObject);
    }

    public Map<Integer, GameObject> getGameObjects(){
        return gameObjects;
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects.forEach((i, go) -> {
            if(gameObject.equals(go)) gameObjects.remove(i);
        });
    }
}
