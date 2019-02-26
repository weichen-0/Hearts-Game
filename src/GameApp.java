

public class GameApp {
    public static void main(String[] args) {
        System.out.println("==============================   WELCOME TO THE GAME OF HEARTS   ==============================");

        Round round = new Round();

        //while (round.getHighestPoint() < 100) {
            System.out.println("ROUND " + round.getRoundNum());
            System.out.println("");
            round.startRound();

        //}


    }


}
