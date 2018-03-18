package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

/**
 * A játékos irányítja. Eltolhatja a dobozokat illetve meghalhat. Ha jó helyre tol egy dobozt
 * akkor pontot szerez.
 *
 * Movable → Worker
 */
public class Worker extends Movable {
    /**
     * Tárolja a játékos pontjait.
     */
    private int points;

    /**
     * A worker osztály konstruktora
     * @param startField Az objektum kezdő mezője
     * @param label A logger osztály segéd stringje
     */
    public Worker(Field startField, String label) {
        super(label);
        field = startField;
        startField.SetMovable(this);
        points = 0;
    }

    /**
     * A játékos pontjainak növelésére szolgál
     */
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

    /**
     * ​Ez a függvény kezeli a Worker objektum
     * által indított ütközést a Worker objektummal
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
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

    /**
     *  ​Ez a függvény kezeli a Box objektum által indított
     *  ütközést a Worker objektummal
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
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

    /**
     * ​Az objektumot egyik mezőről egy másikra próbálja
     * mozgatni. Ha sikeres a mozgás akkor true értéket ad vissza.
     * @param f A következő mező, amire lépni fog
     * @return Sikerült-e a lépés, annak megfelelő a visszatérési érték
     */
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

    /**
     * A játékos által irányított munkás vezérléséért felel
     * @param d A munkás lépésének iránya
     */
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
