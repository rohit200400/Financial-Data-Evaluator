package financial.planner.GPT_insights_async.repository;

import financial.planner.GPT_insights_async.entity.InvestmentInsights;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface InsightRepository extends MongoRepository<InvestmentInsights, String> {
    List<InvestmentInsights> getInsightByUserId(String userId);
}
