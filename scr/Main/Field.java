package Main;

import java.util.HashMap;

public class Field {
    private HashMap<Direction, Field> neighbors;
    protected Movable movable;

    public Field() {
        neighbors = new HashMap<>();
    }

    public Field GetNeighbor(Direction d){
        return neighbors.get(d);
    }

    public void SetNeighbor(Direction d, Field f){
        neighbors.put(d,f);
    }

    public Movable GetMovable(){
        return movable;
    }

    public void setMovable(Movable movable) {
        this.movable = movable;
    }

    public boolean AcceptWorker(Worker w){
        movable = w;
        return true;
    }

    public boolean AcceptBox(Box b){
        movable = b;
        return true;
    }

    public void RemoveWorker(Worker w){
        movable = null;
    }

    public void RemoveBox(Box b){
        movable = null;
    }
}
