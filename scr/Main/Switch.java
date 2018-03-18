package Main;

/**
 * Kapcsoló, amihez tartozik egy Hole. Ha áll rajta doboz, akkor a Hole nyitva van, ha nincs a
 * mezőn doboz akkor csukva
 */
public class Switch extends Field {
    private Hole hole;

    /**
     * A Switch konsturktora
     * @param label A logger osztály segéd stringje
     */
    public Switch(String label) {
        super(label);
    }

    /**
     * ​ Meghívja a kapcsolóhoz tartozó lyuk SetOpen()
     * függvényét és befogadja a Box-t
     * @param b A mezőre érkető doboz referenciája
     * @return A lépés sikeressége szerinti érték
     */
    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        super.AcceptBox(b);
        hole.SetOpen();
        Logger.funcEnd("AcceptBox", label, "True");
        return true;
    }

    /**
     * Meghívja a Hole SetClosed() függvényét. Bezárja az adott Switch
     objektumhoz tartozó lyukat és eltávolítja a Box-t a mezőről.
     * @param b Az eltávolíttandó doboz referenciája
     */
    @Override
    public void RemoveBox(Box b) {
        Logger.funcStart("RemoveBox", label, b.label);
        super.RemoveBox(b);
        hole.SetClosed();
        Logger.funcEnd("RemoveBox", label, "");
    }

    /**
     * Beállítja, hogy melyik lyuk tartozik az adott kapcsolóhoz
     * @param h ​A kapcsolóhoz tartozó lyuk referenciája
     */
    public void SetHole(Hole h) {
        Logger.funcStart("SetHole", label, h.label);
        hole = h;
        Logger.funcEnd("SetHole", label, "");
    }
}
