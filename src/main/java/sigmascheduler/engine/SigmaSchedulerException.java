package sigmascheduler.engine;

/**
 * Exception for all SigmaScheduler related problems
 * 
 * @author Dominik Scholz
 * @version 0.2
 */
public class SigmaSchedulerException extends Exception {
    
    private final String message;
    private final String argument;

    public SigmaSchedulerException(String message) {
        this("",message);
    }
    
    public SigmaSchedulerException(String argument, String message) {
        this.argument = argument;
        this.message = message;
    }
    
    public SigmaSchedulerException(Exception ex) {
        this("",ex.getMessage());
    }
    
    public SigmaSchedulerException(String argument, Exception ex) {
        this(argument,ex.getMessage());
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public String getArgument() {
        return argument;
    }
}
