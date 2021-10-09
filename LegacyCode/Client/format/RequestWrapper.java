package format;

import java.io.Serializable;

public class RequestWrapper implements Serializable {
    /*
     * A Class-Wrapper over incoming requests.
     * Contains string representation of command name,
     * arguments and an instance of MusicBand class.
     */

    private String command;
    private String arg;
    private MusicBand musicBand;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }

    public void setMusicBand(MusicBand musicBand) {
        this.musicBand = musicBand;
    }
}
