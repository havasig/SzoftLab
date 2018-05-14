package Graphics;

import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {

    private boolean gridSizeCalced = false;
    private Game game;
    private ArrayList<Drawable> fields;
    private ArrayList<Drawable> movables;
    private JFrame window;

    //windowheight and width
    private int height = 600;
    private int width = 600;

    //width and height of a cell in the grid
    private int gridsize;

    public View(Game _game, JFrame _window) {
        this.game = _game;
        fields = new ArrayList<Drawable>();
        movables = new ArrayList<Drawable>();
        window = _window;
        init();
    }

    private void init() {
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

    private void calcGrid() {

        int h = height / game.getMap().getHeight();
        int w = width / game.getMap().getWidth();

        //Always use the smaller size for the grid
        gridsize = h > w ? w : h;
    }

    int getGridsize() {
        if (!gridSizeCalced) {
            calcGrid();
            gridSizeCalced = true;
        }
        return gridsize;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable field : fields)
            field.Draw(g);
        for (Drawable movable : movables)
            movable.Draw(g);
    }

    public void validate() {
        window.repaint();
    }

}
