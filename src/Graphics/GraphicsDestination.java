package Graphics;

import Main.Destination;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsDestination implements Drawable {

    private BufferedImage imageDestination;
    private Destination destination;
    private View view;

    public GraphicsDestination(Destination _destination) {
        destination = _destination;

        try {
            imageDestination = ImageIO.read(new File("resources/destination.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void Draw(Graphics g) {
        int size = view.getGridsize();
        Point pos = destination.getPos();
        g.drawImage(imageDestination, pos.x * size, pos.y * size, size, size, null);
        GraphicsSplich.Draw(destination, g, pos, size);
    }
}