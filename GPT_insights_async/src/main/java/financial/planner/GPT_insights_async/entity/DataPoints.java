package financial.planner.GPT_insights_async.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class DataPoints {

    private Map<String, String> keyValuePairs;

    public void addKeyValuePair(String key, String value) {
        this.keyValuePairs.put(key, value);
    }

    public String getValueByKey(String key) {
        return this.keyValuePairs.get(key);
    }

}
