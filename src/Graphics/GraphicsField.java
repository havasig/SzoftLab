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
        int size = view.getGridsize();

        //TODO: Is this too much indirection or just OOP? Gfield -> View -> Game -> Factory
        String[] pos = view.getCoords(field).split(":");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        g.drawImage(imageField, x, y - imageField.getHeight(), size, size, null);
    }
}
