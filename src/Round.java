import java.util.*;

public class Round{
    private ArrayList<Player>listOfPlayers = new ArrayList<>();
    private int setNo;
    private boolean heartsBroken;
    private int roundNo;
    private int leadingPlayer;

    private void Round(){
        if (setNo == 13){
            roundNo++;
            setNo = 0;
        }
    }

    public void startRound(){

    }

    public void HumanexchangeCards(){
        Scanner sc = new Scanner(System.in);
        System.out.print("");


    }
    public void tallyPoints(int points, Player player) {

    }





}