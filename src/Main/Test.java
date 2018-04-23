package Main;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Teszteli a jatek porotipusanak mukodeset.
 */
class Test {
    private boolean exit;
    private boolean running;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String saveFile;
    private Factory factory;
    private boolean autoShow = true;
    private boolean save = false;

    private Test() {
        exit = false;
        running = false;
        factory = Game.getInstance().getMap();
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.Run();
    }

    private void Run() {
        while (!exit) {
            List<String> input = ReadLine();

            if (input != null) {
                if (running) {
                    GameSimulate(input);
                } else {
                    Initial(input);
                }
            } else {
                Exit();
            }
        }
    }

    private List<String> ReadLine() {
        String line;
        List<String> input;
        try {
            line = br.readLine();
            if (save) Save(line);
            if (line != null) {
                input = Arrays.asList(line.split(" "));
                return input;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void TestGenerateMap(List<String> input) {
        try {
            if (input.size() != 3) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (Integer.parseInt(input.get(1))<=0||Integer.parseInt(input.get(2))<=0)
                throw new NumberFormatException();
            if (Integer.parseInt(input.get(1))<=2||Integer.parseInt(input.get(2))<=2)
                throw new Exception();
            factory.GenerateMap(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nem sikerült az adott konfigurációt elindítani.");
        } catch (ArrayIndexOutOfBoundsException e){
            Error("Nincs ilyen parancs");
        } catch (Exception e){
            Error(" A megadott méret túl kicsi");
        }
    }

    private void TestCreateColumn(List<String> input) {
        try {
            if (input.size() != 3) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(input.get(1));
            int y = Integer.parseInt(input.get(2));

            if (x > factory.getWidth() || x < 1 || y > factory.getHeight() || y < 1) {
                throw new Exception();
            }
            factory.createColumn(x, y);
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e) {
            Error("Nem a palya resze");
        }
    }

    private void TestCreateDestination(List<String> input) {
        try {
            if (input.size() != 3) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(input.get(1));
            int y = Integer.parseInt(input.get(2));

            if (x > factory.getWidth() || x < 1 || y > factory.getHeight() || y < 1) {
                throw new Exception();
            }
            factory.createDestination(x, y);
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e) {
            Error("Nem a palya resze");
        }
    }

    private void TestCreateHole(List<String> input) {
        try {
            if (input.size() != 4) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(input.get(1));
            int y = Integer.parseInt(input.get(2));

            if (x > factory.getWidth() || x < 1 || y > factory.getHeight() || y < 1) {
                throw new Exception();
            }

            HoleState hs = HoleState.valueOf(input.get(3));

            factory.createHole(x, y, hs);
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e){
            Error("Nem lehet letrehozni");
        }
    }

    private void TestLoadLevel(List<String> input){
        try {
            if (input.size() != 2) {
                throw new NumberFormatException();
            }
            boolean b = factory.ReadMap(input.get(1));
            if (!b)
                throw new Exception();
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch(Exception e){

        }
    }

    private void TestCreateSwitch(List<String> input){
        try {
            if (input.size() != 5) {
                throw new NumberFormatException();
            }
            int switchX = Integer.parseInt(input.get(1));
            int switchY = Integer.parseInt(input.get(2));
            int holeX = Integer.parseInt(input.get(3));
            int holeY = Integer.parseInt(input.get(4));

            if (switchX > factory.getWidth() || switchX < 1 || switchY > factory.getHeight() || switchY < 1 ||
                    holeX > factory.getWidth() || holeX < 1 || holeY > factory.getHeight() || holeY < 1) {
                throw new Exception();
            }

            /*nem ellenőrzi h van e hole*/
            factory.createSwitch(switchX, switchY, holeX, holeY);
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e){
            Error("Nem lehet letrehozni");
        }
    }

    //workert lehet Box-ra létrehozni és fordítva
    private void TestAddWorker(List<String> input){
        try {
            if (input.size() != 4) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(input.get(1));
            int y = Integer.parseInt(input.get(2));
            if (x > factory.getWidth() || x < 1 || y > factory.getHeight() || y < 1){
                throw new Exception();
            }

            factory.addWorker(x, y, Integer.parseInt(input.get(3)));
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e){
            Error("A megadott helyre nem helyezheto");
        }
    }

    //workert lehet Box-ra létrehozni és fordítva
    private void TestAddBox(List<String> input){
        try {
            if (input.size() != 3) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(input.get(1));
            int y = Integer.parseInt(input.get(2));
            if (x > factory.getWidth() || x < 1 || y > factory.getHeight() || y < 1){
                throw new Exception();
            }

            factory.addBox(x, y);
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e){
            Error("A megadott helyre nem helyezheto");
        }
    }

    private void Initial(List<String> input) {
        if (!input.isEmpty()) {
            switch (input.get(0)) {
                case "start":
                    running = true;
                    isThisTheEnd();
                    break;
                case "exit":
                    Exit();
                    break;
                case "saveFile":
                    saveFile(input.get(1));
                    //System.out.println("SaveFile added");
                    break;
                case "load":
                    Load(input.get(1));
                    break;
                case "save":
                    save = true;
                    System.out.println("A mentés sikerrel lezaljlott.");
                    break;
                case "loadLevel":
                    this.TestLoadLevel(input);
                    break;
                case "generateMap":
                    this.TestGenerateMap(input);
                    break;
                case "createColumn":
                    this.TestCreateColumn(input);
                    break;
                case "createDestination":
                    this.TestCreateDestination(input);
                    break;
                case "createHole":
                    this.TestCreateHole(input);
                    break;
                case "createSwitch":
                    this.TestCreateSwitch(input);
                    break;
                case "addWorker":
                    this.TestAddWorker(input);
                    break;
                case "addBox":
                    this.TestAddBox(input);
                    break;
                case "autoShow":
                    autoShow(input.get(1));
                    break;
                case "Show":
                    Draw(factory.Draw());
                    break;
                case "":
                    Error("Ures sor");
                    break;
                default:
                    Error("Nincs ilyen parancs");
                    break;
            }
        }
    }

    private void saveFile(String path) {
        saveFile = path;
    }

    /**
     * Betolti a jatek egy regebbi allapotat.
     *
     * @param string: ezen nevu file-bol tolti be a jatek egy regebbi allapotat.
     */
    private void Load(String string) {
        String line;
        List<String> input;

        try {
            BufferedReader brIn = new BufferedReader(new FileReader(string));
            while ((line = brIn.readLine()) != null) {
                System.out.println(string + ": " + line);
                input = Arrays.asList(line.split(" "));
                if (running) {
                    GameSimulate(input);
                } else {
                    Initial(input);
                }
            }
        } catch (IOException e) {
            Error("A tesztesetet nem sikerült futtatni.");
        }
    }

    private void TestMoveWorker(List<String> input){
        try {
            if (input.size() != 3) {
                throw new NumberFormatException();
            }
            int _id = Integer.parseInt(input.get(1));
            Direction dir = Direction.valueOf(input.get(2));

            Game.getInstance().moveWorker(_id, dir);
            Draw(factory.Draw());
            isThisTheEnd();
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e){
            Error("A megadott irany helytelen.");
        }
    }

    // ezt nem így kell megcsinálni :( nem stringet kell átadni (szerintem) hanem már itt kell konvertálni az adatot (Object-et kell átadni)
    private void TestPlaceObject(List<String> input){
        try {
            if(input.size() != 3 || input.get(3)!="Honey" || input.get(3) != "Oil")
                throw new NumberFormatException();
            Game.getInstance().placeObject(Integer.parseInt(input.get(1)), input.get(2), input.get(3));
            Draw(factory.Draw());

            if(input.get(2)!="Right" || input.get(2) != "Left"||input.get(2) != "Up"||input.get(2) != "Down")
                throw new Exception();
            Game.getInstance().placeObject(Integer.parseInt(input.get(1)), input.get(2), input.get(3));
            Draw(factory.Draw());
        } catch (NumberFormatException e){
            Error("A megadott folyadék nem létezik.");
        } catch (Exception e){
            Error("A megadott irány nem létezik.");
            }
        }

    /**
     * Szimulalja a jatek mukodeset a bemeneti nyelvnek megfelelo formaban kapott utasiatsok alapjan.
     *
     * @param input: a lista elemei a bemeneti utasitasok.
     */
    private void GameSimulate(List<String> input) {
        if (!input.isEmpty()) {
            switch (input.get(0)) {
                case "abort":
                    running = false;
                    break;
                case "moveWorker":
                    this.TestMoveWorker(input);
                    break;
                case "placeObject":
                    this.TestPlaceObject(input);
                    break;
                case "":
                    Error("Ures sor");
                    break;
                default:
                    Error("Nincs ilyen parancs");
                    break;
            }
        }
    }

    /**
     * Ellenorzi, hogy le kell-e allnia a jateknak.
     */
    private void isThisTheEnd() {
        if (Game.getInstance().getMap().ThisIsTheEnd()) {
            Game.getInstance().EndGame();
            running = false;
        }
    }

    private void autoShow(String s) {
        if (s.equals("on")) this.autoShow = true;
        if (s.equals("off")) this.autoShow = false;

    }

    private void Draw(String output) {
        System.out.println(output);
        if (save) Save(output);
    }

    /**
     * Kimenti a jatek adott pillanatban levo allasat
     *
     * @param output: ilyen nevu file-ba menti ki az aktualis allast.
     */
    private void Save(String output) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(saveFile, true)))) {
            out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Error(String err) {
        System.out.println(err);
    }

    /**
     * Kilep a jatekbol
     */
    private void Exit() {
        exit = true;
        System.out.println("Exiting...");
    }

}
