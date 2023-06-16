package state;

import millionaire.*;

import java.util.*;

public class PhoneAFriendState extends State {

    private Game g;
    private String correctAnsDes;
    private String correctAnsChoice;
    private String friend;
    private Map<Integer, String> contactList;
    private String[] wrongAnsChoiceList;
    private String randAnsChoice;
    private int qNo;
    private int qRound;

    //    Class Constructor
    public PhoneAFriendState(Game g) {
        super(g);
        this.g = getGame();
        this.qNo = g.getqNo();
        this.qRound = g.getRound();
        this.correctAnsDes = fetchCorrectAnsDesc();
        this.correctAnsChoice = fetchCorrectAnsChoice();
        this.wrongAnsChoiceList = fetchWrongAnsChoiceList();
        this.contactList = new LinkedHashMap<>();
        this.contactList.put(1, "Chris");
        this.contactList.put(2, "Mehrnaz");
        this.contactList.put(3, "Nebojsa");
        this.contactList.put(4, "Patrick");
        this.contactList.put(5, "Robert");
        this.randAnsChoice = fetchRandAnsChoice();
    }

    //    Overriding the display method from the State class to determine what is displaying in this class
    @Override
    public void display() {
        g.setPhoneAFriend(false);
        int quickKey = quickKey();
        // If phone other friend option is chosen 
        if (quickKey == 6){
            setFriend();
            System.out.println(friend + " replied, 'I reckon " + randAnsChoice + ".'");
        } else {
            setFriend(quickKey);
            // If phone Mehrnaz option is chosen. Mehrnaz is a genius (or she has the answer key of all the questions).
            // She will always get it correct.
            // If phone top friends other than Mehrnaz is chosen, the else block will run. 
            if (quickKey == 2){
                System.out.println(friend + " replied, 'I reckon " + correctAnsChoice + ".'");
            } else {
                System.out.println(friend + " replied, 'I reckon " + randAnsChoice + ".'");
            }
        }
    }

    //    Overriding the next method from the State class to determine which class and how this class will bring you forward to
    @Override
    public void next(String input) {
        Scanner scanner = new Scanner(System.in);
        while (!(input.toUpperCase().equals(Question.Choice.A.name())) && !(input.toUpperCase().equals(Question.Choice.B.name())) &&
                !(input.toUpperCase().equals(Question.Choice.C.name())) && !(input.toUpperCase().equals(Question.Choice.D.name()))) {
            System.out.println("Invalid input.");
            System.out.print("Enter: ");
            input = scanner.nextLine();
        }
        chooseAnswer(input.toUpperCase());
    }

    //   Allow the player to choose their friends to call with a list of 5 closest friends or other friends to call 
    //   and return the integer quickKey to call as an attribute of the class
    public int quickKey() {
        boolean invalidInput = false;
        int quickKey = 0;
        Scanner scanner;
        System.out.println("Contact List");
        System.out.println("+-----------------+-------------------+");
        System.out.println("| Top Friend List | Quick Key To Dial |");
        System.out.println("+-----------------+-------------------+");
        System.out.println("| Chris           | 1                 |");
        System.out.println("| Mehrnaz         | 2                 |");
        System.out.println("| Nebojsa         | 3                 |");
        System.out.println("| Patrick         | 4                 |");
        System.out.println("| Robert          | 5                 |");
        System.out.println("| Other Friend    | 6                 |");
        //   Ensure the player is choosing any number, which is the quickKey attribute, within 1 to 6. 
        do {
              scanner = new Scanner(System.in);
              invalidInput = false;
              System.out.println("Enter quick key to dial: ");
            try {
                quickKey = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. The valid input should be 1, 2, 3, 4, 5 or 6");
                invalidInput = true;
            }
        } while (invalidInput);
        while (quickKey < 1 || quickKey > 6){
            System.out.println("Invalid input. The valid input should be 1, 2, 3, 4, 5 or 6");
            System.out.println("Enter quick key to dial: ");
            quickKey = scanner.nextInt();
        }
        return quickKey;
    }

    //   A setter for the friend attribute of the class from the returned quickKey (1 - 5) attribute.
    private String setFriend(int quickKey){
        this.friend = contactList.get(quickKey);
        return friend;
    }

    //   A setter for the friend attribute of the class from the returned quickKey (6) attribute. Allow the player to
    //   enter the name of their friend to call and return it. 
    private String setFriend(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the friend you wish to call: ");
        this.friend = scanner.nextLine();
        return friend;
    }

    //   Get the corresponding description of the correct answer choice of this question and return it as an attribute of the class
    private String fetchCorrectAnsDesc() {
        Question.Choice correctAns = g.getQuestionBank().get(qNo).getCorrectAnswer();
        String correctAnsDes = g.getQuestionBank().get(qNo).getAnswerDescription().get(correctAns);
        return correctAnsDes;
    }

    //    Get the corresponding correct answer choice of this question and return it as an attribute of the class
    private String fetchCorrectAnsChoice(){
        return g.getQuestionBank().get(qNo).getCorrectAnswer().name();
    }

    //    Get the corresponding wrong answer choice of this question and use an array to contain them and return the array
    private String[] fetchWrongAnsChoiceList() {
        Question.Choice correctAns = g.getQuestionBank().get(qNo).getCorrectAnswer();
        int correctAnsVal = correctAns.ordinal();
        Question.Choice [] allChoices = Question.Choice.values();
        String [] allWrongChoices = new String[allChoices.length - 1];
        for (int i = 0, k = 0; i < allChoices.length; i++){
            if (i == correctAnsVal){
                continue;
            }
            allWrongChoices[k++] = allChoices[i].name();
        }
        return allWrongChoices;
    }

    //   Assign the odd for the answer to be replied and return it 
    private String fetchRandAnsChoice() {
        // create a string array work like a pool which is containing 20 strings
        String [] randAnsList = new String[20];
        int oddCorrectAns;
        // configure the occurrence of the correct answer in the pool. The later the game, the lower the occurrence.
        if (qRound == 1) {
            oddCorrectAns = 16;
        } else if (qRound == 2) {
            oddCorrectAns = 13;
        } else {
            oddCorrectAns = 10;
        }
        // configure the other 3 wrong answers' occurrence in the pool to share the remaining spots of the pool
        int odd2 = new Random().nextInt(21 - oddCorrectAns);
        int odd3 = new Random().nextInt(21 - oddCorrectAns - odd2);
        int odd4 = 20 - oddCorrectAns - odd2 - odd3;
        for (int x = 0; x < 20; x++){
            if (x < oddCorrectAns){
                randAnsList[x] = correctAnsChoice;
            } else if (oddCorrectAns <= x && x < oddCorrectAns + odd2){
                randAnsList[x] = wrongAnsChoiceList[0];
            } else if (oddCorrectAns + odd2 <= x && x < oddCorrectAns + odd2 + odd3){
                randAnsList[x] = wrongAnsChoiceList[1];
            } else {
                randAnsList[x] = wrongAnsChoiceList[2];
            }
        }
        // randomise one string from the pool and return it
        String randAnsChoice = randAnsList[new Random().nextInt(20)];
        return randAnsChoice;
    }

    //    set the choice by invoking the setChosenAns method from the Game class and bring it forward to the FinalizeAnswerState Class
    private void chooseAnswer(String ans) {
        g.setChosenAns(Question.Choice.valueOf(ans));
        g.setState(new FinalizeAnswerState(g));
    }


}
