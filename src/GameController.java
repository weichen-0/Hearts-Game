public class GameController {

    public void startGame() {
        System.out.println("====================================   WELCOME TO THE GAME OF HEARTS   ====================================");

        Game game = new Game(1);
        while (game.getHighestScore() < 100) {
            game.initRound();
            game.passedCards();
            //first round logic: decide first player
            int startPlayerIndex = GameRegulator.getStartPlayerIndex(game.getListOfPlayers());
            for (int setCount = 1; setCount <= 13; setCount++) {
                startPlayerIndex = game.startSet(setCount, startPlayerIndex);
            }
            game.tallyPointsForRound();
        }
        System.out.printf("Game has ended. %s wins with the lowest score! ", game.getWinner().getName());
        System.out.println("====================================   THANK YOU FOR PLAYING HEARTS   ====================================");
    }
}
