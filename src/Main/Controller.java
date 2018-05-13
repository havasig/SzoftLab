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
                chars. add('u');
                chars.add('i');
                chars.add('o');
                chars.add('j');
                chars.add('k');
                chars.add('l');
            case 2:
                chars.add('q');
                chars.add('w');
                chars.add('e');
                chars.add('a');
                chars.add('s');
                chars.add('d');

                chars.add((char)KeyEvent.VK_UP);
                chars.add((char)KeyEvent.VK_LEFT);
                chars.add((char)KeyEvent.VK_DOWN);
                chars.add((char)KeyEvent.VK_RIGHT);
                chars.add('0');
                chars.add('1');

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
    }

    private void workerAction(int id, Field.FieldState f){
        Game.getInstance().placeObject(id, f);
    }

    private void workerMove(int id,Direction d){
        Game.getInstance().moveWorker(id, d);
    }

    public boolean Run() {
        boolean stHappened = false;
        for(int i = 0; i < events.size(); i++){
            char step = events.get(i).getKeyChar();
            switch (step){
                case (char)KeyEvent.VK_UP: workerMove(1,Direction.Up); break;
                case (char)KeyEvent.VK_LEFT: workerMove(1,Direction.Left); break;
                case (char)KeyEvent.VK_DOWN: workerMove(1,Direction.Down); break;
                case (char)KeyEvent.VK_RIGHT: workerMove(1,Direction.Right); break;
                case '1': workerAction(1, Field.FieldState.Honey); break;
                case '0': workerAction(1, Field.FieldState.Oil); break;

                case 'w': workerMove(2,Direction.Up); break;
                case 'a': workerMove(2,Direction.Left); break;
                case 's': workerMove(2,Direction.Down); break;
                case 'd': workerMove(2,Direction.Right); break;
                case 'q': workerAction(2, Field.FieldState.Honey); break;
                case 'e': workerAction(2, Field.FieldState.Oil); break;

                case 'i': workerMove(3,Direction.Up); break;
                case 'j': workerMove(3,Direction.Left); break;
                case 'k': workerMove(3,Direction.Down); break;
                case 'l': workerMove(3,Direction.Right); break;
                case 'u': workerAction(3, Field.FieldState.Honey); break;
                case 'o': workerAction(3, Field.FieldState.Oil); break;
            }
        }
        if(!events.isEmpty()){
            stHappened = true;
        }
        events.clear();
        return stHappened;
    }
}
