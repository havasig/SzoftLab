package Main;

public class Worker extends Movable {
    private int points;

    public void IncrementPoints(){

    }

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
}
