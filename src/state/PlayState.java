package state;

import millionaire.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static millionaire.Game.PRIZE_SCALE_EASY;
import static millionaire.Game.PRIZE_SCALE_HARD;

public class PlayState extends State {
    public PlayState(Game g) {
        super(g);
    }
    Game g = getGame();
    NumberFormat formatter = NumberFormat.getIntegerInstance(Locale.CANADA);
    String currentPrize = formatter.format(getCurrentPrize(g.getqNo() + 1));

    // if it is Round 1 in hard mode, lifeline option will not be displayed
    @Override
    public void display() {
        if (g.getMode() == Game.Difficulty.HARD && g.getRound() == 1) {
            displayQuestion();
        } else {
            displayQuestion();
            displayLifelineOptions();
        }
    }
    
    @Override
    public void next(String input) {
        switch(input.toUpperCase()) {
            case "A":
            case "B":
            case "C":
            case "D":
                chooseAnswer(input.toUpperCase());
                break;
            case "Q":
                activateLifeLine(g.isFiftyFifty(), new FiftyFiftyState(g));
                break;
            case "W":
                activateLifeLine(g.isAskTheAudience(), new AskTheAudienceState(g));
                break;
            case "E":
                activateLifeLine(g.isPhoneAFriend(), new PhoneAFriendState(g));
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // Find the prize of next question, use question number to match the index of prize scale array
    private int getCurrentPrize(int qNo) {
        int prize;
        if (g.getMode() == Game.Difficulty.EASY) {
            prize = PRIZE_SCALE_EASY[qNo];
        } else {
            prize = PRIZE_SCALE_HARD[qNo];
        }
        return prize;
    }

    // Display question, get the question and answers from the question bank using getters
    private void displayQuestion() {
        System.out.println("Round " + g.getRound());
        System.out.println("Question " + (g.getqNo() + 1) + " (" + g.getQuestionBank().get(g.getqNo()).getDifficulty() + ")" + ":");
        System.out.println("The following question is worth $" + currentPrize);
        System.out.println(g.getQuestionBank().get(g.getqNo()).getQuestionDescription());
        System.out.println(g.getQuestionBank().get(g.getqNo()).getAnswerDescription().get(Question.Choice.A));
        System.out.println(g.getQuestionBank().get(g.getqNo()).getAnswerDescription().get(Question.Choice.B));
        System.out.println(g.getQuestionBank().get(g.getqNo()).getAnswerDescription().get(Question.Choice.C));
        System.out.println(g.getQuestionBank().get(g.getqNo()).getAnswerDescription().get(Question.Choice.D));
        System.out.println();
    }

    // Check the lifeline boolean to find out how many lifeline left, add the lifeline string to arraylist
    // and display it if the lifeline boolean is true
    private void displayLifelineOptions() {
        ArrayList<String> lifeLineList = new ArrayList<>();
        if (g.isFiftyFifty())
            lifeLineList.add("[Q] 50/50");
        if (g.isAskTheAudience())
            lifeLineList.add("[W] Ask the Audience");
        if (g.isPhoneAFriend())
            lifeLineList.add("[E] Phone a friend");
        System.out.println("Available Lifeline: ");
        if (lifeLineList.size() != 0) {
            for (int i = 0; i < lifeLineList.size(); i++) {
                System.out.print(lifeLineList.get(i));
                if (i < lifeLineList.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("You have used up all lifelines");
        }
        System.out.println();
    }

    // Store the chosen answer to the variable and enter to finalizeAnswerState
    private void chooseAnswer(String ans) {
        g.setChosenAns(Question.Choice.valueOf(ans));
        g.setState(new FinalizeAnswerState(g));
    }

    // Validation to check whether the player is allowed to use lifeline or not
    private void activateLifeLine(boolean canUseLifeline, State lifeLine) {
        Game.Difficulty mode = g.getMode();
        int round = g.getRound();
        if (mode == Game.Difficulty.EASY || (mode == Game.Difficulty.HARD && round > 1)) {
            if (canUseLifeline) {
                g.setState(lifeLine);
            } else {
                System.out.println("You have already used this lifeline!");
            }
        } else {
            System.out.println("Hey! You can't use any lifelines right now!");
        }
    }
}
