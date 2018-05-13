package Graphics;

import Main.Hole;
import Main.HoleState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsHole implements Drawable {

    private BufferedImage imageClosed;
    private BufferedImage imageOpen;
    private Hole hole;
    private View view;

    public GraphicsHole(Hole _hole) {
        hole = _hole;

        try {
            imageOpen = ImageIO.read(getClass().getResourceAsStream("/hole_open.png"));
            imageClosed = ImageIO.read(getClass().getResourceAsStream("/hole_closed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g) {
        //TODO splich
        int size = view.getGridsize();
        Point pos = hole.getPos();

        if (hole.getState() == HoleState.Open) {
            g.drawImage(imageOpen, pos.x*size, pos.y*size - imageOpen.getHeight(), size, size, null);
        } else {
            g.drawImage(imageClosed, pos.x*size, pos.y*size - imageClosed.getHeight(), size, size, null);
        }
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }
}
