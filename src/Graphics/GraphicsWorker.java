package Graphics;

import Main.Worker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsWorker implements Drawable {
    private Worker worker;
    private View view;
    private BufferedImage workerImage;

    public GraphicsWorker(Worker w)
    {
        worker = w;
        try {
            int i = worker.getIdentifier();
            if (i > 5 || i < 0)
                throw new IllegalArgumentException("Worker id fault");
            workerImage = ImageIO.read(getClass().getResourceAsStream("/worker" + Integer.toString(i) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g) {
        int size = view.getGridsize();
        Point pos = worker.getPos();
        g.drawImage(workerImage, pos.x*size, pos.y*size - workerImage.getHeight(), size, size, null);
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }
}
