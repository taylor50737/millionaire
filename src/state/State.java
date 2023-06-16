package state;
import millionaire.Game;

public abstract class State {
    private final Game game;

    public State(Game g) {
        game = g;
    }

    public abstract void display() throws InterruptedException;
    public abstract void next(String input);

    public Game getGame() {
        return game;
    }
}