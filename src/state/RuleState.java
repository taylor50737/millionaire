package state;

import millionaire.Game;

public class RuleState extends State {
    public RuleState(Game g) {
        super(g);
    }

    @Override
    public void display() {
        System.out.println("This game has two levels of difficulty: easy and hard.");
        System.out.println("In both difficulty options, players can choose to walk away with their winnings after each round.");
        System.out.println("If a player fails to answer a question correctly, they will lose all their winnings and the game will end.");
        System.out.println("During the game, players are allowed to use 3 lifelines. Each lifeline can only be used once.");
        System.out.println();
        System.out.println("[E] View Easy mode rules");
        System.out.println("[H] View Hard mode rules");
        System.out.println("[L] View lifelines rules");
        System.out.println("[M] Go back to Main Menu");
        System.out.println();
    }

    @Override
    public void next(String input) {
        Game g = getGame();
        switch (input){
            case "m": case "M":
                g.setState(new MenuState(g));
                break;
            case "e": case "E":
                System.out.println("Easy difficulty");
                System.out.println("+---------+-------------+--------------------------------+---------------------------------------------+");
                System.out.println("| Rounds  | No. of Ques |          Dollar Values         |     Action after completing each round      |");
                System.out.println("+---------+-------------+--------------------------------+--------------------------------------------+");
                System.out.println("| Round 1 | 3 questions |        $100, $500, $1,000      |      Walk away with $1,000 or continue      |");
                System.out.println("| Round 2 | 3 questions |     $8,000, $16,000, $32,000   |      Walk away with $32,000 or continue     |");
                System.out.println("| Round 3 | 3 questions | $125,000, $500,000, $1,000,000 | Win the game and receive 1 million dollars! |");
                System.out.println("+---------+-------------+------------------------------ -+---------------------------------------------+");
                System.out.println();
                break;
            case "h": case "H":
                System.out.println("Hard difficulty");
                System.out.println("+---------+-------------+---------------------------------------------------+---------------------------------------------+");
                System.out.println("| Rounds  | No. of Ques |                   Dollar Values                   |     Action after completing each round      |");
                System.out.println("+---------+-------------+---------------------------------------------------+---------------------------------------------+");
                System.out.println("| Round 1 | 5 questions |           $100, $200, $300, $500, $1,000          |      Walk away with $1,000 or continue      |");
                System.out.println("| Round 2 | 5 questions |      $2,000, $4,000, $8,000, $16,000, $32,000     |      Walk away with $32,000 or continue     |");
                System.out.println("| Round 1 | 5 questions | $64,000, $125,000, $250,000, $500,000, $1,000,000 | Win the game and receive 1 million dollars! |");
                System.out.println("+---------+-------------+---------------------------------------------------+---------------------------------------------+");
                break;
            case "l": case "L":
                System.out.println("The following lifelines are available:");
                System.out.println("50/50 - This lifeline allows the game to eliminate two incorrect answers.");
                System.out.println("Ask the Audience - This lifeline allows the player to “ask the audience” for the correct answer.");
                System.out.println("Phone a friend - This lifeline allows the player to “phone to a friend” for the correct answer.");
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
