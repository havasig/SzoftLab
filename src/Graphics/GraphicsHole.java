package Graphics;

import Main.Hole;
import Main.HoleState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsHole implements Drawable {

    private BufferedImage imageClosed;
    private BufferedImage imageOpen;
    private Hole hole;
    private View view;

    public GraphicsHole(Hole _hole) {
        hole = _hole;

        try {
            imageOpen = ImageIO.read(new File("resources/hole_open.png"));
            imageClosed = ImageIO.read(new File("resources/hole_closed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g) {
        int size = view.getGridsize();
        Point pos = hole.getPos();

        if (hole.getState() == HoleState.Open) {
            g.drawImage(imageOpen, pos.x * size, pos.y * size, size, size, null);
        } else {
            g.drawImage(imageClosed, pos.x * size, pos.y * size, size, size, null);
            GraphicsSplich.Draw(hole, g, pos, size);
        }

    }

    @Override
    public void setView(View view) {
        this.view = view;
    }
}
