package Graphics;

import Main.Switch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsSwitch implements Drawable {

    private BufferedImage imageSwitchOff;
    private BufferedImage imageSwitchOn;
    private Switch switch_;

    private View view;

    GraphicsSwitch(Switch _switch, View _view) {
        switch_ = _switch;
        view = _view;

        try {
            imageSwitchOff = ImageIO.read(getClass().getResourceAsStream("/switch_off.png"));
            imageSwitchOn = ImageIO.read(getClass().getResourceAsStream("/switch_on.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Draw(Graphics g) {
        int size = view.getGridsize();
        Point pos = switch_.getPos();

        if (!switch_.getBox()) {
            g.drawImage(imageSwitchOff, pos.x, pos.y - imageSwitchOff.getHeight(), size, size, null);
        } else {
            g.drawImage(imageSwitchOn, pos.x, pos.y - imageSwitchOn.getHeight(), size, size, null);
        }
    }
}
