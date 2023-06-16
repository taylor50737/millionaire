package millionaire;

import state.MenuState;
import state.State;

import java.util.List;
import java.util.Scanner;

public class Game {
    public enum Difficulty {EASY, HARD}

    /* constants */
    public static final int[] PRIZE_SCALE_EASY = {
            0, 100, 500, 1000,
            8000, 16000, 32000,
            125000, 500000, 1000000
    };
    public static final int[] PRIZE_SCALE_HARD = {
            0, 100, 200, 300, 500, 1000,
            2000, 4000, 8000, 16000, 32000,
            64000, 125000, 250000, 500000, 1000000
    };

    /* fields */
    private List<Question> questionBank;
    private final List<Question> easyQuestionBank;
    private final List<Question> hardQuestionBank;
    private State currentState;
    private int round;
    private int qNo;
    private boolean fiftyFifty;
    private boolean askTheAudience;
    private boolean phoneAFriend;
    private Difficulty mode;
    private Question.Choice chosenAns;
    private String player;

    /* methods */
    public Game(List<Question> easyQuestionBank, List<Question> hardQuestionBank) {
        this.easyQuestionBank = easyQuestionBank;
        this.hardQuestionBank = hardQuestionBank;
        this.currentState = new MenuState(this);
        this.round = 1;
        this.qNo = 0;
        this.fiftyFifty = true;
        this.askTheAudience = true;
        this.phoneAFriend = true;
    }

    // main loop of the game
    public void start() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            currentState.display();
            System.out.print("Enter: ");
            currentState.next(scanner.nextLine());
            System.out.println();
        }
    }

    /* getters setters */
    public void setState(State newState) {
        currentState = newState;
    }

    public Difficulty getMode() {
        return mode;
    }

    public void setMode(Difficulty mode) {
        this.mode = mode;
    }

    public List<Question> getEasyQuestionBank() {
        return easyQuestionBank;
    }

    public List<Question> getHardQuestionBank() {
        return hardQuestionBank;
    }

    public List<Question> getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(List<Question> questionBank) {
        this.questionBank = questionBank;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getqNo() {
        return qNo;
    }

    public void setqNo(int qNo) {
        this.qNo = qNo;
    }

    public boolean isFiftyFifty() {
        return fiftyFifty;
    }

    public void setFiftyFifty(boolean fiftyFifty) {
        this.fiftyFifty = fiftyFifty;
    }

    public boolean isAskTheAudience() {
        return askTheAudience;
    }

    public void setAskTheAudience(boolean askTheAudience) {
        this.askTheAudience = askTheAudience;
    }

    public boolean isPhoneAFriend() {
        return phoneAFriend;
    }

    public void setPhoneAFriend(boolean phoneAFriend) {
        this.phoneAFriend = phoneAFriend;
    }

    public Question.Choice getChosenAns() {
        return chosenAns;
    }

    public void setChosenAns(Question.Choice chosenAns) {
        this.chosenAns = chosenAns;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }
}