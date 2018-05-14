package Main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Controller {
    private ArrayList<KeyEvent> events = new ArrayList<>();
    private ArrayList<Character> chars = new ArrayList<>();

    //inicializálja, hogy milyen billentyűket fogad be
    public void fillChars(){
        int numberOfPlayers = Game.getInstance().getWorkers().size();
        switch(numberOfPlayers){
            case 3:
                chars.add('8');
                chars.add('4');
                chars.add('5');
                chars.add('6');
                chars.add('7');
                chars.add('9');
            case 2:
                chars.add('q');
                chars.add('w');
                chars.add('e');
                chars.add('a');
                chars.add('s');
                chars.add('d');

                chars. add('u');
                chars.add('i');
                chars.add('o');
                chars.add('j');
                chars.add('k');
                chars.add('l');

        }
    }

    public void AddEvent(KeyEvent e){
        boolean addable = true;
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getKeyChar() == e.getKeyChar())
                addable = false;
        }
        if(addable && chars.contains(e.getKeyChar())){
            events.add(e);
        }
        Run();
    }

    private void workerAction(int id, Field.FieldState f){
        Game.getInstance().placeObject(id, f);
    }

    private void workerMove(int id,Direction d){
        Game.getInstance().moveWorker(id, d);
    }

    public void Run() {
        boolean stHappened = false;
        for(int i = 0; i < events.size(); i++){
            char step = events.get(i).getKeyChar();;
            switch (step){
                case '8': if(Game.getInstance().getWorkers().get(1).isAlive()) workerMove(1,Direction.Up); break;
                case '4': if(Game.getInstance().getWorkers().get(1).isAlive()) workerMove(1,Direction.Left); break;
                case '5': if(Game.getInstance().getWorkers().get(1).isAlive()) workerMove(1,Direction.Down); break;
                case '6': if(Game.getInstance().getWorkers().get(1).isAlive()) workerMove(1,Direction.Right); break;
                case '7': if(Game.getInstance().getWorkers().get(1).isAlive()) workerAction(1, Field.FieldState.Honey); break;
                case '9': if(Game.getInstance().getWorkers().get(1).isAlive()) workerAction(1, Field.FieldState.Oil); break;

                case 'w': if(Game.getInstance().getWorkers().get(2).isAlive()) workerMove(2,Direction.Up); break;
                case 'a': if(Game.getInstance().getWorkers().get(2).isAlive()) workerMove(2,Direction.Left); break;
                case 's': if(Game.getInstance().getWorkers().get(2).isAlive()) workerMove(2,Direction.Down); break;
                case 'd': if(Game.getInstance().getWorkers().get(2).isAlive()) workerMove(2,Direction.Right); break;
                case 'q': if(Game.getInstance().getWorkers().get(2).isAlive()) workerAction(2, Field.FieldState.Honey); break;
                case 'e': if(Game.getInstance().getWorkers().get(2).isAlive()) workerAction(2, Field.FieldState.Oil); break;

                case 'i': if(Game.getInstance().getWorkers().get(3).isAlive()) workerMove(3,Direction.Up); break;
                case 'j': if(Game.getInstance().getWorkers().get(3).isAlive()) workerMove(3,Direction.Left); break;
                case 'k': if(Game.getInstance().getWorkers().get(3).isAlive()) workerMove(3,Direction.Down); break;
                case 'l': if(Game.getInstance().getWorkers().get(3).isAlive()) workerMove(3,Direction.Right); break;
                case 'u': if(Game.getInstance().getWorkers().get(3).isAlive()) workerAction(3, Field.FieldState.Honey); break;
                case 'o': if(Game.getInstance().getWorkers().get(3).isAlive()) workerAction(3, Field.FieldState.Oil); break;
            }
        }
        if(!events.isEmpty()){
            stHappened = true;
        }
        events.clear();
        if(Game.getInstance().getMap().ThisIsTheEnd())
            Game.getInstance().EndGame();

    }
}
