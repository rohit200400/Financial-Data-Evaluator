package financial.planner.GPT_insights_async.gemini;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

public class JsonStructure {

    // "sealed" classes and interfaces:
    // - only "permitted" classes can implement the interface
    //   or extend the class
    public sealed interface Part
            permits TextPart, InlineDataPart {
    }

    public record GeminiRequest(List<Content> contents) {
    }

    public record Content(List<Part> parts) {
    }

    public record TextPart(String text) implements Part {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record InlineDataPart(InlineData inlineData) implements Part {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record InlineData(String mimeType, String data) {
    }


    public record GeminiResponse(List<Candidate> candidates,
                                 PromptFeedback promptFeedback) {
        public record Candidate(Content content,
                                String finishReason,
                                int index,
                                List<SafetyRating> safetyRatings) {
            public record Content(List<TextPart> parts, String role) {
            }
        }
    }

    public record SafetyRating(String category, String probability) {
    }

    public record PromptFeedback(List<SafetyRating> safetyRatings) {
    }
}
