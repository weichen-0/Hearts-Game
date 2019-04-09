package hearts.exception;

/**
 * Exception Class that
 */
public class UserMessageException extends Exception {
    /**
     * Information contained in user info message
     * Message = Message body
     * Title = Title of dialogue box
     */
    private String msg;
    private String title;

    /**
     * Constructor for hearts.exception.UserMessageException
     * @param msg message string for hearts.exception (shown in user popup window)
     * @param title title string for hearts.exception (shown in user popup window)
     */
    public UserMessageException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    /**
     * Returns the detail message string.
     * @return the detail message string of this hearts.exception.UserMessageException.
     */
    @Override
    public String getMessage() {
        return msg;
    }


    /**
     * Returns the title message string.
     * @return the title message string of this hearts.exception.UserMessageException.
     */
    public String getTitle() {
        return title;
    }
}
