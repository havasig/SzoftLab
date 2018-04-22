package Main;

public class Column extends Field {
    public Column() {
        super();
    }

    @Override
    public boolean AcceptWorker(Worker w) {
        return false;
    }

    @Override
    public boolean AcceptBox(Box b) {
        return false;
    }

    @Override
    public boolean PseudoAccept() {
        return false;
    }

    @Override
    public String Draw() {
        return "X__";
    }
}
