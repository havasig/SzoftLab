package Main;

import java.awt.*;

import static Main.Movement.Moved;
import static Main.Movement.Stayed;

/**
 * Egy doboz. Tolhatoak a jatekban. Ha Switch-re kerulnek, aktivaljak azt, ha Destination-ra, lock-olodnak
 * ha nyitott lyukra, akkor leesnek.
 */
public class Box extends Movable {
    /**
     * Tarolja, hogy lock-olt-e a doboz
     */
    private boolean locked;
    /**
     * Tarolja, mennyi a doboz sulya.
     */
    private int defFriction;

    /**
     * Ez a Box konstruktora.
     *
     * @param startField: a Worker kezo mezeje
     * @param friction:   a doboz sulya
     */
    Box(Field startField, int friction) {
        field = startField;
        startField.AcceptBox(this);
        locked = false;
        this.defFriction = friction;
    }

    /**
     * A Box meghal. Eltavolitja a mezorol a Box-ot.
     */
    @Override
    public void Die() {
        field.RemoveBox(this);
    }

    /**
     * A Box egy Worker-rel valo utkozeset kezeli.
     *
     * @param d:           az irany, amelyre mozog a Box.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Box-nak.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */
    @Override
    public Movement CollideWorker(Direction d, int sumFriction) {
        return CollideMovable(d, sumFriction);
    }

    /**
     * A Box egy Box-szal valo utkozeset kezeli.
     *
     * @param d:           az irany, amelyre mozog a Box.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Box-nak.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */
    @Override
    public Movement CollideBox(Direction d, int sumFriction) {
        return CollideMovable(d, sumFriction);
    }

    /**
     * A Box egy Movable-lel valo utkozeset kezeli.
     *
     * @param d:           az irany, amelyre mozog a Box.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Box-nak.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */
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
    public Movement PseudoCollideWorker(Direction d, int sumFriction) {
        return PseudoCollideMovable(d, sumFriction);
    }

    @Override
    public Movement PseudoCollideBox(Direction d, int sumFriction) {
        return PseudoCollideMovable(d, sumFriction);
    }

    private Movement PseudoCollideMovable(Direction d, int sumFriction) {
        int friction = defFriction + field.getSplich().getValue();
        if (friction <= sumFriction) {
            if (!locked) {
                Field nextField = field.GetNeighbor(d);
                Movable m = nextField.GetMovable();
                if (m != null) {
                    Movement state = m.PseudoCollideBox(d, (sumFriction - friction));
                    if (state == Moved) {
                        return Moved;
                    }
                } else {
                    if (nextField.PseudoAccept())
                        return Moved;
                }
            }
        }
        return Stayed;
    }

    /**
     * A Box-ot mozgatja.
     *
     * @param f: erre a mezore mozog a Box.
     * @return Igaz, ha sikeres volt a mozgas, s hamis, ha nem.
     */
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

    /**
     * Lock-olja a Box-ot.
     */
    void Lock() {
        Game.getInstance().SetPoint();
        locked = true;
    }

    public boolean getLocked()
    {
        return locked;
    }

    public Point getPos(){
        return field.getPos();
    }
}
