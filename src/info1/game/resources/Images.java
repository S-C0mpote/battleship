package info1.game.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Images {

    public static BufferedImage BUTTON_YELLOW;
    public static BufferedImage BUTTON_YELLOW_OVER;
    public static BufferedImage BUTTON_YELLOW_PRESS;
    public static BufferedImage BUTTON_RED;
    public static BufferedImage BUTTON_RED_OVER;
    public static BufferedImage BUTTON_RED_PRESS;

    public static BufferedImage MENU_LOGO;

    public static BufferedImage RIGHT_CLICK;
    public static BufferedImage LEFT_CLICK;

    public static BufferedImage SHIP_SUBMARINE;
    public static BufferedImage SHIP_DESTROYER;
    public static BufferedImage SHIP_CRUISER;
    public static BufferedImage SHIP_BATTLESHIP;
    public static BufferedImage SHIP_AIRCRAFT_CARRIER;

    public static BufferedImage HIT;
    public static BufferedImage MISS;

    static {
        try {
            BUTTON_YELLOW = ImageIO.read(new File("assets/ui/buttons/yellow_button.png"));
            BUTTON_YELLOW_OVER = ImageIO.read(new File("assets/ui/buttons/yellow_button_over.png"));
            BUTTON_YELLOW_PRESS = ImageIO.read(new File("assets/ui/buttons/yellow_button_press.png"));
            BUTTON_RED = ImageIO.read(new File("assets/ui/buttons/red_button.png"));
            BUTTON_RED_OVER = ImageIO.read(new File("assets/ui/buttons/red_button_over.png"));
            BUTTON_RED_PRESS = ImageIO.read(new File("assets/ui/buttons/red_button_press.png"));

            MENU_LOGO = ImageIO.read(new File("assets/ui/logo.png"));

            RIGHT_CLICK = ImageIO.read(new File("assets/ui/right_click.png"));
            LEFT_CLICK = ImageIO.read(new File("assets/ui/left_click.png"));

            SHIP_SUBMARINE = ImageIO.read(new File("assets/ships/submarine.png"));
            SHIP_DESTROYER = ImageIO.read(new File("assets/ships/destroyer.png"));
            SHIP_CRUISER = ImageIO.read(new File("assets/ships/cruiser.png"));
            SHIP_BATTLESHIP = ImageIO.read(new File("assets/ships/battleship.png"));
            SHIP_AIRCRAFT_CARRIER = ImageIO.read(new File("assets/ships/aircraft-carrier.png"));

            HIT = ImageIO.read(new File("assets/ui/hit.png"));
            MISS = ImageIO.read(new File("assets/ui/miss.png"));
        } catch (IOException e) {e.printStackTrace();}
    }

}
