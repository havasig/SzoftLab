package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

/**
 *  Léptethető objektum. A játékosok tologatják. Ha a helyére kerül akkor már nem lehet
 *  mozgatni.
 *
 *  Movable → Box
 */
public class Box extends Movable {
    private boolean locked;

    private Movable movable;


    /**
     * A Box osztály konsturktora.
     * @param startField Az objektum kezdő mezője
     * @param label A logger osztály segéd stringje
     */
    public Box(Field startField, String label) {
        super(label);
        field = startField;
        startField.SetMovable(this);
        locked = false;
    }

    /**
     * ​ Megszünteti a Box objektumot.
     */
    @Override
    public void Die() {
        Logger.funcStart("Die", label, "");
        field.RemoveBox(this);
        Logger.funcEnd("Die", label, "");
    }

    /**
     * ​ Ez a függvény kezeli a Worker objektum által
     * indított ütközést a Box objektummal.
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
    @Override
    public Movement CollideWorker(Direction d) {
        Logger.funcStart("CollideWorker", label, d.name());
        Movement ret = CollideMovable(d);
        Logger.funcEnd("CollideWorker", label, ret.name());
        return ret;
    }

    /**
     * ​ Ez a függvény kezeli a Box objektum által
     * indított ütközést a Box objektummal.
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
    @Override
    public Movement CollideBox(Direction d) {
        Logger.funcStart("CollideBox", label, d.name());
        Movement ret = CollideMovable(d);
        Logger.funcEnd("CollideBox", label, ret.name());
        return ret;
    }

    /**
     * A Collide függvények duplikált részét tartalmazza
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
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


    /**
     * ​ Az objektumot egyik mezőről egy másikra próbálja
     * mozgatni. Ha sikeres a mozgás akkor true értéket ad vissza.
     * @param f A következő mező, amire lépni fog
     * @return Sikerült-e a lépés, annak megfelelő a visszatérési érték
     */
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

    /**
     * ​ Lezárja a dobozt. Ez után már a doboz nem mozgatható
     */
    public void Lock() {
        Logger.funcStart("Lock", label, "");
        Game.getInstance().SetPoint();
        locked = true;
        Logger.funcEnd("Lock", label, "");
    }

}
