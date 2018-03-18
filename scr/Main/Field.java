package Main;

import java.util.HashMap;

public class Field {
    protected Movable movable;
    protected String label;
    private HashMap<Direction, Field> neighbors;

    public Field(String label) {
        this.label = label;
        neighbors = new HashMap<>();
    }

    public Field GetNeighbor(Direction d) {
        Logger.funcStart("GetNeighbor", label, d.name());
        Field ret = neighbors.get(d);
        if (ret != null)
            Logger.funcEnd("GetNeighbor", label, ret.label);
        else
            Logger.funcEnd("GetMovable", label, "null");
        return ret;
    }

    public void SetNeighbor(Direction d, Field f) {
        neighbors.put(d, f);
    }

    public Movable GetMovable() {
        Logger.funcStart("GetMovable", label,"");
        Movable ret = movable;
        if (ret != null)
            Logger.funcEnd("GetMovable", label, ret.label);
        else
            Logger.funcEnd("GetMovable", label, "null");
        return ret;
    }

    public void SetMovable(Movable movable) {
        this.movable = movable;
    }

    public boolean AcceptWorker(Worker w) {
        Logger.funcStart("AcceptWorker", label, w.label);
        movable = w;
        Logger.funcEnd("AcceptWorker", label, "True");
        return true;
    }

    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        movable = b;
        Logger.funcEnd("AcceptBox", label, "True");
        return true;
    }

    public void RemoveWorker(Worker w) {
        Logger.funcStart("RemoveWorker", label, w.label);
        movable = null;
        Logger.funcEnd("RemoveWorker", label, "");
    }

    public void RemoveBox(Box b) {
        Logger.funcStart("RemoveBox", label, b.label);
        movable = null;
        Logger.funcEnd("RemoveBox", label, "");
    }

    public String getLabel() {
        return label;
    }
}
