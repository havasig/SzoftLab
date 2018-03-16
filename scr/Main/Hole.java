package Main;

public class Hole extends Field {
    public enum HoleState{
        Open,
        Closed
    }

    private HoleState state;

    @Override
    public boolean AcceptWorker(Worker w) {
        return super.AcceptWorker(w);
    }

    @Override
    public boolean AcceptBox(Box b) {
        return super.AcceptBox(b);
    }

    public void SetOpen(){

    }

    public void SetClosed(){

    }
}
