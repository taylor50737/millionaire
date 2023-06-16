package millionaire;

import java.util.LinkedHashMap;
import java.util.Map;

public class Question {
    public enum Choice {A, B, C, D}
    public String difficulty;
    private final String questionDescription;
    private final Map<Choice, String> answerDescription;
    private final Choice correctAnswer;

    public Question(String qDesc, String aDesc, String bDesc, String cDesc, String dDesc, Choice corrAns, String difficulty) {
        this.questionDescription = qDesc;
        this.answerDescription = new LinkedHashMap<>();
        this.answerDescription.put(Choice.A, aDesc);
        this.answerDescription.put(Choice.B, bDesc);
        this.answerDescription.put(Choice.C, cDesc);
        this.answerDescription.put(Choice.D, dDesc);
        this.correctAnswer = corrAns;
        this.difficulty = difficulty;
    }

    public Choice getCorrectAnswer(){
        return correctAnswer;
    }

    @Override
    public String toString() {
        return questionDescription + "\n" +
                 answerDescription.values()
                ;
    }

    public Map<Choice, String> getAnswerDescription() {
        return answerDescription;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public String getDifficulty(){return difficulty;}
}


