//package financial.planner.GPT_insights_async.service;
//
//import financial.planner.GPT_insights_async.dto.GPTRequest;
//import financial.planner.GPT_insights_async.dto.GPTResponse;
//import financial.planner.GPT_insights_async.exceeption.ExternalApiException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//
//@Service
//public class ApiService {
//
//    @Value("${external.api.url}")
//    private String externalApiUrl;
//
//
//    @Value("${external.api.key}")
//    private String token;
//
//    @Autowired
//    private WebClient webClient;
//
//
//    public Mono<String> callExternalApiAsync(GPTRequest request) {
//        // Make the external API call asynchronously
//        return webClient.post()
//                .header("Authorization", "Bearer " + token)  // Set the authorization header
//                .bodyValue(request)  // Set the request body
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
//                    // Handle 5xx server errors
//                    return Mono.error(new ExternalApiException("Server error: " + clientResponse.statusCode()));
//                })
//                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
//                    // Handle 4xx client errors
//                    return Mono.error(new ExternalApiException("Client error: " + clientResponse.statusCode()));
//                })
//                .bodyToMono(GPTResponse.class)
//                .flatMap(gptResponse -> {
//                    GPTResponse.Choice firstChoice = gptResponse.getChoices().stream().findFirst().orElse(null);
//                    if (firstChoice != null) {
//                        return Mono.just(firstChoice.getMessage().getContent());
//                    } else {
//                        return Mono.error(new RuntimeException("No choices found in the response."));
//                    }
//                });
//    }
//}
