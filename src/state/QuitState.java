package state;

import millionaire.Game;

public class QuitState extends State {
    public QuitState(Game g) {
        super(g);
    }

    @Override
    public void display() {
        System.out.println("Are you sure you want to quit?");
        System.out.println("[Y] Yes, see you next time");
        System.out.println("[N] No, I wanna play a game");
    }

    @Override
    public void next(String input) {
        Game g = getGame();
        switch (input.toUpperCase()) {
            case "Y":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            case "N":
                g.setState(new MenuState(g));
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
