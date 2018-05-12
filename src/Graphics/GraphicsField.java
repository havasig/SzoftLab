package Graphics;

import Main.Factory;
import Main.Field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsField implements Drawable{

    private BufferedImage imageField;
    private Field field;

    private View view;

    GraphicsField(Field _field, View _view)
    {
        field = _field;
        view = _view;

        try
        {
            imageField = ImageIO.read(getClass().getResourceAsStream("/floor.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        //TODO splich
        int size = view.getGridsize();
        Point pos = field.getPos();
        g.drawImage(imageField, pos.x, pos.y - imageField.getHeight(), size, size, null);
    }
}
