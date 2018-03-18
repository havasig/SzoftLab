package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

public class Box extends Movable {
    private boolean locked;

    private Movable movable;

    public Box(Field startField, String label) {
        super(label);
        field = startField;
        startField.SetMovable(this);
        locked = false;
    }

    @Override
    public void Die() {
        Logger.funcStart("Die", label, "");
        field.RemoveBox(this);
        Logger.funcEnd("Die", label, "");
    }

    @Override
    public Movement CollideWorker(Direction d) {
        Logger.funcStart("CollideWorker", label, d.name());
        Movement ret = CollideMovable(d);
        Logger.funcEnd("CollideWorker", label, ret.name());
        return ret;
    }

    @Override
    public Movement CollideBox(Direction d) {
        Logger.funcStart("CollideBox", label, d.name());
        Movement ret = CollideMovable(d);
        Logger.funcEnd("CollideBox", label, ret.name());
        return ret;
    }

    private Movement CollideMovable(Direction d) {
        Logger.funcStart("CollideMovable", label, d.name());
        if (!locked) {
            Field nextField = field.GetNeighbor(d);
            Movable m = nextField.GetMovable();
            if (m != null) {
                Movement state = m.CollideBox(d);
                if (state == Moved) {
                    Move(nextField);
                    Logger.funcEnd("CollideMovable", label, "Moved");
                    return Moved;
                }
            } else {
                boolean b_state;
                b_state = Move(nextField);
                if (b_state) {
                    Logger.funcEnd("CollideMovable", label, "Moved");
                    return Moved;
                }
            }
        }
        Logger.funcEnd("CollideMovable", label, "Stayed");
        return Stayed;
    }

    @Override
    public boolean Move(Field f) {
        Logger.funcStart("Move", label, f.label);
        if (f.AcceptBox(this)) {
            field.RemoveBox(this);
            field = f;
            Logger.funcEnd("CollideMovable", label, "True");
            return true;
        } else {
            Logger.funcEnd("CollideMovable", label, "False");
            return false;
        }
    }

    public void Lock() {
        Logger.funcStart("Lock", label, "");
        Game.getInstance().SetPoint();
        locked = true;
        Logger.funcEnd("Lock", label, "");
    }

}
