package hearts.exception;

/**
 * Checked exception class that is thrown to display a message for the user.
 * A message dialogue will appear to inform the user of the message.
 */
public class UserMessageException extends Exception {

    private String msg;
    private String title;

    /**
     * Constructor for UserMessageException.
     * @param msg message string (shown in popup window).
     * @param title title string (shown in popup window).
     */
    public UserMessageException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    /**
     * Returns the detail message string.
     * @return the detail message string..
     */
    @Override
    public String getMessage() {
        return msg;
    }

    /**
     * Returns the title message string.
     * @return the title message string.
     */
    public String getTitle() {
        return title;
    }
}

