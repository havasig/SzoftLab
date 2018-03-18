package Main;

public class Column extends Field {
    public Column(String label) {
        super(label);
    }

    @Override
    public boolean AcceptWorker(Worker w) {
        return false;
    }

    @Override
    public boolean AcceptBox(Box b) {
        return false;
    }
}
