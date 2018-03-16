package Main;

import static Main.Movement.*;

public class Box extends Movable {
    private boolean locked;

    private Movable movable;

    public Box(Field startField) {
        field= startField;
        locked = false;
    }

    @Override
    public void Die() {
        field.RemoveBox(this);
    }

    @Override
    public Movement CollideWorker(Direction d) {
        return CollideMovable(d);
    }

    @Override
    public Movement CollideBox(Direction d) {
       return CollideMovable(d);
    }

    private Movement CollideMovable(Direction d) {
        if (!locked) {
            Field nextField = field.GetNeighbor(d);
            Movable m =nextField.GetMovable();
            if(m != null){
                Movement state = m.CollideBox(d);
                if (state == Moved) {
                    Move(nextField);
                    return Moved;
                }
            }else {
                boolean b_state;
                b_state = Move(nextField);
                if (b_state){
                    return Moved;
                }
            }
        }
        return Stayed;
    }

    @Override
    public boolean Move(Field f) {
        if(f.AcceptBox(this)){
            field.RemoveBox(this);
            field = f;
            return true;
        }else {
            return false;
        }
    }

    public void Lock(){
        Game.getInstance().SetPoint();
        locked = true;
    }

}
