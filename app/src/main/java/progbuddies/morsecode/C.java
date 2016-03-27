package progbuddies.morsecode;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 */
public class C {

    // ONE SPACE
    public static final char CHARACTER_SEPERATOR = ' ';
    //THREE SPACES, NOT TAB.
    public static final String WORD_SEPERATOR = "   ";

    public static final char WORD_SEPERATOR_PLACEHOLDER = '$';

    public static final char DOT = '.';

    public static final char DASH = '-';


    public static int DOT_TIME_INTERVAL = 100;
    public static int MIN_DOT_TIME_INTERVAL = 40;
    public static int DASH_TIME_INTERVAL = DOT_TIME_INTERVAL*3;
    public static int CHARACTER_SEPERATOR_TIME_INTERVAL = DOT_TIME_INTERVAL*3;
    public static int WORD_SEPERATOR_TIME_INTERVAL = DOT_TIME_INTERVAL*7;

    public static void updateTimeInterval(int dotTimeInterval){
        DOT_TIME_INTERVAL = dotTimeInterval;
        DASH_TIME_INTERVAL = DOT_TIME_INTERVAL*3;
        CHARACTER_SEPERATOR_TIME_INTERVAL = DOT_TIME_INTERVAL*3;
        WORD_SEPERATOR_TIME_INTERVAL = DOT_TIME_INTERVAL*7;
    }

}
