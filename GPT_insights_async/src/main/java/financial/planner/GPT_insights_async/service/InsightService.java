package financial.planner.GPT_insights_async.service;


import financial.planner.GPT_insights_async.entity.InvestmentInsights;

import java.util.List;

public interface InsightService {
    InvestmentInsights getInsightById(String id);

    List<InvestmentInsights> getInsightByUserId(String userId);

    InvestmentInsights addInsight(InvestmentInsights insight);
}
