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
     * @param msg message string (shown in popup window).
     * @param title title string (shown in popup window).
     */
    public IllegalMoveException(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    /**
     * Returns the detail message string.
     * @return the detail message string.
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

