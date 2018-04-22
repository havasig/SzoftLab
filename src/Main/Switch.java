package Main;

public class Switch extends Field {
    private Hole hole;
    private boolean box;

    public Switch() {
        super();
        box = false;
    }

    @Override
    public boolean AcceptBox(Box b) {
        super.AcceptBox(b);
        hole.SetOpen();
        box = true;
        return true;
    }

    @Override
    public void RemoveBox(Box b) {
        super.RemoveBox(b);
        box = false;
        hole.SetClosed();
    }

    public void SetHole(Hole h) {
        hole = h;
    }

    @Override
    public String Draw() {
        StringBuilder field = new StringBuilder();
        if (box) {
            field.append("S");
        } else
            field.append("s");
        if (movable == null)
            field.append("_");
        else
            field.append(movable.Draw());
        DrawSplich(field);
        Factory.setSwitchHole(Game.getInstance().getMap().getPos(this)
                + ";"
                + Game.getInstance().getMap().getPos(hole)
                + "\n");
        return field.toString();
    }
}
