package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

public class Worker extends Movable {
    private int points;

    public Worker(Field startField, String label) {
        super(label);
        field = startField;
        startField.SetMovable(this);
        points = 0;
    }

    public void IncrementPoints() {
        points++;
    }

    @Override
    public void Die() {
        Logger.funcStart("Die", label, "");
        field.RemoveWorker(this);
        Game.getInstance().PlayerDied();
        Logger.funcEnd("Die", label, "");
    }

    @Override
    public Movement CollideWorker(Direction d) {
        Logger.funcStart("CollideWorker", label, d.name());
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            Movement state = m.CollideWorker(d);
            if (state == Moved) {
                Move(nextField);
                Logger.funcEnd("CollideWorker", label, "Moved");
                return Moved;
            } else {
                Logger.funcEnd("CollideWorker", label, "Stayed");
                return Stayed;
            }
        } else {
            boolean w_state;
            w_state = Move(nextField);
            if (w_state) {
                Logger.funcEnd("CollideWorker", label, "Moved");
                return Moved;
            } else {
                Logger.funcEnd("CollideWorker", label, "Stayed");
                return Stayed;
            }
        }
    }

    @Override
    public Movement CollideBox(Direction d) {
        Logger.funcStart("CollideBox", label, d.name());
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            Movement state = m.CollideWorker(d);
            if (state == Moved) {
                Move(nextField);
            } else {
                Die();
            }
        } else {
            boolean w_state;
            w_state = Move(nextField);
            if (!w_state)
                Die();
        }
        Logger.funcEnd("CollideBox", label, "Moved");
        return Moved;
    }

    @Override
    public boolean Move(Field f) {
        Logger.funcStart("Move", label, f.label);
        if (f.AcceptWorker(this)) {
            field.RemoveWorker(this);
            field = f;
            Logger.funcEnd("Move", label, "True");
            return true;
        } else {
            Logger.funcEnd("Move", label, "False");
            return false;
        }
    }

    public void Control(Direction d) {
        Logger.funcStart("Control", label, d.name());
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            Movement state = m.CollideWorker(d);
            if (state == Moved)
                Move(nextField);
        } else
            Move(nextField);
        Logger.funcEnd("Control", label, "");
    }
}
