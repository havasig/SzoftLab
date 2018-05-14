package Graphics;

import Main.Field;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsField implements Drawable {

    private BufferedImage imageField;
    private Field field;
    private View view;

    public GraphicsField(Field _field) {
        field = _field;

        try {
            imageField = ImageIO.read(getClass().getResourceAsStream("/floor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g) {
        int size = view.getGridsize();
        Point pos = field.getPos();
        g.drawImage(imageField, pos.x * size, pos.y * size, size, size, null);
        GraphicsSplich.Draw(field, g, pos, size);
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }
}
