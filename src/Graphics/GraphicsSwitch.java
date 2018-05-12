package Graphics;

import Main.Factory;
import Main.Switch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsSwitch implements Drawable{

    private BufferedImage imageSwitchOff;
    private BufferedImage imageSwitchOn;
    private Switch switch_;

    private View view;

    GraphicsSwitch(Switch _switch, View _view)
    {
        switch_ = _switch;
        view = _view;

        try
        {
            imageSwitchOff = ImageIO.read(getClass().getResourceAsStream("/switch_off.png"));
            imageSwitchOn = ImageIO.read(getClass().getResourceAsStream("/switch_on.png"));
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
        String[] pos = view.getCoords(switch_).split(":");
        int x = Integer.parseInt(pos[0]);
        int y = Integer.parseInt(pos[1]);

        if (!switch_.getBox())
        {
            g.drawImage(imageSwitchOff, x, y - imageSwitchOff.getHeight(), size, size, null);
        }
        else
        {
            g.drawImage(imageSwitchOn, x, y - imageSwitchOn.getHeight(), size, size, null);
        }
    }
}
