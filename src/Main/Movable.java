package Main;

public abstract class Movable implements Drawable {
    protected Field field;

    /**
     * Meghal a Movable. Absztrakt fuggveny, a leszarmazottak felulirjak.
     */
    public abstract void Die();

    /**
     * A Movable egy Worker-rel valo utkozeset kezeli.
     * Absztrakt fuggveny, a leszarmazottak felulirjak.
     * @param d: az irany, amelyre mozog a Movable.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Movable-nek.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */

    public abstract Movement CollideWorker(Direction d, int sumFriction);
    /**
     * A Movable egy Box-szal valo utkozeset kezeli.
     * Absztrakt fuggveny, a leszarmazottak felulirjak.
     * @param d: az irany, amelyre mozog a Movable.
     * @param sumFriction: az osszsurlodas, amit el kell tolnia a Movable-nek.
     * @return visszaadja, hogy sikeres volt-e a tolas az adott iranyba.
     */

    public abstract Movement CollideBox(Direction d, int sumFriction);

    public abstract Movement PseudoCollideWorker(Direction d, int sumFriction);

    public abstract Movement PseudoCollideBox(Direction d, int sumFriction);

    /**
     * A Movable-t mozgatja.
     * Absztrakt fuggveny, a leszarmazottak felulirjak.
     * @param f: erre a mezore mozog a Movable.
     * @return Igaz, ha sikeres volt a mozgas, s hamis, ha nem.
     */
    public abstract boolean Move(Field f);

    /**
     * Leesik a Movable egy lyukba. Megoli a Movable-t.
     */
    void Fall() {
        Die();
    }


}
