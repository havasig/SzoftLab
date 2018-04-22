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

    @Override
    public String Draw() {
        StringBuilder field = new StringBuilder();
        switch (state){
            case Open:
                field.append("H");
                break;
            case Closed:
                field.append("h");
                break;
        }
        if (movable == null)
            field.append("_");
        else
            field.append(movable.Draw());
        DrawSplich(field);
        return field.toString();
    }
}
