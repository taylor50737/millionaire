package state;

import millionaire.*;

import java.sql.SQLOutput;
import java.util.*;

public class FiftyFiftyState extends State {

    private Game g;
    private final String correctAnsChoice;
    private final String correctAnsDes;

    private final String randWrongAnsChoice;
    private final int correctAnsOrd;
    private final int randWrongAnsOrd;
    private int qNo;

    //    Class Constructor
    public FiftyFiftyState(Game g) {
        super(g);
        this.g = getGame();
        this.qNo = g.getqNo();
        this.correctAnsDes = fetchCorrectAnsDesc();
        this.correctAnsChoice = fetchCorrectAnsChoice();
        this.correctAnsOrd = fetchCorrectAnsOrd();
        this.randWrongAnsChoice = fetchRandWrongAnsChoice();
        this.randWrongAnsOrd = fetchRandWrongAnsOrd();
    }

    //    Overriding the display method from the State class to determine what is displaying in this class
    @Override
    public void display() {
        //TODO
        g.setFiftyFifty(false);
        displayQuestAndChoices();
    }

    //    Overriding the next method from the State class to determine which class and how this class will bring you forward to
    @Override
    public void next(String input) {
        //TODO
        Scanner scanner = new Scanner(System.in);
        //Restrict the user from entering anything other than the 2 choices
        while (!(input.toUpperCase().equals(correctAnsChoice)) && !(input.toUpperCase().equals(randWrongAnsChoice))){
            if (correctAnsOrd < randWrongAnsOrd){
                System.out.println("Invalid input. The valid input should be " + correctAnsChoice + " or "
                        + randWrongAnsChoice + ".");
            } else {
                System.out.println("Invalid input. The valid input should be " + randWrongAnsChoice + " or "
                        + correctAnsChoice + ".");
            }
                System.out.print("Enter: ");
                input = scanner.nextLine();
            }
        chooseAnswer(input.toUpperCase());
        }

    //    Get the corresponding description of the correct answer choice of this question and return it as an attribute of the class
    private String fetchCorrectAnsDesc() {
        Question.Choice correctAns = g.getQuestionBank().get(qNo).getCorrectAnswer();
        String correctAnsDes = g.getQuestionBank().get(qNo).getAnswerDescription().get(correctAns);
        return correctAnsDes;
    }

    //    Get the corresponding correct answer choice of this question and return it as an attribute of the class
    private String fetchCorrectAnsChoice(){
        return g.getQuestionBank().get(qNo).getCorrectAnswer().name();
    }

    //    Get the corresponding ordinal of the correct answer choice of this question and return it as an attribute of the class
    private int fetchCorrectAnsOrd(){
        return Question.Choice.valueOf(correctAnsChoice).ordinal();
    }

    //    Get the corresponding wrong answer choice of this question and use an array to contain them. Then randomise
    //    one out of them and return it as an attribute of the class.
    private String fetchRandWrongAnsChoice() {
        Question.Choice correctAns = g.getQuestionBank().get(qNo).getCorrectAnswer();
        int correctAnsVal = correctAns.ordinal();
        // Create an array of all the choices
        Question.Choice [] allChoices = Question.Choice.values();
        // Eliminate the correct answer and create another array of the other 3 wrong choices
        Question.Choice [] allWrongChoices = new Question.Choice[allChoices.length - 1];
        for (int i = 0, k = 0; i < allChoices.length; i++){
            if (i == correctAnsVal){
                continue;
            }
            allWrongChoices[k++] = allChoices[i];
        }
        // Randomise one out of the 3 wrong choices
        int randomise = new Random().nextInt(3);
        String randWrongAnsChoice = allWrongChoices[randomise].name();
        return randWrongAnsChoice;
    }

    //    Get the ordinal of the randomised wrong answer choice and return it as an attribute of the class
    private int fetchRandWrongAnsOrd(){
        return Question.Choice.valueOf(randWrongAnsChoice).ordinal();
    }

    //    Display the question with the correct answer choice and randomised wrong answer choice
    private void displayQuestAndChoices(){
        String wrongAnsDes = g.getQuestionBank().get(qNo).getAnswerDescription().get(Question.Choice.values()[randWrongAnsOrd]);
        String questDesc = g.getQuestionBank().get(qNo).getQuestionDescription();
        System.out.println("Question " + (qNo + 1) + ":");
        System.out.println(questDesc);
        //ensure the order of the ordinal is ascending (e.g. A will always appear prior to D)
        if (correctAnsOrd < randWrongAnsOrd) {
            System.out.println(correctAnsDes);
            System.out.println(wrongAnsDes);
        } else if (correctAnsOrd > randWrongAnsOrd) {
            System.out.println( wrongAnsDes);
            System.out.println(correctAnsDes);
        }
    }

    //    set the choice by invoking the setChosenAns method from the Game class and bring it forward to the FinalizeAnswerState Class
    private void chooseAnswer(String ans) {
        g.setChosenAns(Question.Choice.valueOf(ans.toUpperCase()));
        g.setState(new FinalizeAnswerState(g));
    }
}
