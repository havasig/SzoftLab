package Main;

/**
 * Absztrakt osztály, amiből leszármaznak a mozgatható objektumok. Eltárolja, hogy melyik
 * mezőn tartózkodik az objektum.
 */
public abstract class Movable {
    /**
     * ​ A mező, amin az objektum áll
     */
    protected Field field;
    /**
     * ​ A logger osztály segéd stringje, a kiíráshoz tárolja az objektum nevét
     */
    protected String label;

    /**
     * A Movable osztály konsturktora
     *
     * @param label A logger osztály segéd stringje
     */
    public Movable(String label) {
        this.label = label;
    }

    /**
     * Absztakt metódus, a leszármazott osztályok definiálják
     */
    public abstract void Die();

    /**
     * ​Ez a függvény kezeli a Worker objektum által
     * indított ütközést az objektummal.
     *
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
    public abstract Movement CollideWorker(Direction d);

    /**
     * ​Ez a függvény kezeli a Box objektum által indított
     * ütközést az objektummal
     *
     * @param d Az ütközés iránya
     * @return Sikerült-e az ütközés során megtolni
     */
    public abstract Movement CollideBox(Direction d);

    /**
     * ​ Átmozgatja az egyik mezőről a másikra az objektumot
     *
     * @param f A következő mező, amire lépni fog
     * @return Sikerült-e a lépés, annak megfelelő a visszatérési érték
     */
    public abstract boolean Move(Field f);

    /**
     * Meghívja az objektum Die() függvényét.
     */
    public void Fall() {
        Logger.funcStart("Fall", label, "");
        Die();
        Logger.funcEnd("Fall", label, "");
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
