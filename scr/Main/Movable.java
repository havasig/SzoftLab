package Main;

public abstract class Movable {
    protected Field field;

    public abstract void Die();

    public abstract Movement CollideWorker(Direction d);

    public abstract Movement CollideBox(Direction d);

    public abstract boolean Move(Field f);

    public void Fall() {
        Die();
    }

}
