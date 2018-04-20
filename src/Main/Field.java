package Main;

import java.util.HashMap;

public class Field implements Drawable {
    protected Movable movable;
    private HashMap<Direction, Field> neighbors;
    protected FieldState splich;

    public Field() {
        neighbors = new HashMap<>();
        splich = FieldState.None;
    }

    public Field GetNeighbor(Direction d) {
        return neighbors.get(d);
    }

    public void SetNeighbor(Direction d, Field f) {
        neighbors.put(d, f);
    }

    public Movable GetMovable() {
        return movable;
    }

    public void setMovable(Movable movable) {
        this.movable = movable;
    }

    public boolean AcceptWorker(Worker w) {
        movable = w;
        return true;
    }

    public boolean AcceptBox(Box b) {
        movable = b;
        return true;
    }

    public void RemoveWorker(Worker w) {
        movable = null;
    }

    public void RemoveBox(Box b) {
        movable = null;
    }

    public FieldState getSplich() {
        return splich;
    }

    public void setSplich(FieldState splich) {
        this.splich = splich;
    }

    @Override
    public String Draw() {
        StringBuilder field = new StringBuilder();
        field.append("_");
        if (movable == null)
            field.append("_");
        else
            field.append(movable.Draw());
        DrawSplich(field);
        return field.toString();
    }

     protected void DrawSplich(StringBuilder field) {
        switch (splich){
            case Oil:
                field.append("O");
                break;
            case None:
                field.append("_");
                break;
            case Honey:
                field.append("M");
                break;
        }
    }

    public enum FieldState {
        Honey(1),
        Oil(-1),
        None(0);

        private int value;

        private FieldState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
