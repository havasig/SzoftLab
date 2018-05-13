package Main;

import Graphics.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static Main.Movement.Moved;

public class Factory {
    private ArrayList<Field> fields;
    private HashMap<Integer, Hole> holes;
    private HashMap<Integer, Switch> switches;
    private int width, height;

    Factory() {
        fields = new ArrayList<>();
        switches = new HashMap<>();
        holes = new HashMap<>();
    }

    private Field getField(int x, int y) {
        return fields.get((y * width) + x);
    }

    public Point getPos(Field f) {
        return new Point((fields.indexOf(f) / width), fields.indexOf(f) % width);
    }

    int index(int x, int y) {
        int num = (y * width) + x;
        return num;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    boolean ThisIsTheEnd() {
        //set all field to unchecked
        for (Field field : fields)
            field.setChecked(false);

        //Iterate through workers, if any of them can move something, then it is not over
        HashMap<Integer, Worker> workers = Game.getInstance().getWorkers();
        for (Worker worker : workers.values()) {
            //Don't test for Workers that died.
            if (!worker.isAlive())
                continue;
            if (worker.IsThereMovement(worker.field) == Moved)
                return false;
        }
        return true;
    }

    private void linkMap() {
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

    //TODO
    public void ReadMap(String fileName) {
        try {
            int count = 0;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String[] parts = line.split(" ");
            if (parts.length != 2)
                throw new Exception();
            this.width = Integer.parseInt(parts[0]);
            this.height = Integer.parseInt(parts[1]);

            for (int actual = 0; actual < height; actual++) {
                line = br.readLine();
                String[] fields = line.split(" ");
                for (String field : fields) {

                    switch (field.charAt(0)) {
                        case 'X':
                            Column column = new Column(this);
                            Game.getInstance().getView().AddField(new GraphicsColumn(column));
                            this.fields.add(column);
                            break;
                        case 'D':
                            Destination destination = new Destination(this);
                            Game.getInstance().getView().AddField(new GraphicsDestination(destination));
                            this.fields.add(destination);
                            break;
                        case 'H':
                            Hole holeO = new Hole(this);
                            holeO.SetOpen();
                            Game.getInstance().getView().AddField(new GraphicsHole(holeO));
                            this.fields.add(holeO);
                            holes.put(this.fields.indexOf(holeO), holeO);
                            break;
                        case 'h':
                            Hole holeC = new Hole(this);
                            holeC.SetOpen();
                            Game.getInstance().getView().AddField(new GraphicsHole(holeC));
                            this.fields.add(holeC);
                            holes.put(this.fields.indexOf(holeC), holeC);
                            break;
                        case 's':
                        case 'S':
                            Switch switch_ = new Switch(this);
                            Game.getInstance().getView().AddField(new GraphicsSwitch(switch_));
                            this.fields.add(switch_);
                            switches.put(this.fields.indexOf(switch_), switch_);
                            break;
                        case '_':
                            Field field_ = new Field(this);
                            Game.getInstance().getView().AddField(new GraphicsField(field_));
                            this.fields.add(field_);
                            break;
                        default:
                            throw new Exception();
                    }


                    switch (field.charAt(1)) {
                        case 'B':
                        case 'b':
                            Box box = new Box(this.fields.get(count), 1);
                            //TODO  Game.getInstance().getView().AddMovable(new GraphicsBox(boxfree);
                            //TODO holtalan lyuk esetén mivan
                            break;
                        case '_':
                            break;
                        default:
                            int num = Character.getNumericValue(field.charAt(1));
                            Game.getInstance().addWorker(new Worker(this.fields.get(count), num));
                            //TODO
                    }

                    switch (field.charAt(2)) {
                        case 'M':
                            this.fields.get(count).setSplich(Field.FieldState.Honey);
                            break;
                        case 'O':
                            this.fields.get(count).setSplich(Field.FieldState.Oil);
                            break;
                        case '_':
                            break;
                        default:
                            throw new Exception();

                    }
                    count++;
                }
            }

            while ((line = br.readLine()) != null) {
                parts = line.split(" ");
                String[] coords = parts[1].split(";");
                String[] switchCoords = coords[0].split(":");
                String[] holeCoords = coords[1].split(":");
                int sx = Integer.parseInt(switchCoords[0]);
                int sy = Integer.parseInt(switchCoords[1]);
                int hx = Integer.parseInt(holeCoords[0]);
                int hy = Integer.parseInt(holeCoords[1]);

                switches.get(index(sx, sy)).SetHole(holes.get(index(hx, hy)));
            }
            linkMap();
        } catch (Exception e) {
            Error("Nem sikerült beolvasni.");
        }
    }

    private void Error(String err) {
        System.out.println(err);
    }
}
