import java.util.Scanner;
import java.util.Random;

public class RPSLS 
{
    enum Move 
    {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }
    public static int winner(Move a, Move b) 
    {
        if (a == b) 
        {
            return 0;
        }
        switch (a) 
        {
            case ROCK:
                if (b == Move.SCISSORS || b == Move.LIZARD)
                    return 1;
                else
                    return -1;

            case PAPER:
                if (b == Move.ROCK || b == Move.SPOCK)
                    return 1;
                else
                    return -1;

            case SCISSORS:
                if (b == Move.PAPER || b == Move.LIZARD)
                    return 1;
                else
                    return -1;

            case LIZARD:
                if (b == Move.SPOCK || b == Move.PAPER)
                    return 1;
                else
                    return -1;

            case SPOCK:
                if (b == Move.SCISSORS || b == Move.ROCK)
                    return 1;
                else
                    return -1;
        }

        return 0;
    }

    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        int playerScore = 0;
        int computerScore = 0;

        Move[] moves = Move.values();
        for (int i = 1; i <= 5; i++) 
        {
            System.out.println("\nRound " + i);
            System.out.print("Enter Move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            Move player = Move.valueOf(s.next().toUpperCase());
            Move computer = moves[r.nextInt(5)];
            System.out.println("Player: " + player);
            System.out.println("Computer: " + computer);
            int result = winner(player, computer);

            if (result == 1) 
            {
                System.out.println("You win this round!");
                playerScore++;
            }
            else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            }
            else {
                System.out.println("Round is a tie!");
            }
        }
        System.out.println("\nFinal Score");
        System.out.println("You: " + playerScore);
        System.out.println("Computer: " + computerScore);

        if (playerScore > computerScore) 
        {
            System.out.println("You win " + playerScore + "-" + computerScore);
        }
        else if (computerScore > playerScore) 
        {
            System.out.println("Computer wins " + computerScore + "-" + playerScore);
        }
        else 
        {
            System.out.println("Match Draw");
        }
        s.close();
    }
}