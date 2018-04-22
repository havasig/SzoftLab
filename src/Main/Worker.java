package Main;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;
/**
 * A játékos irányítja. Eltolhatja a dobozokat, ha megfelelo nagysagu az ereje, illetve meghalhat.
 * Ha jó helyre tol egy dobozt, akkor pontot szerez.
 * Movable → Worker
 */
public class Worker extends Movable {
    /**
     * Tárolja a játékos pontjait.
     */
    private int points;
    /**
     * Tárolja a játékos erejet.
     */
    int strength;
    /**
     * Tárolja, hogy a játékos eletben van-e.
     */
    private boolean alive;
    /**
     * Tárolja a játékos azonositojat.
     */
    private int identifier;

    /**
     *Ez a Worker konstruktora.
     * @param startField: a Worker kezo mezeje
     * @param id: a Worker azonosotioja
     */
    public Worker(Field startField, int id) {
        field = startField;
        startField.AcceptWorker(this);
        alive = true;
        points = 0;
        strength = 5;
        this.identifier = id;
    }

    /**
     * Megnoveli a Worker pontjait eggyel.
     */
    public void IncrementPoints() {
        points++;
    }

    /**
     * A Worker meghal. Eltavolitja a mezorol a Workert, es ertesiti a halalesetrol a Game-et.
     */
    @Override
    public void Die() {
        field.RemoveWorker(this);
        Game.getInstance().PlayerDied();
        alive = false;
    }

    /**
     * A Worker egy Worker-rel valo utkozeset kezeli.
     * @param d: az irany, amelyre mozog a Worker.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Worker-nek.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */
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

    /**
     * A Worker egy Box-szal valo utkozeset kezeli.
     * @param d: az irany, amelyre mozog a Worker.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Worker-nek.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */
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
        return Stayed;
    }

    /**
     * A Worker-t mozgatja.
     * @param f: erre a mezore mozog a Worker.
     * @return Igaz, ha sikeres volt a mozgas, s hamis, ha nem.
     */
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

    /**
     * A patameteret a jatekostol kapja. Ez altal iranyitott a Worker.
     * @param d: ebbe az iranyba szeretne mozgatni a Worker-t a jatekos.
     */
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

    /**
     * Kenoanyagot helyez el egy mezon a Worker.
     * @param d: ebbe az iranyba helyez el kenoanyagot.
     * @param f: ilyen tipusu kenoanyagot helyez el.
     */
    public void placeObject(Direction d, Field.FieldState f) {
        field.GetNeighbor(d).setSplich(f);
    }

    public Movement IsThereMovement(Field currentField) {
        //Avoid checking a field multiple times
        if (currentField.getChecked())
            return Stayed;
        currentField.setChecked(true);

        //This will be returned, it is Stayed if all of the recursive functions returned Stayed, else Moved
        Movement retMov = Stayed;

        for (Direction dir : Direction.values()) {
            Field nextField = currentField.GetNeighbor(dir);

            if (nextField != null && nextField.PseudoAccept() && !nextField.getChecked()) {

                Movable m = nextField.GetMovable();
                if (m != null) {
                    return m.PseudoCollideWorker(dir, strength);
                } else {
                    if (IsThereMovement(nextField) == Moved) {
                        return Moved;
                    }
                }
            }
        }
        return Stayed;
    }

    /**
     * @return igaz, ha meg el a Worker, s hamis, ha nem.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @return az adott Worker pontjai
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return az adott Worker ereje
     */
    public int getStrength() {
        return strength;
    }

    @Override
    public String Draw() {
        return String.valueOf(identifier);
    }
}
