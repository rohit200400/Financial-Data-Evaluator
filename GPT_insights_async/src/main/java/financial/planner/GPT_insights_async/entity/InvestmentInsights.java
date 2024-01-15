package financial.planner.GPT_insights_async.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "InvestmentInsights")
public class InvestmentInsights {

    @Id
    private String id;
    private String userId;
    private String insightType;
    private InsightData insightData;
    private String createdAt;
}
