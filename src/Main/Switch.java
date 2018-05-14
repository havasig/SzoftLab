package Main;

/**
 * Egyfajta mezo. Ha dobozt tolnak ra, a hozza tartozo lyuk kinyilik, ha letolnak rola egy dobozt,
 * a hozza tartozo lyuk bezarodik.
 * Field → Switch
 */
public class Switch extends Field {
    /**
     * A Switch-hez tartozo Hole.
     */
    private Hole hole;
    /**
     * Tarolja, hogy a Switch-en van-e doboz.
     */
    private boolean box;

    /**
     * A Switch konstruktora
     */
    Switch(Factory _factory) {
        super(_factory);
        box = false;
    }

    /**
     * Beallitja, hogy van rajta egy Box. Kinyitja a hozza tartozo lyukat.
     *
     * @param b: a Box, ami rakerult.
     * @return igaz, hiszen rakerult a Box.
     */
    @Override
    public boolean AcceptBox(Box b) {
        super.AcceptBox(b);
        hole.SetOpen();
        box = true;
        return true;
    }

    /**
     * Eltavolitja magarol a Box-ot
     *
     * @param b: a Box, ami lekerult rola.
     */
    @Override
    public void RemoveBox(Box b) {
        super.RemoveBox(b);
        box = false;
        hole.SetClosed();
    }

    /**
     * Beallitja a hozza tartozo lyukat.
     *
     * @param h: a hozza tartozo lyuk.
     */
    void SetHole(Hole h) {
        hole = h;
    }

    public boolean getBox() {
        return box;
    }
}
