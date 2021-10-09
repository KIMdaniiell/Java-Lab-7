package format;

import java.io.Serializable;

/**
 * Enumerated data type used for describing MusicBands' music genre
 */
public enum MusicGenre implements Serializable {
    PROGRESSIVE_ROCK,
    BLUES,
    POST_PUNK;

    /**
     * Static method. Returns true if MusicGenre contains argument
     *
     * @param ingenre - argument, which is expected to be MusicGenre constant
     * @return true if contais and false if not
     */
    public static boolean contains(String ingenre) {
        for (MusicGenre genre : MusicGenre.values()) {
            if (ingenre.equals(genre.name())) {
                return true;
            }
        }
        return false;
    }
}
