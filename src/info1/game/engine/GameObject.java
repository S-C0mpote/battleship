package info1.game.engine;

import java.awt.*;

public interface GameObject {

    /**
    * Appellé quand on doit mettre à jour l'objet
     * @param delta différence de temps entre les frames
    */
    void update(double delta);

    /**
    * Appellé quand on doit dessiner le game object
    * @param g2d le truc pour dessiner je suppose
    */
    void draw(Graphics2D g2d);

    /**
    * Initialise la scène donnée
    * @param engine la scène à initialiser
    */
    void init(GameEngine engine);

}
