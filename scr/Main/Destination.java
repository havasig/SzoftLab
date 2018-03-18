package Main;

/**
 * A pálya padlózatát képző objektum egyfajta típusa. A játék során a léptethető elemek
 * ráléphetnek. Ha egy láda kerül rá, akkor a láda lock-olódik, azaz többé nem lesz mozdítható,
 * s az a játékos, aki mozgása által a Destination objektumra került a láda, az kap egy pontot.
 */
public class Destination extends Field {
    public Destination(String label) {
        super(label);
    }

    /**
     * ​ Meghívja a Box Lock() függvényét és a Game
     * SetPoints() függvényét is.
     *
     * @param b A mezőre érkető doboz referenciája
     * @return A lépés sikeressége szerinti érték
     */
    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        super.AcceptBox(b);
        b.Lock();
        Game.getInstance().SetPoint();
        Logger.funcEnd("CollideMovable", label, "True");
        return true;
    }
}
