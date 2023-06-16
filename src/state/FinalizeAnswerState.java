package state;

import millionaire.*;

public class FinalizeAnswerState extends State {
    public FinalizeAnswerState(Game g) {
        super(g);
    }
    Game g = getGame();
    @Override
    public void display() {
        String chosenAnsDesc = g.getQuestionBank().get(g.getqNo()).getAnswerDescription().get(g.getChosenAns());
        System.out.println("You have chosen answer " + chosenAnsDesc);
        System.out.println("Confirm answer?");
        System.out.println("[Y] Yes, I am pretty sure");
        System.out.println("[N] Wait, on second thought...");
        System.out.println();
    }

    @Override
    public void next(String input) {
        Question.Choice correctAns = g.getQuestionBank().get(g.getqNo()).getCorrectAnswer();
        Question.Choice chosenAns = g.getChosenAns();
        String correctAnsDesc = g.getQuestionBank().get(g.getqNo()).getAnswerDescription().get(correctAns);

        switch (input.toUpperCase()) {
            case "Y":
                System.out.println("The correct answer is: " + correctAnsDesc);
                // display if player is right
                if (chosenAns == correctAns) {
                    System.out.println("Wow! You are right!");
                    g.setqNo(g.getqNo() + 1);
                    // In Easy mode, every 3 correct question will get into WalkawayState
                    // If player answered all question will get into WinState
                    if (g.getMode() == Game.Difficulty.EASY) {
                        if (g.getqNo() == 3 || g.getqNo() == 6) {
                            g.setRound(g.getRound() + 1);
                            g.setState(new WalkawayState(g));
                        } else if (g.getqNo() == 9) {
                            g.setState(new WinState(g));
                        } else {
                            g.setState(new PlayState(g));
                        }
                        // In Hard mode, every 5 correct question will get into WalkawayState
                        // If player answered all question will get into WinState
                    } else {
                        if (g.getqNo() == 5 || g.getqNo() == 10) {
                            g.setRound(g.getRound() + 1);
                            g.setState(new WalkawayState(g));
                        } else if (g.getqNo() == 15) {
                            g.setState(new WinState(g));
                        } else {
                            g.setState(new PlayState(g));
                        }
                    }
                    // display if player is wrong
                } else {
                    System.out.println("Oops! You are wrong!");
                    g.setState(new LoseState(g));
                }
                break;
            case "N":
                // go back to PlayState if player don't confirm answer
                g.setState(new PlayState(g));
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
