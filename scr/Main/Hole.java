package Main;

/**
 * Ha egy doboz rálép, amik a lyuk nyitva van, akkor az leesik, tehát megszünteti az
 * objektumot. Ha játékos lép rá nyitott állapotban akkor a játékos meghal, a játéknak vége.
 * Ha csukva van akkor rendes Field objektumként viselkedik.
 */
public class Hole extends Field {
    private HoleState state;

    /**
     * A Hole osztály konstruktora
     *
     * @param label A logger osztály segéd stringje
     */
    public Hole(String label) {
        super(label);
        state = HoleState.Open;
    }

    /**
     * ​ Ha nyitott állapotban van akkor meghívja az objektum Fall()
     * függvényét. Ha csukott állapotban akkor sima Field mezőkként
     * viselkedik.
     *
     * @param w A mezőre lépő munkás referenciája
     * @return A lépés sikeressége szerinti érték
     */
    @Override
    public boolean AcceptWorker(Worker w) {
        Logger.funcStart("AcceptWorker", label, w.label);
        if (state == HoleState.Open) {
            w.Fall();
            Logger.funcEnd("AcceptWorker", label, "True");
            return true;
        } else {
            boolean ret = super.AcceptWorker(w);
            Logger.funcEnd("AcceptWorker", label, Boolean.toString(ret));
            return ret;
        }
    }

    /**
     * ​ Ha nyitott állapotban van akkor meghívja az objektum
     * Fall() függvényét. Ha csukott állapotban akkor sima Field
     * mezőkként viselkedik.
     *
     * @param b A mezőre érkető doboz referenciája
     * @return A lépés sikeressége szerinti érték
     */
    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        if (state == HoleState.Open) {
            b.Fall();
            Logger.funcEnd("AcceptBox", label, "True");
            return true;
        } else {
            boolean ret = super.AcceptBox(b);
            Logger.funcEnd("AcceptBox", label, Boolean.toString(ret));
            return ret;
        }
    }

    /**
     * ​ Beállítja a lyuk állapotát nyitottra
     */
    public void SetOpen() {
        Logger.funcStart("SetOpen", label, "");
        state = HoleState.Open;
        Logger.funcEnd("SetOpen", label, "");
    }

    /**
     * ​ Beállítja a lyuk állapotát Csukottra
     */
    public void SetClosed() {
        Logger.funcStart("SetClosed", label, "");
        state = HoleState.Closed;
        Logger.funcEnd("SetClosed", label, "");
    }

    /**
     * A lyuk állaptait tartalmazó enumeráció
     */
    public enum HoleState {
        Open,
        Closed
    }
}
