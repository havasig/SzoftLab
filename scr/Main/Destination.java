package Main;

public class Destination extends Field {
    public Destination(String label) {
        super(label);
    }

    @Override
    public boolean AcceptBox(Box b) {
        super.AcceptBox(b);
        b.Lock();
        Game.getInstance().SetPoint();
        return true;
    }
}
