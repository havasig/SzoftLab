package Main;

public class Switch extends Field {
    private Hole hole;

    public Switch(String label) {
        super(label);
    }

    @Override
    public boolean AcceptBox(Box b) {
        super.AcceptBox(b);
        hole.SetOpen();
        return true;
    }

    @Override
    public void RemoveBox(Box b) {
        super.RemoveBox(b);
        hole.SetClosed();
    }

    public void SetHole(Hole h) {
        hole = h;
    }
}
