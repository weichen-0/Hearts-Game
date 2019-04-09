@SuppressWarnings("serial")
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
     * @param msg
     * @param title
     */
    public UserMessageException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    @Override
    public String getMessage() {
        return msg;
    }
    public String getTitle() {
        return title;
    }
}
