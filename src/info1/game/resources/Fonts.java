package info1.game.resources;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class Fonts {

    public static Font MAIN;

    static {
        try {
            MAIN = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/kenvector_future.ttf"));
            MAIN = MAIN.deriveFont(18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

}
