package Main;

public class Column extends Field {
    public Column(String label) {
        super(label);
    }

    @Override
    public boolean AcceptWorker(Worker w) {
        Logger.funcStart("AcceptWorker", label, w.label);
        Logger.funcEnd("AcceptWorker", label, "False");
        return false;
    }

    @Override
    public boolean AcceptBox(Box b) {
        Logger.funcStart("AcceptBox", label, b.label);
        Logger.funcEnd("AcceptBox", label, "False");
        return false;
    }
}
