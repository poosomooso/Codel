package reflection;

public class AnswerCompilationException extends Exception {
    private final String message;

    public AnswerCompilationException(String message) {
        this.message = message;
    }
    public String getFailure() {
        return message;
    }
}
