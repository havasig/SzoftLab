package Graphics;

import Main.Box;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsBox implements Drawable {

    private BufferedImage imageFree;
    private BufferedImage imageLocked;
    private Box box;

    private View view;

    public GraphicsBox(Box _box) {
        box = _box;
        try {
            imageFree = ImageIO.read(getClass().getResourceAsStream("/box.png"));
            imageLocked = ImageIO.read(getClass().getResourceAsStream("/box_locked.png"));
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
        Point pos = box.getPos();

        if (!box.getLocked()) {
            g.drawImage(imageFree, pos.x*size + (int)(size*0.1), pos.y*size  + (int)(size*0.1), (int)(size*0.8), (int)(size*0.8), null);
        } else {
            g.drawImage(imageLocked, pos.x*size + (int)(size*0.1), pos.y*size  + (int)(size*0.1), (int)(size*0.8), (int)(size*0.8), null);
        }
    }
}
