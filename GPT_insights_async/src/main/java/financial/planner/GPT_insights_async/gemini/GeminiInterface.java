package financial.planner.GPT_insights_async.gemini;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;


@HttpExchange("/v1beta/models/")
public interface GeminiInterface {
    @PostExchange("{model}:generateContent")
    JsonStructure.GeminiResponse getCompletion(
            @PathVariable String model,
            @RequestBody JsonStructure.GeminiRequest request);
}