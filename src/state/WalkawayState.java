package state;

import millionaire.Game;

public class WalkawayState extends State {
    public WalkawayState(Game g) {
        super(g);
    }

    @Override
    public void display() {
        Game g = getGame();
        if (g.getRound() == 2) {
            System.out.println("Well done! " + g.getPlayer());
            System.out.println("You have correctly answered all questions in round 1");
            System.out.println("You may choose to move on to round 2 or walk away with $1,000 now");
            System.out.println();
            System.out.println("Continue?");
            System.out.println("[Y] Yes, move on to next round");
            System.out.println("[N] No, I want to walk away now");

        } else {
            System.out.println("Marvelous! " + g.getPlayer());
            System.out.println("You have correctly answered all questions in round 2");
            System.out.println("You may choose to move on to round 3 or walk away with $32,000 now");
            System.out.println();
            System.out.println("Continue?");
            System.out.println("[Y] Yes, move on to next round");
            System.out.println("[N] No, I want to walk away now");
        }
        System.out.println();
    }

    @Override
    public void next(String input) {
        Game g = getGame();
        switch (input.toUpperCase()) {
            case "Y":
                System.out.println("Let us continue!");
                g.setState(new PlayState(g));
                break;
            case "N":
                if (g.getRound() == 2) {
                    System.out.println("Congratulations! You won $1,000");
                } else {
                    System.out.println("Congratulations! You won $32,000");
                }
                System.out.println("Goodbye! " + g.getPlayer());
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
