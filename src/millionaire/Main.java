package millionaire;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<List> questionBank = questionBank();
        for(int i=0;i<3;i++){
            Collections.shuffle(questionBank.get(i));
        }
        List<Question> easyQuestionBank = easyQuestionBank(questionBank);
        List<Question> hardQuestionBank = hardQuestionBank(questionBank);
        Game game = new Game(easyQuestionBank, hardQuestionBank);
        game.start();
    }

    // Reads questions from a txt file, organizes them by difficulty level, and returns a list of lists of questions.
    private static List<List> questionBank() {

        List<Question> questions_round1 = new ArrayList<>();
        List<Question> questions_round2 = new ArrayList<>();
        List<Question> questions_round3 = new ArrayList<>();
        List<List> questions = new ArrayList<>();

        File questionsTxt = new File("src/millionaire/questions.txt");
        try (Scanner input = new Scanner(questionsTxt)){
            while(input.hasNextLine()){
                    String qDesc = input.nextLine();
                    String aDesc = input.nextLine();
                    String bDesc = input.nextLine();
                    String cDesc = input.nextLine();
                    String dDesc = input.nextLine();
                    String corrAns = input.nextLine();
                    String difficulty = input.nextLine();
                    Question.Choice correctChoice = null;
                    switch (corrAns.toUpperCase()) {
                        case "A":
                            correctChoice = Question.Choice.A;
                            break;
                        case "B":
                            correctChoice = Question.Choice.B;
                            break;
                        case "C":
                            correctChoice = Question.Choice.C;
                            break;
                        case "D":
                            correctChoice = Question.Choice.D;
                            break;
                        default:
                            break;
                    }
                    Question q = new Question(qDesc, aDesc, bDesc, cDesc, dDesc, correctChoice, difficulty);
                    if(q.difficulty.equals("Easy")&&questions_round1.size()<10){
                            questions_round1.add(q);
                    } else if (q.difficulty.equals("Medium")&&questions_round2.size()<10) {
                            questions_round2.add(q);
                    } else{
                        if(q.difficulty.equals("Hard")&&questions_round3.size()<10){
                                questions_round3.add(q);
                        }
                    }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

        questions.add(questions_round1);
        questions.add(questions_round2);
        questions.add(questions_round3);

        return questions;
    }

    //Takes in a list of questions and returns 3 from each round as an easy question bank
    private static List<Question> easyQuestionBank(List<List> questionBank) {
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                questionList.add((Question) questionBank.get(i).get(j));
            }
        }
        return questionList;
    }

    ////Takes in a list of questions and returns 5 from each round as a hard question bank
    private static List<Question> hardQuestionBank(List<List> questionBank) {
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                questionList.add((Question) questionBank.get(i).get(j));
            }
        }
        return questionList;
    }
}