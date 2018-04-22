package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static Main.Movement.Moved;

public class Factory implements Drawable {
    private static String switchHole;
    private static int swCount;
    private ArrayList<Field> fields;
    private HashMap<Integer, Hole> holes;
    private int width, height;

    public static void setSwitchHole(String s) {
        swCount++;
        switchHole += String.valueOf(swCount) + ", ";
        switchHole += s;
    }

    void Init() {
        fields = new ArrayList<>();
        holes = new HashMap<>();
    }

    private Field getField(int x, int y) {
        return fields.get((y * width) + x);
    }

    void Load(String name) {
        Init();
        //TODO
    }

    public String getPos(Field f) {
        String pos = String.valueOf(fields.indexOf(f) / width) +
                ":" +
                fields.indexOf(f) % width;
        return pos;
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
        Init();
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

    public void ReadMap(String file) throws IOException {
        BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            String line = null;
            line = br.readLine();
            String[] parts = line.split(" ");
        for (String part : parts) {
            if (part.length() != 1)
                throw new Error("A tesztesetet nem sikerült futtatni.");
        }
            if(parts.length!=2)
                throw new Error("A tesztesetet nem sikerült futtatni.");
        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        this.width = width;
        this.height = height;
        GenerateMap(width,height);
        int actLine = 0;
        ArrayList<String[]> map = new ArrayList<String[]>();
        ArrayList<Integer> switchAndHole = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                if(actLine<height){
                    String[] fields = line.split(" ");
                    if(fields.length!=width)
                        throw new Error("A tesztesetet nem sikerült futtatni.");
                    map.add(fields);
                    actLine++;
                }
                    else {
                    Integer sx = Integer.parseInt(Character.toString(line.charAt(3)));
                    Integer sy = Integer.parseInt(Character.toString(line.charAt(5)));
                    Integer hx = Integer.parseInt(Character.toString(line.charAt(7)));
                    Integer hy = Integer.parseInt(Character.toString(line.charAt(9)));

                    switchAndHole.add(sx);
                    switchAndHole.add(sy);
                    switchAndHole.add(hx);
                    switchAndHole.add(hy);
                }
            }
            for(int i = 1; i < map.size()-1; i++){
                for(int j = 1; j < width-1; j++){
                    char x = map.get(i)[j].charAt(0);
                    switch (x){
                        case 'X':
                            createColumn(j, i);
                            break;
                        case 'D':
                            createDestination(j, i);
                            break;
                        case 'H':
                            createHole(j, i, HoleState.Open);
                            break;
                        case 'h':
                            createHole(j, i, HoleState.Closed);
                            break;
                        case 's':
                            int index=-1;
                            for(int k=0; k < switchAndHole.size(); k++) {
                            if (switchAndHole.get(k).equals(j))
                                if (switchAndHole.get(k + 1).equals(i))
                                    index=k;
                            }
                            if(index==-1)
                                throw new Error("A tesztesetet nem sikerült futtatni.");
                            createSwitch(j,i,switchAndHole.get(index+2), switchAndHole.get(index+3));
                            break;
                        case 'S':
                            int indexS=-1;
                            for(int k=0; k < switchAndHole.size(); k++) {
                                if (switchAndHole.get(k).equals(j))
                                    if (switchAndHole.get(k + 1).equals(i))
                                        indexS=k;
                            }
                            if(indexS==-1)
                                throw new Error("A tesztesetet nem sikerült futtatni.");
                            createSwitch(j,i,switchAndHole.get(indexS+2), switchAndHole.get(indexS+3));
                            break;
                        case '_':
                            break;
                        default:
                            throw new Error("A tesztesetet nem sikerült futtatni.");
                    }
                    char y = map.get(i)[j].charAt(1);
                    switch (y){
                        case 'B':
                            addBox(j,i);
                            break;
                        case '1':
                            addWorker(j, i,1);
                            break;
                        case '2':
                            addWorker(j, i,2);
                            break;
                        case '3':
                            addWorker(j, i,3);
                            break;
                        case '4':
                            addWorker(j, i,4);
                            break;
                        case '5':
                            addWorker(j, i,5);
                            break;
                        case '6':
                            addWorker(j, i,6);
                            break;
                        case '7':
                            addWorker(j, i,7);
                            break;
                        case '8':
                            addWorker(j, i,8);
                            break;
                        case '9':
                            addWorker(j, i,9);
                            break;
                        case '_':
                            break;
                        default:
                            throw new Error("A tesztesetet nem sikerült futtatni.");
                    }
                    char z = map.get(i)[j].charAt(2);
                    switch (z){
                        case 'M':
                            getField(j,i).setSplich(Field.FieldState.Honey);
                            break;
                        case 'O':
                            getField(j,i).setSplich(Field.FieldState.Oil);
                            break;
                        case '_':
                            break;
                        default:
                            throw new Error("A tesztesetet nem sikerült futtatni.");
                    }
                }
            }

    }


    public String Draw() {
        StringBuilder map = new StringBuilder();
        switchHole = "";
        swCount = 0;
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
        map.append(switchHole);
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
        switch (state) {
            case Closed:
                hole.SetClosed();
                break;
            case Open:
                hole.SetOpen();
                break;
        }
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
        for (Integer i : Game.getInstance().getWorkers().keySet())
            if (i == id) {
                System.out.println("Id is not unique!");
                return;
            }
        Game.getInstance().addWorker(new Worker(getField(x, y), id), id);
    }

    public void addBox(int x, int y) {
        new Box(getField(x, y), 1);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
