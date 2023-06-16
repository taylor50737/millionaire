package state;

import millionaire.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class AskTheAudienceState extends State {
    private Game g;
    private String correctAnsChoice;
    private String[] wrongAnsChoiceList;
    private Map<String, Integer> choiceBallotMap;
    private int qNo;
    private int qRound;


//    Class Constructor
    public AskTheAudienceState(Game g) {
        super(g);
        this.g = getGame();
        this.qNo = g.getqNo();
        this.qRound = g.getRound();
        this.correctAnsChoice = fetchCorrectAnsChoice();
        this.wrongAnsChoiceList = fetchWrongAnsChoiceList();
        this.choiceBallotMap = choiceBallotGenerator();
    }

//    Overriding the display method from the State class to determine what is displaying in this class
    @Override
    public void display() throws InterruptedException {
        //set the AskTheAudience boolean to false once this class is being invoked as the lifeline has been used
        g.setAskTheAudience(false);
        displayQuestAndChoices();
    }

//    Overriding the next method from the State class to determine which class and how this class will bring you forward to
    @Override
    public void next(String input) {
        //Restrict the user from entering anything other than the 4 choices
        Scanner scanner = new Scanner(System.in);
        while (!(input.toUpperCase().equals(Question.Choice.A.name())) && !(input.toUpperCase().equals(Question.Choice.B.name())) &&
                !(input.toUpperCase().equals(Question.Choice.C.name())) && !(input.toUpperCase().equals(Question.Choice.D.name()))) {
            System.out.println("Invalid input.");
            System.out.print("Enter: ");
            input = scanner.nextLine();
        }
        chooseAnswer(input.toUpperCase());
    }

//    Get the corresponding correct answer choice of this question and return it as an attribute of the class
    private String fetchCorrectAnsChoice(){
        return g.getQuestionBank().get(qNo).getCorrectAnswer().name();
    }

//    Get the corresponding wrong answer choice of this question and use an array to contain them
//    and return it as an attribute of the class
    private String[] fetchWrongAnsChoiceList() {
        Question.Choice correctAns = g.getQuestionBank().get(qNo).getCorrectAnswer();
        int correctAnsVal = correctAns.ordinal();
        Question.Choice [] allChoices = Question.Choice.values();
        String [] wrongAnsChoiceList = new String[allChoices.length - 1];
        for (int i = 0, k = 0; i < allChoices.length; i++){
            if (i == correctAnsVal){
                continue;
            }
            wrongAnsChoiceList[k++] = allChoices[i].name();
        }
        return wrongAnsChoiceList;
    }

//    Generate a random ballot percentage bound with each answer choice by mapping. The later the game, the lower
//    the chance that the correct answer will be the mostly voted choice
//    and return the map as an attribute of the class
    private Map<String, Integer> choiceBallotGenerator(){
        Map<String, Integer> choiceBallotMap = new HashMap<>();
        int roundBallotModifierA;
        int roundBallotModifierB;
        //roundBallotModifierB determine the lower bound of the correct choice being voted, while
        //(roundBallotModifierA + roundBallotModifierB determine the upper bound
        if(qRound == 1){
            roundBallotModifierA = 60;
            roundBallotModifierB = 41;
        } else if(qRound == 2){
            roundBallotModifierA = 40;
            roundBallotModifierB = 31;
        } else {
            roundBallotModifierA = 20;
            roundBallotModifierB = 21;
        }
        //The ballots of the other 3 wrong choices are 3 randomly generated integer of the remaining percentage out of 100%
        int correctAnsBallot = (new Random().nextInt(roundBallotModifierB)) + roundBallotModifierA;
        int randWrongAnsBallot1 = new Random().nextInt(100 - correctAnsBallot);
        int randWrongAnsBallot2 = new Random().nextInt(100 - correctAnsBallot - randWrongAnsBallot1);
        int randWrongAnsBallot3 = 100 - correctAnsBallot - randWrongAnsBallot1 - randWrongAnsBallot2;

        choiceBallotMap.put(correctAnsChoice, correctAnsBallot);
        choiceBallotMap.put(wrongAnsChoiceList[0],randWrongAnsBallot1);
        choiceBallotMap.put(wrongAnsChoiceList[1],randWrongAnsBallot2);
        choiceBallotMap.put(wrongAnsChoiceList[2],randWrongAnsBallot3);

        return choiceBallotMap;
    }

//    Create an audience voting countdown timer by using the java countdownlatch class. Ensure the voting result
//    and the continuing threads will only be shown after the countdown time thread finished
    private void displayQuestAndChoices() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Thread firstThread = new Thread(() -> {
            try {
                System.out.println("Audiences are voting. 5 seconds left.");
                // simulate a delay of 5 seconds
                for (int i = 4; i >= 0; i--) {
                    Thread.sleep(1000);
                    System.out.println("Audiences are voting. " + i + " seconds left.");
                }
                // count down the latch
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        firstThread.start();

        latch.await();

        System.out.println("Question " + (qNo + 1) + ":");
        System.out.println(g.getQuestionBank().get(qNo).getQuestionDescription());
        System.out.println(g.getQuestionBank().get(qNo).getAnswerDescription().get(Question.Choice.A) + " ----- " + choiceBallotMap.get("A") + "%");
        System.out.println(g.getQuestionBank().get(qNo).getAnswerDescription().get(Question.Choice.B) + " ----- " + choiceBallotMap.get("B") + "%");
        System.out.println(g.getQuestionBank().get(qNo).getAnswerDescription().get(Question.Choice.C) + " ----- " + choiceBallotMap.get("C") + "%");
        System.out.println(g.getQuestionBank().get(qNo).getAnswerDescription().get(Question.Choice.D) + " ----- " + choiceBallotMap.get("D") + "%");
    }

//    set the choice by invoking the setChosenAns method from the Game class and bring it forward to the FinalizeAnswerState Class
    private void chooseAnswer(String ans) {
        g.setChosenAns(Question.Choice.valueOf(ans));
        g.setState(new FinalizeAnswerState(g));
    }
}
