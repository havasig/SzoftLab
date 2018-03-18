package Main;

public abstract class Movable {
    protected Field field;
    protected String label;

    public Movable(String label) {
        this.label = label;
    }

    public abstract void Die();

    public abstract Movement CollideWorker(Direction d);

    public abstract Movement CollideBox(Direction d);

    public abstract boolean Move(Field f);

    public void Fall() {
        Die();
    }

    public String getLabel() {
        return label;
    }
}
