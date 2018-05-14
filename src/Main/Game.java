package Main;

import Graphics.View;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

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
        controller = new Controller();
    }

    /**
     * A játék ablaka
     */
    private static JFrame window;

    static Game getInstance() {
        return game;
    }
    ///////////////////////////////

    private static Menu menu;

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
    Controller controller;  //Havi írta ide, le kéne dokumentálni

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

    public static void main(String[] args)
    {
        game.createAndShowGUI();
    }

    /**
     * A jatek inditasaert felel
     */
    void StartGame() {
        map = new Factory();
        workers = new HashMap<>();
        view = new View(this, window);
        window.remove(menu);
        window.add(view);
        view.addKeyListener(new KeyEventHandler());
        map.ReadMap("level1.txt");
        window.setVisible(true);
        controller.fillChars();
        view.validate();
    }

    /**
     * A jatek befejezeseert felel
     */
    void EndGame() {
        StringBuilder out = new StringBuilder("Game over.\nPoints:\n");
        HashMap<Integer, Worker> winners = new HashMap<>();
        int mostPoints = 0;
        for (Map.Entry<Integer, Worker> worker : Game.getInstance().getWorkers().entrySet()) {
            int points = worker.getValue().getPoints();
            Worker work = worker.getValue();
            int id = worker.getKey();
            out.append("Worker ").append(Integer.toString(id)).append(": ").append(Integer.toString(points)).append("\n");

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
            out.append(out).append("Something went wrong, there are no winners!\n");
            //System.out.println("Something went wrong, there are no winners!");
            return;
        }

        if (winners.size() > 1)
            //System.out.println("The winners are: ");
            out.append("The winners are: ");
        else
            //System.out.println("The winner is: ");
            out.append("The winner is: ");
        for (Map.Entry<Integer, Worker> winner : winners.entrySet()) {
            //System.out.println("Worker " + winner.getKey().toString());
            out.append("Worker ").append(winner.getKey().toString()).append(" ");
        }
        int input = JOptionPane.showOptionDialog(null, out.toString(), "Results", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if(input == JOptionPane.OK_OPTION)
        {
            window.remove(view);
            menu = new Menu(window);
            menu.Run();
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
    void placeObject(int id, Field.FieldState f) {
        workers.get(id).placeObject(f);
        view.validate();
    }


    private void createAndShowGUI() {
        //Create and set up the window.
        window = new JFrame("Sokoban");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        menu = new Menu(window);
        menu.Run();
    }
}