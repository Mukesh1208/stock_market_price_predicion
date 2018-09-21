package com.dao.inter;

import java.util.List;

import com.model.CategoryInfo;
import com.model.CompanyTypeInfo;
import com.model.HashTagsVO;
import com.model.KeywordInfo;
import com.model.PaginationConfigVO;
import com.model.PolarityModel;
import com.model.PolarityOrderInfo;
import com.model.CompanyInfo;
import com.model.CompanyModel;
import com.model.PredictionValue;
import com.model.ReviewModel;
import com.model.ReviewModelObj;
import com.model.SentimentIndexModel;
import com.model.StatusInfo;
import com.model.StockDataProVO;
import com.model.TotalPolarityModel;
import com.model.TweetInfo;

public interface StockMarketDAOIF {

	public StatusInfo insertReview(ReviewModel reviewModel);

	public List<ReviewModel> retriveAllReviews();

	public List<CompanyInfo> retriveAllCompanyInfo();

	public List<CompanyInfo> retriveSpecficCompanyInfo(int productType);

	public List<CompanyTypeInfo> retriveAllCompanyTypes();

	public CompanyTypeInfo retriveSpecificCompanyTypeName(int productType);

	public String retriveCompanyNameForId(int productId);

	public List<KeywordInfo> retrivePositiveKeywords();

	public List<KeywordInfo> retriveNegativeKeywords();

	public List<String> retriveNegativeKeywordsOnly();

	public StatusInfo insertNegativeKeywords(String keywordV);

	public List<String> retrivePositiveKeywordsOnly();

	public StatusInfo insertPositiveKeywords(String keywordV);

	public StatusInfo deleteSentimentAnalyzer(int type);

	public StatusInfo deleteTotalPolarity(int type);

	public List<ReviewModelObj> retriveReviewList(int type);

	public StatusInfo insertPolarity(PolarityModel polarityModel);

	public List<Integer> retriveUniqueCompanysIdsFromSentimentAnalyzer(int type);

	public int retriveTotalPositiveRatingForCompany(Integer CompanyIdTemp, int productType);

	public int retriveTotalNegativeRatingForCompany(Integer productIdTemp, int productType);

	public int retriveTotalNeutralRatingForCompany(Integer productIdTemp, int type);

	public StatusInfo insertTotalPolarity(TotalPolarityModel totalPolarityModel);

	public List<Integer> retriveCompanysIdsIDSORderBy(int type);

	public List<PolarityModel> retrivePolarity(int type);

	public List<PolarityModel> retriveTotalPolarity(int type);

	public List<Integer> retriveDistinctCompanyIdsFromFV(int type);

	public List<Integer> retriveDistinctReviewIDSForCompanyIdsFromFV(Integer productIdTemp, int type);

	public List<CompanyModel> retriveFVForCompanyType(int type);

	public List<Integer> retriveDistinctCompanyIdsFromReviews(int type);

	public List<String> retriveFeatureTypes();

	public List<String> removePositiveKeywordOnly();

	public StatusInfo removePositiveKeyword(String stopWord);

	public List<String> removeNegativeKeywordOnly();

	public StatusInfo removeNegativeKeyword(String stopWord);

	public int retriveTotalPositiveRatingForCompanyAndFeatureType(Integer CompanyIdTemp, int type, String featureType);

	public int retriveTotalNegativeRatingForCompanyAndFeatureType(Integer CompanyIdTemp, int type, String featureType);

	public int retriveTotalNeutralRatingForCompanyAndFeatureType(Integer CompanyIdTemp, int type, String featureType);

	public int retriveTotalFeatureForCompany(Integer CompanyIdTemp, int type, String featureType);

	public StatusInfo insertPolarityOrderInfo(PolarityOrderInfo polarityOrderInfo);

	List<PolarityOrderInfo> retriveAllPolarityOrderInfoRankBy();

	public List<PolarityModel> viewTotalPolarityByType(String type);

	List<HashTagsVO> retriveHashTags();

	StatusInfo insertHashTag(String hashTag, String productId);

	public List<String> viewHashTags();

	public List<CategoryInfo> retriveAllCategory();

	public StatusInfo insertBlockTweetInfo(List<TweetInfo> tweetInfoList);

	public StatusInfo deletePolarityOrder();

	public StatusInfo deleteSentimentIndexInfo();

	public List<Integer> retriveDistinctCompanyIdsFromTotalPolarity();

	public int retriveTotalPositiveRatingForCompanyFromSentimentTotal(Integer companyId);

	public int retriveTotalNegativeRatingForCompanyFromSentimentTotal(Integer companyId);

	public StatusInfo insertSentimentIndex(List<SentimentIndexModel> sentimentIndexModelList);

	public List<SentimentIndexModel> retriveSentimentIndexModelList();

	public List<StockDataProVO> retriveStockMarket();

	public StatusInfo deleteFromPrediction();

	public StatusInfo insertPredictionList(List<PredictionValue> predictionValues);

	public List<PredictionValue> retrivePredictionValueList();

	public List<StockDataProVO> retriveStockDataList();

	public StatusInfo checkCompanyExistInStockData(int companyId);

	public StatusInfo deleteFromStockData(int companyId);

	public StatusInfo insertStockData(StockDataProVO stockData);

	public List<CompanyInfo> retriveSpecficCompanyInfoFromFinance();

	public String retriveFinanceKeyForCompanyId(int companyId);

	public StatusInfo retriveAllReviewsForPaginationConfig(PaginationConfigVO paginationConfigVO);

}
