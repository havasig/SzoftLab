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

        //TODO: Is this too much indirection or just OOP? Gfield -> View -> Game -> Factory
        String[] pos = view.getCoords(hole).split(":");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        if(hole.getState()==HoleState.Open)
        {
            g.drawImage(imageOpen, x, y - imageOpen.getHeight(), size, size, null);
        }
        else
        {
            g.drawImage(imageClosed, x, y - imageClosed.getHeight(), size, size, null);
        }
    }
}
