import Main.*;

/**
 *
 */
public class Logger {
    //A behúzás mértéke, formázás miatt.
    private int tabs;
    //TODO itt kitalálni hogy ez hogy működjön

    private String writeTabs(int num){
        String temp = "";
        for(int i = 0; i<num; i++){
            temp+= "\t";
        }
        return temp;
    }

}
