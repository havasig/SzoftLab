package Graphics;

import Main.Factory;
import Main.Box;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsBox implements Drawable{

    private BufferedImage imageFree;
    private BufferedImage imageLocked;
    private Box box;

    private View view;

    GraphicsBox(Box _box, View _view)
    {
        box = _box;
        view = _view;

        try
        {
            imageFree = ImageIO.read(getClass().getResourceAsStream("/box.png"));
            imageLocked = ImageIO.read(getClass().getResourceAsStream("/box_locked.png"));
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
        String[] pos = view.getCoords(box).split(":");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        if(!box.getLocked())
        {
            g.drawImage(imageFree, x, y - imageFree.getHeight(), size, size, null);
        }
        else
        {
            g.drawImage(imageLocked, x, y - imageLocked.getHeight(), size, size, null);
        }
    }
}
