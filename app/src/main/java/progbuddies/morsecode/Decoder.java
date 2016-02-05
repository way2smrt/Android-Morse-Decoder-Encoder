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
        map.put(".-",'a');
        map.put("-...",'b');
        map.put("-.-.",'c');
        map.put("-..",'d');
        map.put(".",'e');
        map.put("..-.",'f');
        map.put("--.",'g');
        map.put("....",'h');
        map.put("..",'i');
        map.put(".---",'j');
        map.put("-.-",'k');
        map.put(".-..",'l');
        map.put("--",'m');
        map.put("-.",'n');
        map.put("---",'o');
        map.put(".--.",'p');
        map.put("-----",'0');
        map.put("--.-",'q');
        map.put(".----",'1');
        map.put(".-.",'r');
        map.put("..---",'2');
        map.put("...",'s');
        map.put("...--",'3');
        map.put("-",'t');
        map.put("....-",'4');
        map.put("..-",'u');
        map.put(".....",'5');
        map.put("...-",'v');
        map.put("-....",'6');
        map.put(".--",'w');
        map.put("--...",'7');
        map.put("-..-",'x');
        map.put("---..",'8');
        map.put("-.--",'y');
        map.put("----.",'9');
        map.put("--..",'z');

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
