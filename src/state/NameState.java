package state;

import millionaire.Game;

public class NameState extends State{

    public NameState(Game g) {
        super(g);
    }
    @Override
    public void display() {
        System.out.println("Please enter your name: ");
    }

    @Override
    public void next(String input) {
        Game g = getGame();
        g.setPlayer(input);
        g.setState(new ChooseDifficultyState(g));
    }
}
