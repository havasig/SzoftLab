package Main;

public class Hole extends Field {
    private HoleState state;

    public Hole() {
        super();
        state = HoleState.Open;
    }

    @Override
    public boolean AcceptWorker(Worker w) {
        if (state == HoleState.Open) {
            w.Fall();
            return true;
        } else {
            return super.AcceptWorker(w);
        }
    }

    @Override
    public boolean AcceptBox(Box b) {
        if (state == HoleState.Open) {
            b.Fall();
            return true;
        } else {
            return super.AcceptBox(b);
        }
    }

    public void SetOpen() {
        state = HoleState.Open;
    }

    public void SetClosed() {
        state = HoleState.Closed;
    }

    public enum HoleState {
        Open,
        Closed
    }
}
