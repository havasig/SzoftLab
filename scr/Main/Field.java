package Main;

import java.util.HashMap;

/**
 * Üres mező, aminek nincs extra tulajdonsága. Lehet rajta Movable objektum. Ebből
 * származnak le a speciális mezőtípusok.
 */
public class Field {
    protected Movable movable;
    protected String label;
    private HashMap<Direction, Field> neighbors;

    /**
     * A mező osztály konstruktora
     *
     * @param label A logger osztály segéd stringje
     */
    public Field(String label) {
        this.label = label;
        neighbors = new HashMap<>();
    }

    /**
     * Visszatér azzal a mezővel, ami a paraméterként
     * kapott irányban található
     *
     * @param d A lekérdezett szomszédos mező iránya
     * @return A szomszádos mező elem
     */
    public Field GetNeighbor(Direction d) {
        Logger.funcStart("GetNeighbor", label, d.name());
        Field ret = neighbors.get(d);
        if (ret != null)
            Logger.funcEnd("GetNeighbor", label, ret.label);
        else
            Logger.funcEnd("GetMovable", label, "null");
        return ret;
    }

    /**
     * ​ Beállítja a paraméterként kapott irányba a
     * paraméterként kapott mezőt
     *
     * @param d A beállítandó irány
     * @param f A beűllítandó mező
     */
    public void SetNeighbor(Direction d, Field f) {
        neighbors.put(d, f);
    }

    /**
     * ​Visszaadja a mezőn tartózkodó Movable objektumot
     *
     * @return A mezőn lévő léptethető objektum
     */
    public Movable GetMovable() {
        Logger.funcStart("GetMovable", label, "");
        Movable ret = movable;
        if (ret != null)
            Logger.funcEnd("GetMovable", label, ret.label);
        else
            Logger.funcEnd("GetMovable", label, "null");
        return ret;
    }

    /**
     * A mezőn kezdetben álló léptethető objektum beállítására szolgál
     *
     * @param movable A mezőn lévő léptethető objektum
     */
    public void SetMovable(Movable movable) {
        this.movable = movable;
    }

    /**
     * ​Elfogadja a mezőre érkező Worker-t
     *
     * @param w A mezőre lépő munkás referenciája
     * @return A lépés sikeressége szerinti érték
     */
    public boolean AcceptWorker(Worker w) {
        Logger.funcStart("AcceptWorker", label, w.label);
        movable = w;
        Logger.funcEnd("AcceptWorker", label, "True");
        return true;
    }

    /**
     * :Elfogadja a mezőre érkező Box-t
     *
     * @param b A mezőre érkető doboz referenciája
     * @return A mozgatás sikeressége szerinti érték
     */
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        movable = b;
        Logger.funcEnd("AcceptBox", label, "True");
        return true;
    }

    /**
     * ​ Eltávolítja a mezőről a Worker objektumot.
     *
     * @param w ​ Az eltávolíttandó munkás referenciája
     */
    public void RemoveWorker(Worker w) {
        Logger.funcStart("RemoveWorker", label, w.label);
        movable = null;
        Logger.funcEnd("RemoveWorker", label, "");
    }

    /**
     * ​ Eltávolítja a mezőről a Box objektumot
     *
     * @param b Az eltávolíttandó doboz referenciája
     */
    public void RemoveBox(Box b) {
        Logger.funcStart("RemoveBox", label, b.label);
        movable = null;
        Logger.funcEnd("RemoveBox", label, "");
    }

    /**
     * A logger osztály segéd változójának getter függvénye
     *
     * @return Az objektum nevét adja vissza
     */
    public String getLabel() {
        return label;
    }
}
