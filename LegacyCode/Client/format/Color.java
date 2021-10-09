package format;

import java.io.Serializable;

/**
 * Enumerated data type used for describing MusicBand.FrontMans' eyes color
 */
public enum Color implements Serializable {
    RED,
    BLACK,
    BLUE,
    YELLOW,
    ORANGE;

    /**
     * Static method. Returns true if Color contains argument
     *
     * @param ingenre - argument, which is expected to be Color constant
     * @return true if contais and false if not
     */
    public static boolean contains(String ingenre) {
        for (Color color : Color.values()) {
            if (ingenre.equals(color.name())) {
                return true;
            }
        }
        return false;
    }
}