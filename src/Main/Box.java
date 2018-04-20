package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

public class Box extends Movable {
    private boolean locked;
    private int defFriction;

    public Box(Field startField, int friction) {
        field = startField;
        startField.setMovable(this);
        locked = false;
        this.defFriction = friction;
    }

    @Override
    public void Die() {
        field.RemoveBox(this);
    }

    @Override
    public Movement CollideWorker(Direction d, int sumFriction) {
        return CollideMovable(d, sumFriction);
    }

    @Override
    public Movement CollideBox(Direction d, int sumFriction) {
        return CollideMovable(d, sumFriction);
    }

    private Movement CollideMovable(Direction d, int sumFriction) {
        int friction = defFriction + field.getSplich().getValue();
        if (friction <= sumFriction) {
            if (!locked) {
                Field nextField = field.GetNeighbor(d);
                Movable m = nextField.GetMovable();
                if (m != null) {
                    Movement state = m.CollideBox(d, (sumFriction - friction));
                    if (state == Moved) {
                        Move(nextField);
                        return Moved;
                    }
                } else {
                    boolean b_state;
                    b_state = Move(nextField);
                    if (b_state) {
                        return Moved;
                    }
                }
            }
        }
        return Stayed;
    }

    @Override
    public boolean Move(Field f) {
        if (f.AcceptBox(this)) {
            field.RemoveBox(this);
            field = f;
            return true;
        } else {
            return false;
        }
    }

    public void Lock() {
        Game.getInstance().SetPoint();
        locked = true;
    }

    public int getFriction() {
        return defFriction;
    }

    @Override
    public String Draw() {
        if (locked)
            return "b";
        else
            return "B";
    }
}
