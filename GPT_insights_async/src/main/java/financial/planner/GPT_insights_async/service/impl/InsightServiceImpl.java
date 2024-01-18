package financial.planner.GPT_insights_async.service.impl;

import financial.planner.GPT_insights_async.entity.InvestmentInsights;
import financial.planner.GPT_insights_async.repository.InsightRepository;
import financial.planner.GPT_insights_async.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsightServiceImpl implements InsightService {

    private final InsightRepository insightRepository;

    @Autowired
    public InsightServiceImpl(InsightRepository insightRepository) {
        this.insightRepository = insightRepository;
    }

    @Override
    public InvestmentInsights getInsightById(String id) {
        Optional<InvestmentInsights> optionalInsight = insightRepository.findById(id);
        return optionalInsight.orElse(null);
    }

    @Override
    public List<InvestmentInsights> getInsightByUserId(String userId) {
        List<InvestmentInsights> usersInsights = insightRepository.getInsightByUserId(userId);
        return usersInsights.isEmpty() ? null : usersInsights;
    }

    @Override
    public InvestmentInsights addInsight(InvestmentInsights insight) {
        // You might want to set the createdAt field before saving, if needed.
        return insightRepository.save(insight);
    }
}
