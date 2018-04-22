package Main;

import java.util.ArrayList;
import java.util.HashMap;

import static Main.Movement.Moved;

public class Factory implements Drawable {
    private ArrayList<Field> fields;
    private HashMap<Integer, Hole> holes;
    private int width, height;

    private Field getField(int x, int y) {
        return fields.get((y * width) + x);
    }

    void Load(String name) {
        fields = new ArrayList<>();
        //TODO
    }

    public boolean ThisIsTheEnd() {
        //set all field to unchecked
        for (Field field : fields)
            field.setChecked(false);

        //Iterate through workers, if any of them can move something, then it is not over
        HashMap<Integer, Worker> workers = Game.getInstance().getWorkers();
        for (Worker worker : workers.values()) {
            if (worker.IsThereMovement(worker.field) == Moved)
                return false;
        }
        return true;
    }

    public void GenerateMap(int width, int height) {
        fields = new ArrayList<>();
        holes = new HashMap<>();
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || (i + 1) == height)
                    fields.add(new Column());
                else if (j == 0 || (j + 1) == width)
                    fields.add(new Column());
                else
                    fields.add(new Field());
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y - 1 >= 0)
                    getField(x, y).SetNeighbor(Direction.Up, getField(x, y - 1));
                else
                    getField(x, y).SetNeighbor(Direction.Up, null);

                if (y + 1 <= height - 1)
                    getField(x, y).SetNeighbor(Direction.Down, getField(x, y + 1));
                else
                    getField(x, y).SetNeighbor(Direction.Down, null);

                if (x - 1 >= 0)
                    getField(x, y).SetNeighbor(Direction.Left, getField(x - 1, y));
                else
                    getField(x, y).SetNeighbor(Direction.Left, null);

                if (x + 1 <= width - 1)
                    getField(x, y).SetNeighbor(Direction.Right, getField(x + 1, y));
                else
                    getField(x, y).SetNeighbor(Direction.Right, null);
            }
        }
    }

    public String Draw() {
        StringBuilder map = new StringBuilder();
        int width = 0;
        for (Field field : fields) {
            map.append(field.Draw());
            map.append(" ");
            width++;
            if (width == this.width) {
                map.append("\n");
                width = 0;
            }
        }
        //TODO Switch hole kapcsolat
        return map.toString();
    }

    public void createColumn(int x, int y) {
        Column column = new Column();
        replaceField(x, y, column);
    }

    public void createDestination(int x, int y) {
        Destination destination = new Destination();
        replaceField(x, y, destination);
    }

    public void createHole(int x, int y, HoleState state) {
        Hole hole = new Hole();
        replaceField(x, y, hole);
        if (state == HoleState.Open)
            hole.SetOpen();
        else
            hole.SetClosed();
        holes.put((y * width) + x, hole);
    }

    public void createSwitch(int x, int y, int hX, int hY) {
        Switch switcher = new Switch();
        replaceField(x, y, switcher);
        switcher.SetHole(holes.get((hY * width) + hX));
    }


    public void replaceField(int x, int y, Field field) {
        field.SetNeighbor(Direction.Up, getField(x, y).GetNeighbor(Direction.Up));
        field.SetNeighbor(Direction.Down, getField(x, y).GetNeighbor(Direction.Down));
        field.SetNeighbor(Direction.Left, getField(x, y).GetNeighbor(Direction.Left));
        field.SetNeighbor(Direction.Right, getField(x, y).GetNeighbor(Direction.Right));

        field.GetNeighbor(Direction.Up).SetNeighbor(Direction.Down, field);
        field.GetNeighbor(Direction.Down).SetNeighbor(Direction.Up, field);
        field.GetNeighbor(Direction.Left).SetNeighbor(Direction.Right, field);
        field.GetNeighbor(Direction.Right).SetNeighbor(Direction.Left, field);
        fields.set(x + y * width, field);
    }

    public void addWorker(int x, int y, int id) {
        Game.getInstance().addWorker(new Worker(getField(x, y), id), id);
    }

    public void addBox(int x, int y) {
        new Box(getField(x, y), 1);
    }

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
}
