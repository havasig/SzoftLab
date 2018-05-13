package Main;

import Graphics.View;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A jatek fo futasaert felel, valamint a pontozast kezeli. A jatek lelke.
 * Egy futas alatt csak egy lehet belole.
 */
public class Game{
    //singleton/////////////////////
    private static final Game game = new Game();
    /**
     * A Game konstruktora.
     */
    private Game() {
        map = new Factory();
        workers = new HashMap<>();
        changed = true;
        menu = Menu.getInstance();
    }

    /**
     * A játék ablaka
     */
    private static JFrame window;

    static Game getInstance() {
        return game;
    }
    ///////////////////////////////

    private static JFrame menu;

    /**
     * A jelenleg mozgo jatekost tarolja.
     */
    private Worker currentWorker;
    /**
     * A jatekban levo Worker-öket tarolja.
     */
    private HashMap<Integer, Worker> workers;
    /**
     * A jatekteret tarolja.
     */
    private Factory map;
    /**
     * A jatek nézetét reprezentálja
     */
    private View view;
    private boolean changed;

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
     * @return a nézet
     */

    public View getView() {
        return view;
    }

    public static void main(String[] args) {
        ((Menu) menu).Run();
    }

    /**
     * A jatek inditasaert felel
     */
    public void StartGame() {
        createAndShowGUI(this);
        map.ReadMap("test2.txt");
        view.validate();
        window.setVisible(true);
        gameLoop();
    }

    /**
     * A jatek befejezeseert felel
     */
    void EndGame() {
        String out;
        out = "Game over.\nPoints:\n";
        HashMap<Integer, Worker> winners = new HashMap<>();
        int mostPoints = 0;
        for (Map.Entry<Integer, Worker> worker : Game.getInstance().getWorkers().entrySet()) {
            int points = worker.getValue().getPoints();
            Worker work = worker.getValue();
            int id = worker.getKey();
            out = out + "Worker " + Integer.toString(id) + ": " + Integer.toString(points)+"\n";
            //System.out.println("Worker " + Integer.toString(id) + ": " + Integer.toString(points));

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
            out = out + out + "Something went wrong, there are no winners!\n";
            //System.out.println("Something went wrong, there are no winners!");
            return;
        }

        if (winners.size() > 1)
            //System.out.println("The winners are: ");
            out = out + "The winners are: ";
        else
            //System.out.println("The winner is: ");
            out = out + "The winner is: ";
        for (Map.Entry<Integer, Worker> winner : winners.entrySet()) {
            //System.out.println("Worker " + winner.getKey().toString());
            out = out + "Worker " + winner.getKey().toString()+" ";
        }
        JOptionPane.showMessageDialog(null, out);
        int input = JOptionPane.showOptionDialog(null, out, "Results", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if(input == JOptionPane.OK_OPTION)
        {
            ((Menu) menu).Run();
        }
    }

    /**
     * A jelenleg mozgo jatekos pontszamat megnoveli eggyel.
     */
    void SetPoint() {
        if (currentWorker != null) {
            currentWorker.IncrementPoints();
        }
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
     */
    void addWorker(Worker w) {
        workers.put(w.getIdentifier(), w);
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
            view.validate();
    }

    /**
     * Kenoanyagot helyez el a jatekter egy mezejen
     *
     * @param id: ezen azonositoju Worker kezdemenyezte az elhelyezest.
     * @param f:  ilyen tipusu kenoanyagot helyez el
     */
    void placeObject(int id, String f) {
        workers.get(id).placeObject(Field.FieldState.valueOf(f));
        view.validate();
    }

    //This is the game itself, handles the inputs
    //TODO
    private void gameLoop() {
        char c;
        while (!map.ThisIsTheEnd()){
            if (changed) {
                changed = false;
            }
            try {
                c = (char) System.in.read();
                if(c == 'w') moveWorker(1, Direction.Up );
                if(c == 's') moveWorker(1, Direction.Down );
                if(c == 'd') moveWorker(1, Direction.Right );
                if(c == 'a') moveWorker(1, Direction.Left );
                if(c == 'q') placeObject(1, "Oil");
                if(c == 'e') placeObject(1, "Honey");
                if(c != '\n')changed = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EndGame();
    }

    private void createAndShowGUI(Game game) {
        //Create and set up the window.
        window = new JFrame("Sokoban");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        view = new View(game, window);
    }
}
