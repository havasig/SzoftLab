package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventHandler implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e){
        Game.getInstance().controller.AddEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }
}
