package financial.planner.GPT_insights_async.controller;

import financial.planner.GPT_insights_async.gemini.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

//    @PostMapping("/completion")
//    public String getCompletion(@RequestBody GeminiRequest request) {
//        GeminiResponse response = geminiService.getCompletion(request);
//        return response.candidates().getFirst().content().parts().getFirst().text();
//    }
//
//    @PostMapping("/completionWithImage")
//    public String getCompletionWithImage(@RequestBody GeminiRequest request) {
//        GeminiResponse response = geminiService.getCompletionWithImage(request);
//        return response.candidates().getFirst().content().parts().getFirst().text();
//    }

    @GetMapping("/analyzeData")
    public String analyzeData(@RequestParam String text,
                              @RequestParam Map<String, String> userPreferences,
                              @RequestParam("file") MultipartFile file) {
        return geminiService.analyzeData(text, userPreferences, file);
    }

    @GetMapping("/completion")
    public String getCompletion(@RequestParam String text) {
        return geminiService.getCompletion(text);
    }

    @GetMapping("/analyzeImage")
    public String analyzeImage(@RequestParam String text,
                               @RequestParam Map<String, String> userPreferences,
                               @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        return geminiService.analyzeDataWithImage(text, userPreferences, imageFile);
    }
}
