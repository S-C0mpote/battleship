package info1.game.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Images {

    public static BufferedImage BUTTON_YELLOW;
    public static BufferedImage BUTTON_YELLOW_OVER;
    public static BufferedImage BUTTON_YELLOW_PRESS;

    public static BufferedImage MENU_LOGO;

    static {
        try {
            BUTTON_YELLOW = ImageIO.read(new File("assets/ui/buttons/yellow_button.png"));
            BUTTON_YELLOW_OVER = ImageIO.read(new File("assets/ui/buttons/yellow_button_over.png"));
            BUTTON_YELLOW_PRESS = ImageIO.read(new File("assets/ui/buttons/yellow_button_press.png"));
            MENU_LOGO = ImageIO.read(new File("assets/ui/logo.png"));
        } catch (IOException e) {e.printStackTrace();}
    }

}
