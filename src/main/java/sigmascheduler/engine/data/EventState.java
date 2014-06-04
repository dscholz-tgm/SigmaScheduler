package sigmascheduler.engine.data;

/**
 * The state of an event,
 * when its over its done,
 * you probably missed it (there is no 2nd chance)
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.1
 */
public enum EventState {
    UNPUBLISHED,
    VOTEABLE,
    CLOSED,
    OVER;
}
