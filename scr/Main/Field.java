package Main;

import java.util.Map;

public class Field {
    public enum Direction{
        Left,
        Right,
        Up,
        Down
    }

    private Map<Direction, Field> neighbors;

    public boolean AcceptWorker(Worker w){
       return false;
    }

    public boolean AcceptBox(Box b){
       return false;
    }

    public void RemoveWorker(Worker w){

    }

    public void RemoveBox(){

    }

    public Field GetNeighbor(Direction d){
        return null;
    }

    public void SetNeighbor(Direction d, Field f){

    }

    public Movable GetMovable(){
        return null;
    }


}
