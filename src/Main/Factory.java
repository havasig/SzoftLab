package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static Main.Movement.Moved;

public class Factory  {
    private static String switchHole;
    private static int swCount;
    private ArrayList<Field> fields;
    private HashMap<Integer, Hole> holes;
    private int width, height;

    private void Init() {
        fields = new ArrayList<>();
        holes = new HashMap<>();
    }

    private Field getField(int x, int y) {
        return fields.get((y * width) + x);
    }

    //TODO megváltoztatni a működését
    public String getPos(Field f) {
        return String.valueOf(fields.indexOf(f) / width) +
                ":" +
                fields.indexOf(f) % width;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
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

    void GenerateMap(int width, int height) {
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
        //TODO kirendezni pl.: linking()
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
    public boolean ReadMap(String file){
/*
        try{
        BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] parts = line.split(" ");
            if(parts.length!=2)
                throw new Exception();
        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        this.width = width;
        this.height = height;
        GenerateMap(width, height);
        int actLine = 0;
        ArrayList<String[]> map = new ArrayList<>();
        ArrayList<Integer> switchAndHole = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(actLine<height){
                    String[] fields = line.split(" ");
                    if(fields.length!=width)
                        throw new Exception();
                    map.add(fields);
                    actLine++;
                }
                    else {
                    String[] row = line.split(" ");
                    String[] coords = row[1].split(";");
                    String[] switchCoords = coords[0].split(":");
                    String[] holeCoords = coords[1].split(":");
                    Integer sx = Integer.parseInt(switchCoords[0]);
                    Integer sy = Integer.parseInt(switchCoords[1]);
                    Integer hx = Integer.parseInt(holeCoords[0]);
                    Integer hy = Integer.parseInt(holeCoords[1]);

                    switchAndHole.add(sx);
                    switchAndHole.add(sy);
                    switchAndHole.add(hx);
                    switchAndHole.add(hy);
                }
            }
            for (String[] aMap : map) {
                if (aMap[0].charAt(0) != 'X' || aMap[0].charAt(1) != '_' || aMap[0].charAt(2) != '_')
                    throw new Exception();
            }
            for (String[] aMap : map) {
                if (aMap[width - 1].charAt(0) != 'X' || aMap[width - 1].charAt(1) != '_' || aMap[width - 1].charAt(2) != '_')
                    throw new Exception();
            }
            for(int i = 0; i < width; i++){
                if(map.get(0)[i].charAt(0)!='X'||map.get(0)[i].charAt(1)!='_'||map.get(0)[i].charAt(2)!='_')
                    throw new Exception();
            }
            for(int i = 0; i < width; i++){
                if(map.get(map.size()-1)[i].charAt(0)!='X'||map.get(map.size()-1)[i].charAt(1)!='_'||map.get(map.size()-1)[i].charAt(2)!='_')
                    throw new Exception();
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
                                throw new Exception();
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
                                throw new Exception();
                            createSwitch(j,i,switchAndHole.get(indexS+2), switchAndHole.get(indexS+3));
                            break;
                        case '_':
                            break;
                        default:
                            throw new Exception();
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
                            throw new Exception();
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
                            throw new Exception();
                    }
                }
            }
            System.out.println("A beolvasás sikerrel lezajlott.");
        }
        catch(Exception e){
            Error("Nem sikerült beolvasni.");
            return false;
        }
*/
        return true;
    }

    private void replaceField(int x, int y, Field field) {
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

    private void Error(String err) {
        System.out.println(err);
    }
}
