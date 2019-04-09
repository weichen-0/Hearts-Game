/**
 * Thrown when player makes an illegal move.
 * A message dialogue will appear to inform the user of their illegal move.
 */
@SuppressWarnings("serial")
public class IllegalMoveException extends Exception{
    /**
     * Information contained in error message
     * Message = Message body
     * Title = Title of dialogue box
     */
    private String msg;
    private String title;
    /**
     * Constructor for IllegalMoveException
     * @param msg
     * @param title
     */
    public IllegalMoveException(String msg, String title) {
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
