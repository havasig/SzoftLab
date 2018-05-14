package Main;

import java.awt.*;
import java.util.HashMap;

/**
 * Egyszeru mezo. Lehet rajta Movable, valamint kenoanyag.
 */
public class Field {
    /**
     * Rajta levo Movable.
     */
    Movable movable;

    private Factory factory;
    /**
     * A mezo szomszedsagaban levo mezok
     */
    private HashMap<Direction, Field> neighbors;
    /**
     * A mezon levo kenoanyag
     */
    private FieldState splich;
    /**
     * Tarolja, hogy volt-e mar ellenorizve a mezo.
     */
    private Boolean checked;

    /**
     * A Field kontruktora.
     */
    public Field(Factory _factory) {
        this.factory = _factory;
        neighbors = new HashMap<>();
        splich = FieldState.None;
        checked = false;
    }

    /**
     * @return igaz, ha volt ellenorizve a mezo, s hamis, ha nem.
     */
    Boolean getChecked() {
        return checked;
    }

    /**
     * Beallitja a checked tagvaltozo erteket.
     *
     * @param checked: erre allitja be a tagvaltozot.
     */
    void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * Visszaadja a d iranyban levo szomszedjat a mezonek.
     *
     * @param d: egy irany
     * @return a d iranyban levo mezo
     */
    Field GetNeighbor(Direction d) {
        return neighbors.get(d);
    }

    /**
     * Beallitja a d iranyban levo szomszedjat a mezonek.
     *
     * @param d: egy irany
     */
    void SetNeighbor(Direction d, Field f) {
        neighbors.put(d, f);
    }

    /**
     * @return a mezon levo Movable
     */
    Movable GetMovable() {
        return movable;
    }

    /**
     * Beallitja, hogy van rajta egy Worker.
     *
     * @param w: a Worker, ami rakerult.
     * @return igaz, ha rakerult a Worker, hamis, ha nem.
     */
    boolean AcceptWorker(Worker w) {
        movable = w;
        return true;
    }

    /**
     * Beallitja, hogy van rajta egy Box.
     *
     * @param b: a Box, ami rakerult.
     * @return igaz, ha rakerult a Box, hamis, ha nem.
     */
    boolean AcceptBox(Box b) {
        movable = b;
        return true;
    }

    /**
     * Eltavolitja magarol a Worker-t
     *
     * @param w: a Worker, ami lekerult rola.
     */
    void RemoveWorker(Worker w) {
        if (movable == w) {
            movable = null;
        }
    }

    /**
     * Eltavolitja magarol a Box-ot
     *
     * @param b: a Box, ami lekerult rola.
     */
    void RemoveBox(Box b) {
        movable = null;
    }

    /**
     * Visszaadja a rajta levo kenoanyagot
     *
     * @return a rajta levo kenoanyag
     */
    public FieldState getSplich() {
        return splich;
    }

    /**
     * Beallitja, hogy van rajta kenoanyag
     *
     * @param splich: a kenoaynag, ami rakerult.
     */
    void setSplich(FieldState splich) {
        this.splich = splich;
    }

    boolean PseudoAccept() {
        return true;
    }

    public Point getPos() {
        return factory.getPos(this);
    }

    /**
     * A mezon lehetseges kenoanyagok tipusai
     */
    public enum FieldState {
        /**
         * Honey: 1-gyel noveli a surlodast.
         * Oil: 1-gyel csokkenti a surlodast.
         * None: nincs semmi a mezon. A surlodas nem valtozik.
         */
        Honey(1),
        Oil(-1),
        None(0);

        private int value;

        FieldState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


}
