public class GameController {

    public void startGame() {
        System.out.println("====================================   WELCOME TO THE GAME OF HEARTS   ====================================");

        Round round = new Round(1);
        while (round.getHighestScore() < 100) {
            round.startRound();
        }
        System.out.printf("Game has ended. %s wins with the lowest score! ", round.getWinner().getName());
        System.out.println("====================================   THANK YOU FOR PLAYING HEARTS   ====================================");
    }
}
