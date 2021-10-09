package handling.command;

import format.MusicBand;
import format.Response;

public interface Command {
    Response execute(String args, MusicBand band);
}
