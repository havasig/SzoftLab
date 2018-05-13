package Graphics;

import Main.Field;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsSplich {
    private static BufferedImage imageHoney;
    private static BufferedImage imageOil;

    static {
        try {
            imageHoney = ImageIO.read(new File("resources/honey.png"));
            imageOil = ImageIO.read(new File("resources/oil.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void Draw(Field field, Graphics g, Point pos, int size) {
        Field.FieldState state = field.getSplich();
        switch (state) {
            case Honey:
                g.drawImage(imageHoney, pos.x*size, pos.y*size , size, size, null);
                break;
            case Oil:
                g.drawImage(imageOil, pos.x*size, pos.y*size , size, size, null);
                break;
            case None:
                break;
        }
    }

}
