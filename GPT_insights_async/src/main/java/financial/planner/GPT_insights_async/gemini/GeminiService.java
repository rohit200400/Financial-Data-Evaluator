package financial.planner.GPT_insights_async.gemini;

import financial.planner.GPT_insights_async.entity.InvestmentInsights;
import financial.planner.GPT_insights_async.gemini.JsonStructure.*;
import financial.planner.GPT_insights_async.service.InsightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.List;
import java.util.Map;


@Service
@Log4j2
public class GeminiService {
    public static final String GEMINI_PRO = "gemini-pro";
    public static final String GEMINI_PRO_VISION = "gemini-pro-vision";

    private final GeminiInterface geminiInterface;
    @Autowired
    private InsightService insightService;

    @Autowired
    public GeminiService(GeminiInterface geminiInterface) {
        this.geminiInterface = geminiInterface;
    }

    public GeminiResponse getCompletion(GeminiRequest request) {
        return geminiInterface.getCompletion(GEMINI_PRO, request);
    }

    public GeminiResponse getCompletionWithImage(GeminiRequest request) {
        return geminiInterface.getCompletion(GEMINI_PRO_VISION, request);
    }

    public String analyzeData(String text, Map<String, String> userPreferences, MultipartFile file, String username) {
        StringBuilder prompt = new StringBuilder(text);

        // Add user preferences to StringBuilder
        StringBuilder preferences = new StringBuilder();
        for (Map.Entry<String, String> entry : userPreferences.entrySet()) {
            preferences.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
        }

        prompt.append(preferences);

        // Add the file content to StringBuilder
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                prompt.append(line).append("\n");
            }
        } catch (IOException e) {
            // Handle the exception appropriately, e.g., log the error
            log.error("Error reading file content: {}", e.getMessage());
        }

        // Update the 'text' variable with the combined content
        text = prompt.toString();

        // Perform the Gemini
        // request
        GeminiResponse response = getCompletion(new GeminiRequest(
                List.of(new Content(List.of(new TextPart(text))))));

        String analysis = response.candidates().getFirst().content().parts().getFirst().text();
        // save to database
        InvestmentInsights investmentInsight= InvestmentInsights.builder().insightData(analysis)
                .UserPreferences(preferences.toString())
                .userId(username).build();

        insightService.addInsight(investmentInsight);

        return analysis;
    }

    public String analyzeDataWithImage(String text, Map<String, String> userPreferences, MultipartFile imageFile,String username) throws IOException {
        StringBuilder prompt = new StringBuilder(text);

        // Add user preferences to prompt
        StringBuilder preferences = new StringBuilder(text);
        for (Map.Entry<String, String> entry : userPreferences.entrySet()) {
            prompt.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
        }

        prompt.append(preferences);

        byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
        String contentType = imageFile.getContentType();
        if (contentType != null && contentType.startsWith("image/")) {
            // If the content type is available and starts with "image/"
            GeminiResponse response = getCompletionWithImage(
                    new GeminiRequest(List.of(new Content(List.of(
                            new TextPart(prompt.toString()),
                            new InlineDataPart(new InlineData(contentType,
                                    Base64.getEncoder().encodeToString(imageBytes)))
                    )))));

            // System.out.println(response);
            String analysis = response.candidates().getFirst().content().parts().getFirst().text();
            // save to database
            InvestmentInsights investmentInsight= InvestmentInsights.builder().insightData(analysis)
                    .UserPreferences(preferences.toString())
                    .userId(username).build();

            insightService.addInsight(investmentInsight);
            return analysis;

        } else {
            // Handle the case where the content type is not recognized
            throw new IllegalArgumentException("Invalid or unsupported image file");
        }
    }

    public String getCompletion(String text) {
        GeminiResponse response = getCompletion(new GeminiRequest(
                List.of(new Content(List.of(new TextPart(text))))));
        return response.candidates().getFirst().content().parts().getFirst().text();
    }

    public String getCompletionWithImage(String prompt, MultipartFile imageFile) throws IOException {
        byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
        String contentType = imageFile.getContentType();
        if (contentType != null && contentType.startsWith("image/")) {
            // If the content type is available and starts with "image/"
            GeminiResponse response = getCompletionWithImage(
                    new GeminiRequest(List.of(new Content(List.of(
                            new TextPart(prompt),
                            new InlineDataPart(new InlineData(contentType,
                                    Base64.getEncoder().encodeToString(imageBytes)))
                    )))));
            // System.out.println(response);
            return response.candidates().getFirst().content().parts().getFirst().text();
        } else {
            // Handle the case where the content type is not recognized
            throw new IllegalArgumentException("Invalid or unsupported image file");
        }
    }
}