package Graphics;

import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class View {

    private ArrayList<Drawable> fields;
    private ArrayList<Drawable> movables;
    BufferedImage buffer;
    Boolean valid;
    Game game;

    //windowheight and width
    private int height;
    private int width;

    //width and height of a cell in the grid
    private int gridsize;

    public View(Game _game) {
        game = _game;
        valid = false;
        fields = new ArrayList<Drawable>();
        movables = new ArrayList<Drawable>();
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
        return gridsize;
    }

    //TODO
    public void paintComponent(Graphics g) {

    }

    //TODO
    public void clear() {
    }

    //TODO
    public void validate() {
        valid = false;
    }

    public void Draw() {
        if (!valid) {
            //TODO
            for (Drawable field : fields) /*field.Draw(g)*/ ;
            for (Drawable movable : movables) /*movable.Draw(g)*/ ;
            valid = true;
        }
    }


}
