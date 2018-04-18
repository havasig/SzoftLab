package Main;

import java.util.Scanner;

public class Menu {
    //////////////////////////////////////////////////////////////////////////////////////////
    // Based on : http://chronicles.blog.ryanrampersad.com/2011/03/text-based-menu-in-java/ //
    //////////////////////////////////////////////////////////////////////////////////////////
    private Scanner input = new Scanner(System.in);

    public void displayMain() {
        System.out.println("-- Teszt --");


        String selection = input.next();
        input.nextLine();

        switch (selection) {
            case "1":

                break;
            case "2":

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
}
