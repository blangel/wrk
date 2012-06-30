package net.ocheyedan.wrk.cmd;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 4:00 PM
 *
 * Represents a command given to wrk from the user via the command line.
 */
public abstract class Command implements Runnable {

    public final Args args;

    protected Command(Args args) {
        this.args = args;
    }
}
