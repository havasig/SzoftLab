package Main;

import java.util.ArrayList;
import java.util.HashMap;

import static Main.Movement.Moved;

public class Factory implements Drawable {
    private ArrayList<Field> fields;
    private int width, height;


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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i - 1 > 0)
                    fields.get(i * width + j).SetNeighbor(Direction.Up, fields.get((i - 1) * width + j));
                else
                    fields.get(i * width + j).SetNeighbor(Direction.Up, null);

                if (i + 1 < height)
                    fields.get(i * width + j).SetNeighbor(Direction.Down, fields.get((i + 1) * width + j));
                else
                    fields.get(i * width + j).SetNeighbor(Direction.Down, null);

                if (j - 1 > 0)
                    fields.get(i * width + j).SetNeighbor(Direction.Left, fields.get(i * width + j - 1));
                else
                    fields.get(i * width + j).SetNeighbor(Direction.Left, null);

                if (j + 1 < width)
                    fields.get(i * width + j).SetNeighbor(Direction.Right, fields.get(i * width + j + 1));
                else
                    fields.get(i * width + j).SetNeighbor(Direction.Right, null);
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

    public void createHole(int x, int y, String state) {
        Hole hole = new Hole();
        replaceField(x, y, hole);
        switch (state){
            case "Open":
                hole.SetOpen();
                break;
            case "Closed":
                hole.SetClosed();
                break;
        }
    }

    public void createSwitch(int x, int y, int hX, int hY) {
        Switch switcher = new Switch();
        replaceField(x, y, switcher);
        switcher.SetHole((Hole) fields.get(hX + hY * height));
    }


    public void replaceField(int x, int y, Field field) {
        field.SetNeighbor(Direction.Up, fields.get(x + y * height).GetNeighbor(Direction.Up));
        field.SetNeighbor(Direction.Down, fields.get(x + y * height).GetNeighbor(Direction.Down));
        field.SetNeighbor(Direction.Left, fields.get(x + y * height).GetNeighbor(Direction.Left));
        field.SetNeighbor(Direction.Right, fields.get(x + y * height).GetNeighbor(Direction.Right));

        field.GetNeighbor(Direction.Up).SetNeighbor(Direction.Down, field);
        field.GetNeighbor(Direction.Down).SetNeighbor(Direction.Up, field);
        field.GetNeighbor(Direction.Left).SetNeighbor(Direction.Right, field);
        field.GetNeighbor(Direction.Right).SetNeighbor(Direction.Left, field);
        fields.set(x + y * height, field);
    }

    public void addWorker(int x, int y, int id) {
        Game.getInstance().addWorker(new Worker(fields.get(x + y * height), id),  id);
    }

    public void addBox(int x, int y) {
        new Box(fields.get(x + y * height), 1);
    }

}
