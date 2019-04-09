package hearts.controller;

import hearts.model.*;
import hearts.exception.*;

import java.util.List;

/**
 * Class that directs the entire flow of the Hearts game
 * Default constructor is used
 */
public class GameController {

    Game game;
    HumanPlayer humanPlayer;

    /**
     * Starts the game and
     * @return
     */
    public Player[] startGame() {
        System.out.println("====================================   WELCOME TO THE GAME OF HEARTS   ====================================");
        game = new Game(1);
        startNextRound();
        Player[] players = game.getListOfPlayers();
        humanPlayer = (HumanPlayer) players[0];
        return players;
    }

    /**
     *
     * @return
     */
    private boolean startNextRound() {
        if (game.getHighestScore() < 100) {
            game.initRound();
            game.unsetPlayedCards();
            try{
                executeComputerMoves(); // maximum 3 of 4 players (bots) will play cards
            }catch(UserMessageException e){ // will not occur since round has not ended
                System.out.println(e.getMessage());
            }
            return true;
        }
        // hearts.model.Game ends
        System.out.printf("hearts.model.Game has ended. %s wins with the lowest score! ", game.getWinner().getName());
        System.out.println("====================================   THANK YOU FOR PLAYING HEARTS   ====================================");
        return false;
    }

    public boolean hasRoundEnded() {
        return humanPlayer.getHand().getCardList().isEmpty();
    }

    public void executeMove(List<Card> cards) throws IllegalMoveException, UserMessageException {
        if (!game.hasPassedCards()) {
            passCards(cards);
            executeComputerMoves();
        } else {
            if (cards.size() > 1) {
                humanPlayer.deselectCardsInHand();
                throw new IllegalMoveException("Pick only 1 card.", "Invalid Selection");
            } else if (cards.size() == 0) {
                throw new IllegalMoveException("You must play a card.", "Invalid Selection");
            }
            game.makePlayerMove(cards.get(0));
            executeComputerMoves();
        }
    }

    public void executeComputerMoves() throws UserMessageException {
        if (!game.hasPassedCards()) {
            return;
        }
        if(game.getCurrentSet().getNumOfCardsInSet() == 0){
            game.unsetPlayedCards();
        }
        game.makeComputerMoves();
    }

    /**
     *
     * @param cards
     * @throws IllegalMoveException
     * @throws UserMessageException
     */
    public void passCards(List<Card> cards) throws IllegalMoveException, UserMessageException {
        if(cards.size() != 3){
            humanPlayer.deselectCardsInHand();
            throw new IllegalMoveException("You must pick 3 cards!", "Invalid Selection");
        }
        game.passCards(cards);
    }
}
