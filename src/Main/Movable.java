package Main;

public abstract class Movable {
    protected Field field;

    public abstract void Die();

    public abstract Movement CollideWorker(Direction d, int sumFriction);

    public abstract Movement CollideBox(Direction d, int sumFriction);

    public abstract boolean Move(Field f);

    void Fall() {
        Die();
    }

}
