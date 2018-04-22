package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

public class Worker extends Movable {
    private int points;
    private int strength;
    private boolean alive;
    private int identifier;

    public Worker(Field startField, int id) {
        field = startField;
        startField.AcceptWorker(this);
        alive = true;
        points = 0;
        strength = 5;
        this.identifier = id;
    }

    public void IncrementPoints() {
        points++;
    }

    @Override
    public void Die() {
        field.RemoveWorker(this);
        Game.getInstance().PlayerDied();
        alive = false;
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
    public Movement PseudoCollideBox(Direction d, int sumFriction) {
        return Moved;
    }

    @Override
    public Movement PseudoCollideWorker(Direction d, int sumFriction) {
        Field nextField = field.GetNeighbor(d);
        Movable m = nextField.GetMovable();
        if (m != null) {
            return m.PseudoCollideWorker(d, sumFriction);
        } else {
            return Moved;
        }
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

    public Movement IsThereMovement(Field currentField){
        //Avoid checking a field multiple times
        if (currentField.getChecked())
            return Stayed;
        currentField.setChecked(true);

        //This will be returned, it is Stayed if all of the recursive functions returned Stayed, else Moved
        Movement retMov = Stayed;

        for(Direction dir: Direction.values()){
            Field nextField = currentField.GetNeighbor(dir);
            Movable m = nextField.GetMovable();
            if (m != null)
                return m.PseudoCollideWorker(dir, strength);
            else
            {
                if(IsThereMovement(nextField) == Moved)
                    retMov = Moved;
            }
        }

        return retMov;

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

    @Override
    public String Draw() {
        return String.valueOf(identifier);
    }
}
