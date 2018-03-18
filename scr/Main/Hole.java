package Main;

public class Hole extends Field {
    private HoleState state;

    public Hole(String label) {
        super(label);
        state = HoleState.Open;
    }

    @Override
    public boolean AcceptWorker(Worker w) {
        Logger.funcStart("AcceptWorker", label, w.label);
        if (state == HoleState.Open) {
            w.Fall();
            Logger.funcEnd("AcceptWorker", label, "True");
            return true;
        } else {
            boolean ret = super.AcceptWorker(w);
            Logger.funcEnd("AcceptWorker", label, Boolean.toString(ret));
            return ret;
        }
    }

    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        if (state == HoleState.Open) {
            b.Fall();
            Logger.funcEnd("AcceptBox", label, "True");
            return true;
        } else {
            boolean ret = super.AcceptBox(b);
            Logger.funcEnd("AcceptBox", label, Boolean.toString(ret));
            return ret;
        }
    }

    public void SetOpen() {
        Logger.funcStart("SetOpen", label, "");
        state = HoleState.Open;
        Logger.funcEnd("SetOpen", label, "");
    }

    public void SetClosed() {
        Logger.funcStart("SetClosed", label, "");
        state = HoleState.Closed;
        Logger.funcEnd("SetClosed", label, "");
    }

    public enum HoleState {
        Open,
        Closed
    }
}
