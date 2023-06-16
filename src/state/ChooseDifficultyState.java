package state;

import millionaire.Game;

public class ChooseDifficultyState extends State {
    public ChooseDifficultyState(Game g) {
        super(g);
    }

    @Override
    public void display() {
        Game g = getGame();
        System.out.println("Nice to meet you! " + g.getPlayer());
        System.out.println("Please choose the difficulty (Easy/Hard)");
        System.out.println("[E] Choose Easy mode");
        System.out.println("[H] Choose Hard mode");
    }

    @Override
    public void next(String input) {
        Game g = getGame();
        switch (input) {
            case "e": case "E":
                g.setMode(Game.Difficulty.EASY);
                g.setQuestionBank(g.getEasyQuestionBank());
                g.setState(new PlayState(g));
                break;
            case "h": case "H":
                g.setMode(Game.Difficulty.HARD);
                g.setQuestionBank(g.getHardQuestionBank());
                g.setState(new PlayState(g));
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
