import java.util.List;

public class GameController {

    Game game;
    Player humanPlayer;

    public Player[] startGame() {
        System.out.println("====================================   WELCOME TO THE GAME OF HEARTS   ====================================");
        game = new Game(1);
        Player[] players = game.getListOfPlayers();
        humanPlayer = players[0];
        startRound();
        return players;
    }

    public void startRound() {
        if (game.getHighestScore() < 100) {
            game.initRound();
            game.unsetPlayedCards();
            return;
        }
        System.out.printf("Game has ended. %s wins with the lowest score! ", game.getWinner().getName());
        System.out.println("====================================   THANK YOU FOR PLAYING HEARTS   ====================================");
    }

    public void executeMove(List<Card> cards) throws IllegalMoveException, UserMessageException{
        if (!game.hasPassedCards() && game.getRoundNum() % 4 != 0) {
            passCards(cards);
            executeComputerMoves();
        } else {
            if (cards.size() > 1) {
                throw new IllegalMoveException("Pick only 1 card.", "Invalid Selection");
            } else if (cards.size() == 0) {
                throw new IllegalMoveException("You must play a card.", "Invalid Selection");
            }
            game.makePlayerMove(cards.get(0));
            executeComputerMoves();
        }
    }

    public int getHighestScore() {
        return game.getHighestScore();
    }

    public void executeComputerMoves() throws UserMessageException{
        if (!game.hasPassedCards() ) {
            return;
        }
        if(game.getCurrentSet().getNumOfCardsInSet() == 0){
            game.unsetPlayedCards();
        }
        game.makeComputerMoves();
    }

    public void passCards(List<Card> cards) throws IllegalMoveException, UserMessageException{
        if(cards.size() != 3){
            throw new IllegalMoveException("You must pick 3 cards!", "Invalid Selection");
        }
        game.passCards(cards);
    }
}
