package Main;

public class Worker extends Movable {
    private int points;

    public void IncrementPoints(){

    }

    @Override
    public void Die() {

    }

    @Override
    public Movement CollideWorker(Field.Direction d) {
        return null;
    }

    @Override
    public Movement CollideBox(Field.Direction d) {
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
