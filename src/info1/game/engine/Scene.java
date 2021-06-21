package info1.game.engine;

import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.listeners.InteractiveGameObject;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Scene {

    private final String name;
    private final Map<Integer, GameObject> gameObjects = new TreeMap<>();
    private final Map<Integer, InteractiveGameObject> interactiveGO = new TreeMap<>(Collections.reverseOrder());

    public Scene(String name) {
        this.name = name;
    }

    public void addGameObject(GameObject gameObject){
        addGameObject(gameObjects.size(), gameObject);
    }

    public void addGameObject(int zIndex, GameObject gameObject) {
        if(gameObject instanceof InteractiveGameObject)
            interactiveGO.put(zIndex, (InteractiveGameObject) gameObject);

        gameObjects.put(zIndex, gameObject);
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects.forEach((i, go) -> {
            if(gameObject.equals(go)) {
                gameObjects.remove(i);

                if(go instanceof InteractiveGameObject) gameObjects.remove(i);
            }
        });
    }

    public Map<Integer, GameObject> getGameObjects(){ return gameObjects; }
    public Map<Integer, InteractiveGameObject> getInteractiveGO() { return interactiveGO; }

}
