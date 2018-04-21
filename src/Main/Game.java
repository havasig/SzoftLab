package Main;

import java.util.HashMap;
import java.util.Map;

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
        gameLoop();
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

    //This is the game itself, handles the inputs
    public void gameLoop(){
        //A round:
        while(true)
        {
            //Loop for each worker
            for (Map.Entry<Integer, Worker> worker : workers.entrySet())
            {
                System.out.print(map.Draw());
                System.out.println("This is worker " + worker.getKey() + "'s turn.");
                System.out.println("Type Up, Right, Left or Down to move");
                //TODO - reliable input
                String input = new String("");
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

        }
    }
}
