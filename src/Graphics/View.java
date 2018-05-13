package Graphics;

import Main.Game;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class View extends JPanel {

    private ArrayList<Drawable> fields;
    private ArrayList<Drawable> movables;
    BufferedImage buffer;
    boolean valid = false;
    boolean gridSizeCalced = false;
    Game game;

    private JFrame window;

    //windowheight and width
    private int height = 600;
    private int width = 600;

    //width and height of a cell in the grid
    private int gridsize;

    public View(Game _game, JFrame _window) {
        game = _game;
        valid = false;
        fields = new ArrayList<Drawable>();
        movables = new ArrayList<Drawable>();
        window = _window;
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        window.add(this);
        setFocusable(true);
        requestFocus();
        window.pack();
    }

    public void AddField(Drawable field) {
        field.setView(this);
        fields.add(field);
    }

    public void AddMovable(Drawable mov) {
        mov.setView(this);
        movables.add(mov);
    }

    public void calcGrid() {

        int h = height / game.getMap().getHeight();
        int w = width / game.getMap().getWidth();

        //Always use the smaller size for the grid
        gridsize = h > w ? w : h;
    }

    public int getGridsize() {
        if (!gridSizeCalced) {
            calcGrid();
            gridSizeCalced =true;
        }
        return gridsize;
    }

    //TODO
    public void paintComponent(Graphics g) {
        if (!valid) {
            super.paintComponent(g);
            for (Drawable field : fields)
                field.Draw(g);
            for (Drawable movable : movables)
                movable.Draw(g);
             //valid = true; //<-- ezzel vmi szar van
        }
        g.drawString("BLAH", 20, 20);
    }

    //TODO ?Unnecessary? - This is done by super.paint(g) in paintComponent
    public void clear() {}

    public void validate() {
        valid = false;
        window.repaint();
    }

    //TODO ?Unnecessary?
    public void Draw() { }
}
