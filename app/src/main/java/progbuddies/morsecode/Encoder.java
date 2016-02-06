package progbuddies.morsecode;
import java.util.HashMap;

/**
 *@author Bilal Tahir <bilal@bilaltahir.com>
 */
public class Encoder {

    private HashMap<Character, String> charToMorseMap = new HashMap<Character, String>();

    public Encoder() {
        charToMorseMap.put('a', ".-");
        charToMorseMap.put('b', "-...");
        charToMorseMap.put('c', "-.-.");
        charToMorseMap.put('d', "-..");
        charToMorseMap.put('e', ".");
        charToMorseMap.put('f', "..-.");
        charToMorseMap.put('g', "--.");
        charToMorseMap.put('h', "....");
        charToMorseMap.put('i', "..");
        charToMorseMap.put('j', ".---");
        charToMorseMap.put('k', "-.-");
        charToMorseMap.put('l', ".-..");
        charToMorseMap.put('m', "--");
        charToMorseMap.put('n', "-.");
        charToMorseMap.put('o', "---");
        charToMorseMap.put('p', ".--.");
        charToMorseMap.put('q', "--.-");
        charToMorseMap.put('r', ".-.");
        charToMorseMap.put('s', "...");
        charToMorseMap.put('t', "-");
        charToMorseMap.put('u', "..-");
        charToMorseMap.put('v', "...-");
        charToMorseMap.put('w', ".--");
        charToMorseMap.put('x', "-..-");
        charToMorseMap.put('y', "-.--");
        charToMorseMap.put('z', "--..");
        charToMorseMap.put('1', ".----");
        charToMorseMap.put('2', "..---");
        charToMorseMap.put('3', "...--");
        charToMorseMap.put('4', "....-");
        charToMorseMap.put('5', ".....");
        charToMorseMap.put('6', "-....");
        charToMorseMap.put('7', "--...");
        charToMorseMap.put('8', "---..");
        charToMorseMap.put('9', "----.");
        charToMorseMap.put('0', "-----");
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
                builder.append(C.CHARACTER_SEPERATOR);
            }
            builder.append(C.WORD_SEPERATOR);
        }

        return builder.toString();
    }

}
