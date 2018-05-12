package Graphics;

import Main.Factory;
import Main.Hole;
import Main.HoleState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsHole implements Drawable{

    private BufferedImage imageClosed;
    private BufferedImage imageOpen;
    private Hole hole;

    private View view;

    GraphicsHole(Hole _hole, View _view)
    {
        hole = _hole;
        view = _view;

        try
        {
            imageOpen = ImageIO.read(getClass().getResourceAsStream("/hole_open.png"));
            imageClosed = ImageIO.read(getClass().getResourceAsStream("/hole_closed.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        int size = view.getGridsize();
        Point pos = hole.getPos();

        if(hole.getState()==HoleState.Open)
        {
            g.drawImage(imageOpen, pos.x, pos.y - imageOpen.getHeight(), size, size, null);
        }
        else
        {
            g.drawImage(imageClosed, pos.x, pos.y - imageClosed.getHeight(), size, size, null);
        }
    }
}
