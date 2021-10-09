package format;

import java.io.Serializable;
import java.util.Stack;

public class Response implements Serializable {
    /*
     * A Class-Wrapper over outgoing responses.
     * Contains enum Status information of
     * commands execution and a collection, which is
     * supposed to be additionally processed on
     * the clients side.
     *
     */

    CommandAccomplishment status;
    Stack<MusicBand> mystack;

    public Response(CommandAccomplishment status, Stack<MusicBand> stack) {
        this.status = status;
        mystack = stack;

    }

    public Stack<MusicBand> getMystack() {
        return mystack;
    }

    public void setMystack(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    public CommandAccomplishment getStatus() {
        return status;
    }

    public void setStatus(CommandAccomplishment status) {
        this.status = status;
    }
}


