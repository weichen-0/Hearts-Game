import java.util.List;

public class GameController {

    Game game;
    Player humanPlayer;

    public Player[] startGame() throws UserMessageException{
        System.out.println("====================================   WELCOME TO THE GAME OF HEARTS   ====================================");
        game = new Game(1);
        Player[] players = game.getListOfPlayers();
        humanPlayer = players[0];
        startRound();
        return players;
    }

    public void startRound() throws UserMessageException{
        if (game.getHighestScore() < 100) {
            game.initRound();
        }else{
            System.out.printf("Game has ended. %s wins with the lowest score! ", game.getWinner().getName());
            System.out.println("====================================   THANK YOU FOR PLAYING HEARTS   ====================================");
            throw new UserMessageException(game.getWinner().getName() + " won the game! Thanks for playing.", "Game Ended");
        }
    }

    public String executeMove(List<Card> cards) throws IllegalMoveException, UserMessageException{
        if(game.getCurrentSet().getNumOfCardsInSet() == 0){
            game.unsetPlayedCards();
        }
        if(!game.hasPassedCards()){
            passCards(cards);
            executeComputerMoves();
        }else{
            if(cards.size() > 1){
                throw new IllegalMoveException("Pick only 1 card.", "Invalid Selection");
            }else if(cards.size() == 0){
                throw new IllegalMoveException("You must play a card.", "Invalid Selection");
            }
            game.makePlayerMove(cards.get(0));
            executeComputerMoves();
        }
        return null;
    }

    public void executeComputerMoves() throws UserMessageException{
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
