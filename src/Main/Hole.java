package Main;

/**
 * Egyfajta mezo. Ha nyitva van, es rakerul egy Movable, akkor az leesik. Ha zarva van, akkor Field-kent viselkedik.
 * Ha tartozik hozza Switch, akkor az kinyitni es bezarni kepes.
 * Field → Hole
 */
public class Hole extends Field {
    /**
     * A Hole allapotat tarolja.
     */
    private HoleState state;

    /**
     * A Hole konstruktora.
     */
    Hole(Factory _factory) {
        super(_factory);
        state = HoleState.Open;
    }

    /**
     * Beallitja, hogy van rajta egy Worker. Ha nyitva van, akkor a Worker leesik.
     *
     * @param w: a Worker, ami rakerult.
     * @return igaz, ha rakerult a Worker, hamis, ha nem.
     */
    @Override
    public boolean AcceptWorker(Worker w) {
        if (state == HoleState.Open) {
            w.Fall();
            return true;
        } else {
            return super.AcceptWorker(w);
        }
    }

    /**
     * Beallitja, hogy van rajta egy Box. Ha nyitva van, akkor a Box leesik.
     *
     * @param b: a Box, ami rakerult.
     * @return igaz, ha rakerult a Box, hamis, ha nem.
     */
    @Override
    public boolean AcceptBox(Box b) {
        if (state == HoleState.Open) {
            b.Fall();
            return true;
        } else {
            return super.AcceptBox(b);
        }
    }

    /**
     * Kinyitja a lyukat.
     */
    void SetOpen() {
        state = HoleState.Open;
        if (movable != null) {
            movable.Fall();
        }
    }

    /**
     * Bezarja a lyukat.
     */
    void SetClosed() {
        state = HoleState.Closed;
    }

    public HoleState getState() {
        return state;
    }
}
