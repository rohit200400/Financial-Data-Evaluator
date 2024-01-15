package financial.planner.GPT_insights_async.exceeption;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message) {
        super(message);
    }
}
