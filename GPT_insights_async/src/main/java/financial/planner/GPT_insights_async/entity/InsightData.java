package financial.planner.GPT_insights_async.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsightData {
    private String description;
    private DataPoints dataPoints;
}
