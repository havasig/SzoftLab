package Main;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
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
        System.out.println("Boldogot Havi! :*");
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
                throw new NumberFormatException();
            }
            factory.GenerateMap(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
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

    //TODO: Havi Hibakezelés
    private void TestCreateHole(List<String> input) {
        try {
            if (input.size() != 4) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(input.get(1));
            int y = Integer.parseInt(input.get(2));
            HoleState hs = HoleState.valueOf(input.get(3));


            if (x > factory.getWidth() || x < 1 || y > factory.getHeight() || y < 1) {
                throw new Exception();
            }
            factory.createHole(x, y, hs);
            if (autoShow) Draw(factory.Draw());
        } catch (NumberFormatException e) {
            Error("Nincs ilyen parancs");
        } catch (Exception e) {
            Error("Nem a palya resze vagy a HoleState szar");
        }
    }

    private void Initial(List<String> input)  {
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
                    System.out.println("SaveFile added");
                    break;
                case "load":
                    Load(input.get(1));
                    break;
                case "save":
                    save = true;
                    break;
                case "loadLevel": //TODO: Havi
                    try {
                        factory.ReadMap(input.get(1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (autoShow) Draw(factory.Draw());
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
                case "createSwitch": //TODO: Havi
                    factory.createSwitch(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)),
                            Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)));
                    if (autoShow) Draw(factory.Draw());
                    break;
                case "addWorker": //TODO: Havi
                    factory.addWorker(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)),
                            Integer.parseInt(input.get(3)));
                    if (autoShow) Draw(factory.Draw());
                    break;
                case "addBox":
                    factory.addBox(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    if (autoShow) Draw(factory.Draw());
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
            e.printStackTrace();
        }
    }


    private void GameSimulate(List<String> input) {
        if (!input.isEmpty()) {
            switch (input.get(0)) {
                case "abort":
                    running = false;
                    break;
                case "moveWorker": //TODO: Havi
                    Game.getInstance().moveWorker(Integer.parseInt(input.get(1)), input.get(2));
                    Draw(factory.Draw());
                    isThisTheEnd();
                    break;
                case "placeObject": //TODO: Havi
                    Game.getInstance().placeObject(Integer.parseInt(input.get(1)), input.get(2), input.get(3));
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

    private void isThisTheEnd() {
        if (Game.getInstance().getMap().ThisIsTheEnd()) {
            System.out.println("Game over.");
            System.out.println("Points:");
            HashMap<Integer, Worker> winners = new HashMap<>();
            int mostPoints = 0;
            for (Map.Entry<Integer, Worker> worker : Game.getInstance().getWorkers().entrySet()) {
                int points = worker.getValue().getPoints();
                Worker work = worker.getValue();
                int id = worker.getKey();

                System.out.println("Worker " + Integer.toString(id) + ": " + Integer.toString(points));

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
                System.out.println("Something went wrong, there are no winners!");
                return;
            }

            if (winners.size() > 1)
                System.out.println("The winners are: ");
            else
                System.out.println("The winner is:");

            for (Map.Entry<Integer, Worker> winner : winners.entrySet()) {
                System.out.println("Worker " + winner.getKey().toString());
            }

            running = false;
        }
    }

    private void autoShow(String s) {
        if (s.equals("on")) this.autoShow = true;
        if (s.equals("off")) this.autoShow = false;

    }

    void Draw(String output) {
        System.out.println(output);
        if (save) Save(output);
    }

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

    private void Exit() {
        exit = true;
        System.out.println("Exiting...");
    }

}
