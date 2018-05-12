package Main;

/**
 * Egyfajta mezo. Ha dobozt tolnak ra, Lock-olja azt.
 * Field → Destination
 */
public class Destination extends Field {
    /**
     * A Destination konstruktora.
     */
    Destination() {
        super();
    }

    /**
     * Beallitja, hogy van rajta egy Box. Lock-kolja a dobozt.
     *
     * @param b: a Box, ami rakerult.
     * @return igaz, hiszen rakerult a Box.
     */
    @Override
    public boolean AcceptBox(Box b) {
        super.AcceptBox(b);
        b.Lock();
        return true;
    }

}
