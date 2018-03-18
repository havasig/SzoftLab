package Main;

public class Destination extends Field {
    public Destination(String label) {
        super(label);
    }

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
