package Main;

import Main.*;

import java.rmi.UnexpectedException;

/**
 * A loggolást segítő osztály. Futás közben kiírja terminálban az állapotváltozásokat
 */
public class Logger {
    //A behúzás mértéke, formázás miatt.
    private static int tabs = 0;
    //TODO itt kitalálni hogy ez hogy működjön

    private static String writeTabs(int num){
        String temp = "";
        for(int i = 0; i<num; i++){
            temp+= "\t";
        }
        return temp;
    }

    public static void funcStart(String func_name, String label, String params)
    {
        System.out.println(writeTabs(tabs++) + label + " --> " + func_name + "(" + params + ")");
    }
    public static void funcEnd(String func_name, String label, String ret){
        if (tabs > 0)
            tabs--;
        System.out.println(writeTabs(tabs) + label + " <-- " + func_name + ": " + ret);
    }

}
