package Main;

public class Destination extends Field {
    public Destination() {
        super();
    }

    @Override
    public boolean AcceptBox(Box b) {
        super.AcceptBox(b);
        b.Lock();
        Game.getInstance().SetPoint();
        return true;
    }
}
