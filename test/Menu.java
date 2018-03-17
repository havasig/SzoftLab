import java.util.Scanner;

public class Menu {
    //////////////////////////////////////////////////////////////////////////////////////////
    // Based on : http://chronicles.blog.ryanrampersad.com/2011/03/text-based-menu-in-java/ //
    //////////////////////////////////////////////////////////////////////////////////////////
    private Scanner input = new Scanner(System.in);

    public void displayMain() {
        System.out.println("-- Teszt --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Move\n" +
                        "  2. Collide\n" +
                        "  3. Exit\n" +
                        "Választott:"
        );

        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                displayMove();
                break;
            case "2":
                displayCollide();
                break;
            case "3":
                exit();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void exit() {
        System.out.println("Kilépés...");
        System.exit(1);
    }

    private void displayMove(){
        System.out.println("-- Move --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Move to Field\n" +
                        "  2. Move to Switch\n" +
                        "  3. Move to Destination\n" +
                        "  4. Move to Hole\n" +
                        "  5. Move to Column\n" +
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                Test.MoveToField();
                break;
            case "2":
                Test.MoveToSwitch();
                break;
            case "3":
                Test.MoveToDestination();
                break;
            case "4":
                displayMoveToHole();
                break;
            case "5":
                Test.MoveToColumn();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void displayMoveToHole() {
        System.out.println("-- Move to Hole --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Move to Closed Hole\n" +
                        "  2. Move to Opened Hole\n"+
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                Test.MoveToClosedHole();
                break;
            case "2":
                Test.MoveToOpenedHole();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void displayCollide(){
        System.out.println("-- Collide --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Collide with Worker\n" +
                        "  2. Collide with Box\n" +
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                displayCollideWithWorker();
                break;
            case "2":
                displayCollideWithBox();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void displayCollideWithWorker() {
        System.out.println("-- Collide with Worker --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Collide with Worker to Column\n" +
                        "  2. Collide with Worker to Field\n" +
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                Test.CollideWithWorkerToColumn();
                break;
            case "2":
                Test.CollideWithWorkerToField();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void displayCollideWithBox() {
        System.out.println("-- Collide with Box --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Collide with Box to Switch\n" +
                        "  2. Collide with Box to Worker\n" +
                        "  3. Collide with Box to Field\n" +
                        "  4. Collide with Box to Hole\n" +
                        "  5. Collide with Box to Column\n" +
                        "  6. Collide with Box to Destination\n" +
                        "  7. Collide with Box to Switch\n" +
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                Test.CollideWithBoxToSwitch();
                break;
            case "2":
                displayCollideWithBoxToWorker();
                break;
            case "3":
                Test.CollideWithBoxToField();
                break;
            case "4":
                displayCollideWithBoxToHole();
                break;
            case "5":
                Test.CollideWithBoxToColumn();
                break;
            case "6":
                Test.CollideWithBoxToDestination();
                break;
            case "7":
                Test.CollideWithBoxOffFromSwitch();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void displayCollideWithBoxToHole() {
        System.out.println("-- Collide with Box to Hole --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Collide with Box to Closed Hole\n" +
                        "  2. Collide with Box to Opened Hole\n" +
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                Test.CollideWithBoxToClosedHole();
                break;
            case "2":
                Test.CollideWithBoxToOpenedHole();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }

    private void displayCollideWithBoxToWorker() {
        System.out.println("-- Collide with Box to Worker --");
        System.out.println(
                "Válasz egy lehetőséget: \n" +
                        "  1. Collide with Box to Worker to Column\n" +
                        "  2. Collide with Box to Worker to Worker to Column\n" +
                        " Választott:"
        );
        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":
                Test.CollideWithBoxToWorkerToColumn();
                break;
            case "2":
                Test.CollideWithBoxToWorkerToWorkerToColumn();
                break;
            default:
                System.out.println("Nem létező alpont.");
                break;
        }
    }
}
