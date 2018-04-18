package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

public class Worker extends Movable {
    private int points;
    private int strength;
    private boolean alive;


    public Worker(Field startField) {
        field = startField;
        startField.setMovable(this);
        alive = true;
        points = 0;
        strength = 5;
    }

    public void IncrementPoints() {
        points++;
    }

    @Override
    public void Die() {
        field.RemoveWorker(this);
        Game.getInstance().PlayerDied();
    }

    @Override
    public Movement CollideWorker(Direction d, int sumFriction) {
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            Movement state = m.CollideWorker(d, sumFriction);
            if (state == Moved) {
                Move(nextField);
                return Moved;
            } else {
                return Stayed;
            }
        } else {
            boolean w_state;
            w_state = Move(nextField);
            if (w_state) {
                return Moved;
            } else {
                return Stayed;
            }
        }
    }

    @Override
    public Movement CollideBox(Direction d, int sumFriction) {
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            Movement state = m.CollideWorker(d, sumFriction);
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
        return Moved;
    }

    @Override
    public boolean Move(Field f) {
        if (f.AcceptWorker(this)) {
            field.RemoveWorker(this);
            field = f;
            return true;
        } else {
            return false;
        }
    }

    public void Control(Direction d) {
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            Movement state = m.CollideWorker(d, strength);
            if (state == Moved)
                Move(nextField);
        } else
            Move(nextField);
    }

    public boolean isAlive() {
        return alive;
    }

    public int getPoints() {
        return points;
    }

    public int getStrength() {
        return strength;
    }
}
