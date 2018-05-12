package Graphics;

import Main.Factory;
import Main.Destination;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsDestination implements Drawable{

    private BufferedImage imageDestination;
    private Destination destination;

    private View view;

    GraphicsDestination(Destination _destination, View _view)
    {
        destination = _destination;
        view = _view;

        try
        {
            imageDestination = ImageIO.read(getClass().getResourceAsStream("/destination.png"));
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
        Point pos = destination.getPos();
        g.drawImage(imageDestination, pos.x, pos.y - imageDestination.getHeight(), size, size, null);
    }
}