package Main;

/**
 * A pálya padlózatát képző objektum egyfajta típusa. A játék során a léptethető elemek nem
 * léphetnek rá.
 */
public class Column extends Field {
    /**
     * A Column osztály konstruktora
     * @param label A logger osztály segéd stringje
     */
    public Column(String label) {
        super(label);
    }

    /**
     * ​ False-al tér vissza, mivel erre a mezőre nem
     * lehet lépni
     * @param w A mezőre lépő munkás referenciája
     * @return Konstans false
     */
    @Override
    public boolean AcceptWorker(Worker w) {
        Logger.funcStart("AcceptWorker", label, w.label);
        Logger.funcEnd("AcceptWorker", label, "False");
        return false;
    }

    /**
     * ​False-al tér vissza, mivel erre a mezőre nem lehet lépni.
     * @param b A mezőre érkető doboz referenciája
     * @return Konstans false
     */
    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        Logger.funcEnd("AcceptBox", label, "False");
        return false;
    }
}
