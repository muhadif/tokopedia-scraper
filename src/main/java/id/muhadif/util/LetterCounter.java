package id.muhadif.util;

/**
 * @Author muhadif
 * @create 08/06/21 16.55
 */
public class LetterCounter {
    public static long countLetters(String value) {
        if (value == null) {
            return 0;
        }
        return value.chars().filter(Character::isLetter).count();
    }
}