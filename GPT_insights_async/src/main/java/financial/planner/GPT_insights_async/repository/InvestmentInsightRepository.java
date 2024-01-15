package financial.planner.GPT_insights_async.repository;

import financial.planner.GPT_insights_async.entity.InvestmentInsights;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InvestmentInsightRepository extends ReactiveMongoRepository<InvestmentInsights, String> {

    Flux<InvestmentInsights> findByUserId(String userId);

}
