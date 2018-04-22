package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Test {
    private boolean exit;
    private boolean running;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String saveFile;
    private Factory factory;
    private boolean autoShow = true;

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
            if (line != null) {
                input = Arrays.asList(line.split(" "));
                return input;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void Initial(List<String> input) {
        if (!input.isEmpty()) {
            switch (input.get(0)) {
                case "start":
                    running = true;
                    break;
                case "exit":
                    Exit();
                    break;
                case "saveFile":
                    saveFile = input.get(1);
                    System.out.println("Save...");
                    break;
                case "load":
                    //TODO
                    break;
                case "save":
                    //TODO
                    break;
                case "loadLevel":
                    factory.Load(input.get(1));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "generateMap":
                    factory.GenerateMap(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "createColumn":
                    factory.createColumn(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "createDestination":
                    factory.createDestination(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "createHole":
                    factory.createHole(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)),
                            input.get(3));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "createSwitch":
                    factory.createSwitch(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)),
                            Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "addWorker":
                    factory.addWorker(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)),
                            Integer.parseInt(input.get(3)));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "addBox":
                    factory.addBox(Integer.parseInt(input.get(1)), Integer.parseInt(input.get(2)));
                    if (autoShow) System.out.println(factory.Draw());
                    break;
                case "autoShow":
                    autoShow(input.get(1));
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


    private void GameSimulate(List<String> input) {
        if (!input.isEmpty()) {
            switch (input.get(0)) {
                case "abort":
                    running = false;
                    break;
                case "moveWorker":
                    Game.getInstance().moveWorker(Integer.parseInt(input.get(1)), input.get(2));
                    System.out.println(factory.Draw());
                    break;
                case "placeObject":
                    //TODO
                    break;
                case "save":
                    //TODO
                    break;
                case "IsThisTheEnd":
                    if(Game.getInstance().getMap().ThisIsTheEnd())
                        System.out.println("This is the end.");
                    else
                        System.out.println("This is not the end");
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

    private void autoShow(String s) {
        if (s.equals("on")) this.autoShow = true;
        if (s.equals("off")) this.autoShow = false;
    }


    private void Error(String err) {
        System.out.println(err);
    }

    private void Exit() {
        exit = true;
        System.out.println("Exiting...");
    }

}
