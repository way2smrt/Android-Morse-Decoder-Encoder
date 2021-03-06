package progbuddies.morsecode;
import android.os.Vibrator;
import android.view.View;

import java.util.HashMap;

/**
 *@author Bilal Tahir <bilal@bilaltahir.com>
 */
public class Encoder {

    private HashMap<Character, String> charToMorseMap = new HashMap<>();

    public Encoder() {
       charToMorseMap = new Mapper().getCharToMorseMap();
    }


    /**
     * @param string String which we need encoded to morse.
     * @return The morse representation of given string.
     */
    public String encode(String string) {

        string = string.toLowerCase();

        String[] words = string.split(" ");

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            char[] chars = word.toCharArray();

            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                String morse = charToMorseMap.get(c);
                builder.append(morse);
                //This is so there is not 4 spaces between words we don't want to append to end of last char in word
                if(j!= chars.length-1){
                    builder.append(C.CHARACTER_SEPERATOR);
                }
            }
            builder.append(C.WORD_SEPERATOR);
        }

        String withOutInvalidCharacters = builder.toString().replace("null", "");

        return withOutInvalidCharacters;
    }
}
