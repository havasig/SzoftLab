package Main;

import java.util.HashMap;

/**
 * A jatek fo futasaert felel, valamint a pontozast kezeli. A jatek lelke.
 * Egy futas alatt csak egy lehet belole.
 */
public class Game {

    //singleton/////////////////////
    private static Game game = new Game();

    public static Game getInstance() {
        return game;
    }

    /**
     * A Game konstruktora.
     */
    private Game() {
        map = new Factory();
        workers = new HashMap<>();
    }

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
     * A jelenleg eletben levo jatekosok szama.
     */
    private int livePlayerCount;
    /**
     * A jatekteret tarolja.
     */
    private Factory map;

    public static void main(String[] args) {
        Game.getInstance().StartGame();
    }

    /**
     * A jatek inditasaert felel
     */
    private void StartGame() {
        map.Load("");
        //gameLoop();
        //TODO

    }

    /**
     * A jatek befejezeseert felel
     */
    private void EndGame() {
        //TODO
        System.out.println("Game Over");
    }

    /**
     * A jelenleg mozog jatekos pontszamat megnoveli eggyel.
     */
    public void SetPoint() {
        currentWorker.IncrementPoints();
    }

    /**
     * Csokkenti a jelenleg eletben levo jatekosok szamat eggyel.
     */
    public void PlayerDied() {
        livePlayerCount--;
        if (livePlayerCount == 0) {
            EndGame();
        }
    }

    /**
     * @return a jatekban levo Worker-ek.
     */
    public HashMap<Integer, Worker> getWorkers() {
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
    public void setCurrentWorker(Worker currentWorker) {
        this.currentWorker = currentWorker;
    }

    /**
     * Hozzaad egy Worker-t a jelenleg a jatekban levo Worker-okhoz.
     * @param w: a Worker, amit hozzaad.
     * @param id: a hozaadott Worker azonositoja
     */
    public void addWorker(Worker w, int id) {
        workers.put(id, w);
        livePlayerCount++;
    }

    /**
     * Mozgatja a Worker-t a jatekterben.
     * @param id: ezen azonositoju Worker kezdemenyezte a mozgast.
     * @param dir: ebbe az iranyba mozgatja a Worker-t
     */
    public void moveWorker(int id, String dir) {
        setCurrentWorker(workers.get(id));
        workers.get(id).Control(Direction.valueOf(dir));
    }

    /**
     * Kenoanyagot helyez el a jatekter egy mezejen
     * @param id: ezen azonositoju Worker kezdemenyezte az elhelyezest.
     * @param d: ebbe az iranyba helyezi el a kenoanyagot
     * @param f: ilyen tipusu kenoanyagot helyez el
     */
    public void placeObject(int id, String d, String f) {
        workers.get(id).placeObject(Direction.valueOf(d), Field.FieldState.valueOf(f));
    }

    //This is the game itself, handles the inputs
    public void gameLoop() {
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
