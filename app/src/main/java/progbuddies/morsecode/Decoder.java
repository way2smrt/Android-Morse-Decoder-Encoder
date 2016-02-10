package progbuddies.morsecode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *      
 */
public class Decoder {

    private HashMap<String, Character> map = new HashMap<>();

    public Decoder(){
       map = new Mapper().getMorseToCharMap();

    }


    /**
     * @param string String we need decoded from Morse.
     * @return The decoded version of the given string.
     */
    public String decode(String string) {

        string = string.trim();

        String[] words = string.split(C.WORD_SEPERATOR);

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < words.length; i++) {

            String word = words[i];

            word = word.trim();

            String[] characters = word.split(C.CHARACTER_SEPERATOR);

            for(int j = 0; j < characters.length; j++) {
                builder.append(map.get(characters[j]));
            }
            builder.append(" ");
        }
        return builder.toString();
    }



}
