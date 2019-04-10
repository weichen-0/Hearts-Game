package hearts.exception;

/**
 * Checked exception class that is thrown when player makes an illegal move.
 * A message dialogue will appear to inform the user of their illegal move.
 */
public class IllegalMoveException extends Exception{

    private String msg;
    private String title;

    /**
     * Constructor for IllegalMoveException.
     * @param msg message string for exception (shown in popup window).
     * @param title title string for exception (shown in popup window).
     */
    public IllegalMoveException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    /**
     * Returns the detail message string.
     * @return the detail message string of this IllegalMoveException.
     */
    @Override
    public String getMessage() { return msg; }

    /**
     * Returns the title message string.
     * @return the title message string of this IllegalMoveException.
     */
    public String getTitle() { return title; }
}
