package financial.planner.GPT_insights_async.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "insights")
public class InvestmentInsights {

    @Id
    private String id;
    private String userId;
    private String UserPreferences;
    private String insightData;
    @CreatedDate
    @Field("createdAt")
    private LocalDateTime createdAt;
}
