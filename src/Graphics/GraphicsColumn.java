package Graphics;

import Main.Column;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsColumn implements Drawable {

    private BufferedImage imageColumn;
    private Column column;

    private View view;

    public GraphicsColumn(Column _column) {
        column = _column;

        try {
            imageColumn = ImageIO.read(new File("resources/column.png"));
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
        Point pos = column.getPos();
        g.drawImage(imageColumn, pos.x*size, pos.y*size , size, size, null);
        GraphicsSplich.Draw(column, g, pos,size );
    }
}