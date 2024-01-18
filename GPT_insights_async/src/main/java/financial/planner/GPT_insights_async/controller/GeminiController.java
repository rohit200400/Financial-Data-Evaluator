package financial.planner.GPT_insights_async.controller;

import financial.planner.GPT_insights_async.gemini.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/finsight")
public class GeminiController {
    private final GeminiService geminiService;

    @Autowired
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/analyzeData")
    public String analyzeData(@RequestParam String text,
                              @RequestParam Map<String, String> userPreferences,
                              @RequestParam("file") MultipartFile file,
                              @RequestHeader("username") String username) {
        return geminiService.analyzeData(text, userPreferences, file, username);
    }

    @GetMapping("/completion")
    public String getCompletion(@RequestParam String text, @RequestHeader("username") String username) {
        return geminiService.getCompletion(text);
    }

    @PostMapping("/analyzeImage")
    public String analyzeImage(@RequestParam String text,
                               @RequestParam Map<String, String> userPreferences,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               @RequestHeader("username") String username) {
        try {
            return geminiService.analyzeDataWithImage(text, userPreferences, imageFile,username );
        } catch (IOException e) {
            return "Error analyzing image data: " + e.getMessage();
        }
    }
}
