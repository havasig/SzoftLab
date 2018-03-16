package Main;

import java.util.List;

public class Game {

    //singleton////
    private static Game game = new Game();
    private Game(){}
    public static Game getInstance(){
        return game;
    }
    ///////////////

    private List<Factory> levels;
    private int currentLevel =1;
    private int livePlayerCount;

    private void StartGame(){

    }
    private void EndGame(){}
    public void SetPoint(){}

    public void PlayerDied(){
        livePlayerCount--;
        if (livePlayerCount == 0){
            EndGame();
        }
    }

    public static void main(String[] args) {
        Game.getInstance().StartGame();
    }
}
