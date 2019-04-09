package hearts.exception;

/**
 * Checked exception class that is thrown to display a popup message for the user
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
     * Constructor for UserMessageException
     * @param msg message string for exception (shown in popup window)
     * @param title title string for exception (shown in popup window)
     */
    public UserMessageException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    /**
     * Returns the detail message string.
     * @return the detail message string of this UserMessageException.
     */
    @Override
    public String getMessage() { return msg; }


    /**
     * Returns the title message string.
     * @return the title message string of this UserMessageException.
     */
    public String getTitle() { return title; }
}
