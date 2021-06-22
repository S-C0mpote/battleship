package info1.game.engine;

import info1.game.Game;
import info1.game.engine.gameobjects.GameObject;
import info1.game.engine.listeners.InteractiveGameObject;

import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Scene {

    private final String name;

    private Map<Integer, GameObject> gameObjects = new TreeMap<>();
    private Map<Integer, InteractiveGameObject> interactiveGO = new TreeMap<>(Collections.reverseOrder());

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

    public void disableListeners(){
        for(GameObject go : this.getGameObjects().values()){
            if(go instanceof KeyListener){
                Game.engine.getGameCanvas().removeKeyListener((KeyListener) go);
            }
        }
    }
    public void enableListeners(){
        for(GameObject go : this.getGameObjects().values()){
            if(go instanceof KeyListener){
                Game.engine.getGameCanvas().addKeyListener((KeyListener) go);
            }
        }
    }
    synchronized public void removeGameObject(GameObject gameObject) {
        Map<Integer, GameObject> gameObjectsSorted = new TreeMap<>(gameObjects);
        Map<Integer, InteractiveGameObject> interactiveGOSorted = new TreeMap<>(interactiveGO);

        gameObjects.forEach((i, go) -> {
            if(gameObject.equals(go)) {
                gameObjectsSorted.remove(i);

                if(go instanceof InteractiveGameObject) interactiveGOSorted.remove(i);
            }
        });

        gameObjects = gameObjectsSorted;
        interactiveGO = interactiveGOSorted;
    }

    public Map<Integer, GameObject> getGameObjects(){ return gameObjects; }
    public Map<Integer, InteractiveGameObject> getInteractiveGO() { return interactiveGO; }

}
