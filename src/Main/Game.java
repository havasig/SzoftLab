package Main;

import java.util.HashMap;

public class Game {

    //singleton/////////////////////
    private static Game game = new Game();

    public static Game getInstance() {
        return game;
    }

    private Game() {
        map = new Factory();
        workers = new HashMap<>();
    }

    ///////////////////////////////
    private HashMap<Integer, Worker> workers;
    private Worker currentWorker;
    private int livePlayerCount;
    private Factory map;

    public static void main(String[] args) {
        Game.getInstance().StartGame();
    }

    private void StartGame() {
        map.Load("");
        //TODO

    }

    private void EndGame() {
        //TODO
    }

    public void SetPoint() {
        //TODO
        currentWorker.IncrementPoints();
    }

    public void PlayerDied() {
        livePlayerCount--;
        if (livePlayerCount == 0) {
            EndGame();
        }
    }

    public HashMap<Integer, Worker> getWorkers()
    {
        return workers;
    }

    public Factory getMap() {
        return map;
    }

    public void setCurrentWorker(Worker currentWorker) {
        this.currentWorker = currentWorker;
    }

    public void addWorker(Worker w, int id) {
        workers.put(id, w);
    }

    public void moveWorker(int id, String dir) {
        workers.get(id).Control(Direction.valueOf(dir));
        setCurrentWorker(workers.get(id));
    }
}
