package financial.planner.GPT_insights_async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GptInsightsAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(GptInsightsAsyncApplication.class, args);
    }

}
