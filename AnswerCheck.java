package engine;

public class AnswerCheck {
    public boolean success;

    public String feedback;

    public AnswerCheck(boolean guess) {
        success = guess;
        feedback = success ? "Congratulations, you're right!" : "Wrong answer! Please try again.";
    }
}
