package progbuddies.morsecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 */
public class Mapper {


    public HashMap<Character, String> getCharToMorseMap() {
        return charToMorseMap;
    }

    public HashMap<String, Character> getMorseToCharMap() {
        return morseToCharMap;
    }

    private HashMap<Character, String> charToMorseMap = new HashMap<>();

    private HashMap<String, Character> morseToCharMap = new HashMap<>();


    public Mapper() {
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
        for(Map.Entry<Character,String> entry : charToMorseMap.entrySet()) {
            morseToCharMap.put(entry.getValue(),entry.getKey());
        }
    }


}
