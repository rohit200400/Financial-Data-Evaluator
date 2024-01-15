//package financial.planner.GPT_insights_async.controller;
//
//import financial.planner.GPT_insights_async.dto.GPTRequest;
//import financial.planner.GPT_insights_async.exceeption.ExternalApiException;
//import financial.planner.GPT_insights_async.service.ApiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/api")
//public class ApiController {
//
////    @Value("${external.api.model}")
////    private String model;
//    @Autowired
//    private ApiService apiService;
//
//    @GetMapping("/callExternalApiAsync")
//    public ResponseEntity<Mono<String>> callExternalApiAsync(@RequestParam String prompt) {
//        GPTRequest request = new GPTRequest("model", prompt);
//        // Delegate the asynchronous API call to the service
//        Mono<String> contentMono = apiService.callExternalApiAsync(request);
//
//        // Subscribe to the Mono to initiate the asynchronous API call
//        contentMono.subscribe(content -> {
//            // Handle the content of the first choice here
//            System.out.println("Content of the first choice: " + content);
//
//        }, error -> {
//            if (error instanceof ExternalApiException) {
//                // Handle ExternalApiException
//                System.err.println("External API error: " + error.getMessage());
//            } else {
//                // Handle other types of errors
//                System.err.println("Unexpected error: " + error.getMessage());
//            }
//        });
//        return ResponseEntity.ok(contentMono);
//    }
//}
