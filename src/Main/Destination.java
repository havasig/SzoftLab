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

    @Override
    public String Draw() {
        StringBuilder field = new StringBuilder();
        field.append("D");
        if (movable == null)
            field.append("_");
        else
            field.append(movable.Draw());
        DrawSplich(field);
        return field.toString();
    }
}
