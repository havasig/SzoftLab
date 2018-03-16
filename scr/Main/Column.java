package Main;

public class Column extends Field {
    @Override
    public boolean AcceptWorker(Worker w) {
        return super.AcceptWorker(w);
    }

    @Override
    public boolean AcceptBox(Box b) {
        return super.AcceptBox(b);
    }
}
