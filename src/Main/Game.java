package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A jatek fo futasaert felel, valamint a pontozast kezeli. A jatek lelke.
 * Egy futas alatt csak egy lehet belole.
 */
public class Game {

    //singleton/////////////////////
    private static final Game game = new Game();
    ///////////////////////////////

    /**
     * A jatekban levo Worker-öket tarolja.
     */
    private HashMap<Integer, Worker> workers;

    /**
     * A jelenleg mozgo jatekost tarolja.
     */
    private Worker currentWorker;

    /**
     * A jatekteret tarolja.
     */
    private Factory map;

    private boolean changed;
    /**
     * A Game konstruktora.
     */
    private Game() {
        map = new Factory();
        workers = new HashMap<>();
        changed = true;
    }

    static Game getInstance() {
        return game;
    }

    public static void main(String[] args) {
        Game.getInstance().StartGame();
    }

    /**
     * A jatek inditasaert felel
     */
    private void StartGame() {
        LoadGame("tesztek/LoadGame_test1.txt");
        gameLoop();
    }

    private void Error(String message){
        System.out.println(message);
    }

    private boolean LoadGame(String file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] parts = line.split(" ");
            if(parts.length!=2)
                throw new Exception();
            int width = Integer.parseInt(parts[0]);
            int height = Integer.parseInt(parts[1]);
            map.setWidth(width);
            map.setHeight(height);
            map.GenerateMap(width, height);
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
                            this.map.createColumn(j, i);
                            break;
                        case 'D':
                            this.map.createDestination(j, i);
                            break;
                        case 'H':
                            this.map.createHole(j, i, HoleState.Open);
                            break;
                        case 'h':
                            this.map.createHole(j, i, HoleState.Closed);
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
                            this.map.createSwitch(j,i,switchAndHole.get(index+2), switchAndHole.get(index+3));
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
                            this.map.createSwitch(j,i,switchAndHole.get(indexS+2), switchAndHole.get(indexS+3));
                            break;
                        case '_':
                            break;
                        default:
                            throw new Exception();
                    }
                    char y = map.get(i)[j].charAt(1);
                    switch (y){
                        case 'B':
                            this.map.addBox(j,i);
                            break;
                        case '1':
                            this.map.addWorker(j, i,1);
                            break;
                        case '2':
                            this.map.addWorker(j, i,2);
                            break;
                        case '3':
                            this.map.addWorker(j, i,3);
                            break;
                        case '4':
                            this.map.addWorker(j, i,4);
                            break;
                        case '5':
                            this.map.addWorker(j, i,5);
                            break;
                        case '6':
                            this.map.addWorker(j, i,6);
                            break;
                        case '7':
                            this.map.addWorker(j, i,7);
                            break;
                        case '8':
                            this.map.addWorker(j, i,8);
                            break;
                        case '9':
                            this.map.addWorker(j, i,9);
                            break;
                        case '_':
                            break;
                        default:
                            throw new Exception();
                    }
                    char z = map.get(i)[j].charAt(2);
                    switch (z){
                        case 'M':
                            this.map.getField(j,i).setSplich(Field.FieldState.Honey);
                            break;
                        case 'O':
                            this.map.getField(j,i).setSplich(Field.FieldState.Oil);
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
        return true;
    }

    /**
     * A jatek befejezeseert felel
     */
    void EndGame() {
        System.out.println("Game over.");
        System.out.println("Points:");
        HashMap<Integer, Worker> winners = new HashMap<>();
        int mostPoints = 0;
        for (Map.Entry<Integer, Worker> worker : Game.getInstance().getWorkers().entrySet()) {
            int points = worker.getValue().getPoints();
            Worker work = worker.getValue();
            int id = worker.getKey();

            System.out.println("Worker " + Integer.toString(id) + ": " + Integer.toString(points));

            //Get the winner(s) while we write the points
            if (points >= mostPoints) {
                winners.put(id, work);
                if (points > mostPoints) {
                    winners.clear();
                    winners.put(id, work);
                    mostPoints = points;
                }
            }
        }

        if (winners.size() == 0) {
            System.out.println("Something went wrong, there are no winners!");
            return;
        }

        if (winners.size() > 1)
            System.out.println("The winners are: ");
        else
            System.out.println("The winner is:");

        for (Map.Entry<Integer, Worker> winner : winners.entrySet()) {
            System.out.println("Worker " + winner.getKey().toString());
        }
    }

    /**
     * A jelenleg mozog jatekos pontszamat megnoveli eggyel.
     */
    void SetPoint() {
        currentWorker.IncrementPoints();
    }


    /**
     * @return a jatekban levo Worker-ek.
     */
    HashMap<Integer, Worker> getWorkers() {
        return workers;
    }

    /**
     * @return a jatekter
     */
    public Factory getMap() {
        return map;
    }

    /**
     * @param currentWorker a jelenleg mozgo jatekos.
     */
    private void setCurrentWorker(Worker currentWorker) {
        this.currentWorker = currentWorker;
    }

    /**
     * Hozzaad egy Worker-t a jelenleg a jatekban levo Worker-okhoz.
     *
     * @param w:  a Worker, amit hozzaad.
     * @param id: a hozaadott Worker azonositoja
     */
    void addWorker(Worker w, int id) {
        workers.put(id, w);
    }

    /**
     * Mozgatja a Worker-t a jatekterben.
     *
     * @param id:  ezen azonositoju Worker kezdemenyezte a mozgast.
     * @param dir: ebbe az iranyba mozgatja a Worker-t
     */
    void moveWorker(int id, Direction dir) {
            setCurrentWorker(workers.get(id));
            workers.get(id).Control(dir);
    }

    /**
     * Kenoanyagot helyez el a jatekter egy mezejen
     *
     * @param id: ezen azonositoju Worker kezdemenyezte az elhelyezest.
     * @param d:  ebbe az iranyba helyezi el a kenoanyagot
     * @param f:  ilyen tipusu kenoanyagot helyez el
     */
    void placeObject(int id, String d, String f) {
        workers.get(id).placeObject(Direction.valueOf(d), Field.FieldState.valueOf(f));
    }

    //This is the game itself, handles the inputs
    private void gameLoop() {
        char c;
        while (!map.ThisIsTheEnd()){
            if (changed) {
                System.out.println(map.Draw());
                changed = false;
            }
            try {
                c = (char) System.in.read();
                if(c == 'w') moveWorker(1, Direction.Up );
                if(c == 's') moveWorker(1, Direction.Down );
                if(c == 'd') moveWorker(1, Direction.Right );
                if(c == 'a') moveWorker(1, Direction.Left );
                if(c != '\n')changed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(map.Draw());
        EndGame();
            //A round:
        /*while(true)
        {
            //Loop for each worker
            for (Map.Entry<Integer, Worker> worker : workers.entrySet())
            {
                System.out.print(map.Draw());
                System.out.println("This is worker " + worker.getKey() + "'s turn.");
                System.out.println("Type Up, Right, Left or Down to move");

                String input = "";
                switch(input)
                {
                    case "Up":
                        worker.getValue().Control(Direction.Up);
                        break;
                    case "Left":
                        worker.getValue().Control(Direction.Left);
                        break;
                    case "Right":
                        worker.getValue().Control(Direction.Right);
                        break;
                    case "Down":
                        worker.getValue().Control(Direction.Down);
                        break;
                }
                //Must we check every worker step, or is it enough to check every round outside the foreach?
                if(map.ThisIsTheEnd())
                {
                    EndGame();
                    return;
                }
            }

        }*/
    }
}
