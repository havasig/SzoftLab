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
        int size = view.getGridsize();

        //TODO: Is this too much indirection or just OOP? Gfield -> View -> Game -> Factory
        String[] pos = view.getCoords(destination).split(":");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        g.drawImage(imageDestination, x, y - imageDestination.getHeight(), size, size, null);
    }
}