package Main;

public class Switch extends Field {
    private Hole hole;

    public Switch(String label) {
        super(label);
    }

    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        super.AcceptBox(b);
        hole.SetOpen();
        Logger.funcEnd("AcceptBox", label, "True");
        return true;
    }

    @Override
    public void RemoveBox(Box b) {
        Logger.funcStart("RemoveBox", label, b.label);
        super.RemoveBox(b);
        hole.SetClosed();
        Logger.funcEnd("RemoveBox", label, "");
    }

    public void SetHole(Hole h) {
        Logger.funcStart("SetHole", label, h.label);
        hole = h;
        Logger.funcEnd("SetHole", label, "");
    }
}
