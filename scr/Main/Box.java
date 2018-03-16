package Main;

public class Box extends Movable {
    private boolean locked;

    @Override
    public void Die() {

    }

    @Override
    public Movement CollideWorker(Direction d) {
        return null;
    }

    @Override
    public Movement CollideBox(Direction d) {
        return null;
    }

    @Override
    public boolean Move(Field f) {
        return false;
    }

    @Override
    public void Fall() {

    }

    private void Lock(){

    }

}
