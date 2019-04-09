package hearts.exception;

/**
 * Thrown when player makes an illegal move.
 * A message dialogue will appear to inform the user of their illegal move.
 */
public class IllegalMoveException extends Exception{
    /**
     * Information contained in error message
     * Message = Message body
     * Title = Title of dialogue box
     */
    private String msg;
    private String title;

    /**
     * Constructor for hearts.exception.IllegalMoveException
     * @param msg message string for hearts.exception (shown in user popup window)
     * @param title title string for hearts.exception (shown in user popup window)
     */
    public IllegalMoveException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    /**
     * Returns the detail message string.
     * @return the detail message string of this hearts.exception.IllegalMoveException.
     */
    @Override
    public String getMessage() { return msg; }

    /**
     * Returns the title message string.
     * @return the title message string of this hearts.exception.IllegalMoveException.
     */
    public String getTitle() { return title; }
}
