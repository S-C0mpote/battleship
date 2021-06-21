package info1.game.engine;

public enum Scenes {
    MENU(new Scene("Menu")), IN_GAME(new Scene("Jeu"));

    private final Scene scene;

    Scenes(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }
}
