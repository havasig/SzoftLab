package Graphics;

import Main.Factory;
import Main.Column;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsColumn implements Drawable{

    private BufferedImage imageColumn;
    private Column column;

    private View view;

    GraphicsColumn(Column _column, View _view)
    {
        column = _column;
        view = _view;

        try
        {
            imageColumn = ImageIO.read(getClass().getResourceAsStream("/column.png"));
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
        String[] pos = view.getCoords(column).split(":");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        g.drawImage(imageColumn, x, y - imageColumn.getHeight(), size, size, null);
    }
}
