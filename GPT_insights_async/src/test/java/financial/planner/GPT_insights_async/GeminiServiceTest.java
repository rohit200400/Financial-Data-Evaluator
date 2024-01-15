package financial.planner.GPT_insights_async;

import financial.planner.GPT_insights_async.gemini.GeminiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GeminiServiceTest {
    @Autowired
    private GeminiService service;

    @Test
    void getCompletion_HHGtTG_question() {
        String text = service.getCompletion("""
                What is the Ultimate Answer to
                the Ultimate Question of Life, the Universe,
                and Everything?
                """);
        assertNotNull(text);
        System.out.println(text);
        assertThat(text).contains("42");
    }

    @Test
    void getCompletion() {
        String text = service.getCompletion("""
                How many roads must a man walk down
                before you can call him a man?
                """);
        assertNotNull(text);
        System.out.println(text);
    }

    @Test
    void pirateCoverLetter() {
        String text = service.getCompletion("""
                Please write a cover letter for a Java developer
                applying for an AI position, written in pirate speak.
                """);
        assertNotNull(text);
        System.out.println(text);
    }


    @Test
    void writeAStory() {
        String text = service.getCompletion("Write a story about a magic backpack.");
        assertNotNull(text);
        System.out.println(text);
    }

    @Test
    void analyzeTheData() {
        Map<String, String> userPreferences = new HashMap<>();
        userPreferences.put("Risk Factor", "low");
        userPreferences.put("duration of investment", "long");

        // Mock MultipartFile content
        String content = "Date,Open,High,Low,Close,Volume\n" +
                "2023-01-01,150.25,155.00,149.50,153.75,1000000\n" +
                "2023-02-01,154.00,160.50,153.75,159.25,1200000\n" +
                "2023-03-01,160.75,165.25,159.00,164.50,800000\n" +
                "2023-04-01,163.50,168.75,162.25,167.25,950000\n" +
                "2023-05-01,168.00,172.50,166.75,171.50,1100000\n" +
                "2023-06-01,172.75,178.25,171.50,177.75,850000\n" +
                "2023-07-01,177.00,182.00,175.25,180.25,900000\n" +
                "2023-08-01,179.50,185.00,178.75,183.50,1050000\n" +
                "2023-09-01,183.75,188.50,182.00,187.25,800000\n" +
                "2023-10-01,186.25,192.25,185.75,191.50,1200000\n" +
                "2023-11-01,190.00,195.50,189.75,194.75,1000000\n" +
                "2023-12-01,195.25,200.00,193.50,198.00,1100000";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "filename.txt", "text/plain", content.getBytes());

        String text = service.analyzeData("Given the below data generate insightful content based on user preferences.", userPreferences, multipartFile);
        assertNotNull(text);
        System.out.println(text);
    }

    @Test
    void describeAnImage() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("image", "",
                "image/png", "src/main/resources/image.png".getBytes());

        String text = service.getCompletionWithImage(
                "Do the Financial Analysis by using the data of below image.", imageFile);
        assertNotNull(text);
        System.out.println(text);

    }

}