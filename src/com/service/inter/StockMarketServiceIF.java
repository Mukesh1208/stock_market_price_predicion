package com.service.inter;

import java.util.List;

import com.model.ActivationProbabilityVO;
import com.model.CategoryCountVO;
import com.model.ClassifierInfo;
import com.model.CleanReviewUIModel;
import com.model.EnhanceContigency;
import com.model.FeatureVO;
import com.model.FrequencyVO;
import com.model.HashTagsVO;
import com.model.KeywordInfo;
import com.model.PaginationConfigVO;
import com.model.PolarityModel;
import com.model.CompanyInfo;
import com.model.CompanyModel;
import com.model.CompanyRankInfo;
import com.model.CompanyTypeInfo;
import com.model.PredictionValue;
import com.model.RankCompany;
import com.model.ReviewDetailModel;
import com.model.ReviewModel;
import com.model.SentimentIndexModel;
import com.model.StatusInfo;
import com.model.StockDataProVO;
import com.model.StopWordsVO;
import com.model.TokenVO;
import com.model.FinanceProVO;

public interface StockMarketServiceIF {

	public StatusInfo storeReviews(ReviewModel reviewModel);

	public List<ReviewDetailModel> obtainAllReviews();

	public StatusInfo insertNegativeKeyword(String keywordV);

	public StatusInfo insertPositiveKeyword(String keywordV);

	public List<KeywordInfo> retriveNegativeKeywords();

	public List<KeywordInfo> retrivePositiveKeywords();

	public List<CompanyModel> rankPolarity(int type);

	public List<PolarityModel> retrivePolarity(int type);

	public List<PolarityModel> retriveTotalPolarity(int type);

	public StatusInfo removePositiveKeyword(String stopWord);

	public StatusInfo removeNegativeKeyword(String stopWord);

	public List<PolarityModel> viewTotalPolarityByType(String type);

	public StatusInfo addHashTag(String hashTag, String collegeId);

	public List<HashTagsVO> viewHashTags();

	StatusInfo retriveTweetsAndStore();

	public StatusInfo retriveTweetsForAllUsers();

	List<CompanyInfo> retriveSpecficCompanysInfo(int companyType);

	List<CompanyTypeInfo> retriveAllCompanyTypes();

	List<CompanyModel> retriveFVForCompanyType(int type);

	public StatusInfo performSentiments();

	public List<SentimentIndexModel> retriveSentimentIndexModelList();

	public StatusInfo performPrediction();
 
	public List<PredictionValue> retrivePredictionValueList();

	
	public List<StockDataProVO> retriveStockDataList();


	public List<CompanyInfo> retriveSpecficCompanysInfoFromFinance();

	public StatusInfo retriveFromFinance(FinanceProVO yahooFinanceVO);

	public StatusInfo retriveReviewsForPagination(PaginationConfigVO paginationConfigVO);

}
