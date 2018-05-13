package Main;

/**
 * Egyfajta mezo. Mivel oszlop, igy nem kerulhet ra semmi.
 * Field → Column
 */
public class Column extends Field {
    /**
     * A Column konstruktora.
     */
    Column(Factory _factory) {
        super(_factory);
    }

    /**
     * @param w: a Worker, ami szeretne rakerulni.
     * @return hamis, hiszen nem kerult ra.
     */
    @Override
    public boolean AcceptWorker(Worker w) {
        return false;
    }

    /**
     * @param b: a Box, ami szeretne rakerulni.
     * @return hamis, hiszen nem kerult ra.
     */
    @Override
    public boolean AcceptBox(Box b) {
        return false;
    }

    @Override
    public boolean PseudoAccept() {
        return false;
    }

}
