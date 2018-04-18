package Main;

import java.util.ArrayList;
import java.util.List;

public class Game {

    //singleton////
    private static Game game = new Game();
    private Game() {
    }

    public static Game getInstance() {
        return game;
    }
    ///////////////

    private int livePlayerCount;
    private Factory map;
    private int currentLevel = 1;
    private ArrayList<Worker> workers;
    private Worker currentWorker;

    public static void main(String[] args) {
        Game.getInstance().StartGame();
    }

    private void StartGame() {
        //TODO
    }

    private void EndGame() {
        //TODO
    }

    public void SetPoint() {
        //TODO
    }

    public void PlayerDied() {
        livePlayerCount--;
        if (livePlayerCount == 0) {
            EndGame();
        }
    }

    public void setCurrentWorker(Worker currentWorker) {
        this.currentWorker = currentWorker;
    }

    public void addWorker(Worker w){
        workers.add(w);
    }
}
