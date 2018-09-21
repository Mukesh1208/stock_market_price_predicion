package com.delegate.impl;

import java.util.List;

import com.delegate.inter.StockMarketDelegateIF;
import com.model.HashTagsVO;
import com.model.KeywordInfo;
import com.model.PaginationConfigVO;
import com.model.PolarityModel;
import com.model.CompanyInfo;
import com.model.CompanyModel;
import com.model.CompanyTypeInfo;
import com.model.PredictionValue;
import com.model.ReviewDetailModel;
import com.model.ReviewModel;
import com.model.SentimentIndexModel;
import com.model.StatusInfo;
import com.model.StockDataProVO;
import com.model.FinanceProVO;
import com.service.inter.StockMarketServiceIF;

public class StockMarketDelegateImpl implements StockMarketDelegateIF {

	private StockMarketServiceIF stockMarketService;

	public StockMarketServiceIF getStockMarketService() {
		return stockMarketService;
	}

	public void setStockMarketService(StockMarketServiceIF stockMarketService) {
		this.stockMarketService = stockMarketService;
	}

	@Override
	public StatusInfo storeReviews(ReviewModel reviewModel) {
		return stockMarketService.storeReviews(reviewModel);
	}

	@Override
	public List<ReviewDetailModel> retriveReviews() {
		return stockMarketService.obtainAllReviews();
	}

	@Override
	public List<CompanyTypeInfo> retriveAllCompanyTypes() {
		return stockMarketService.retriveAllCompanyTypes();
	}

	@Override
	public List<CompanyInfo> retriveSpecficCompanysInfo(int productType) {
		return stockMarketService.retriveSpecficCompanysInfo(productType);
	}

	@Override
	public StatusInfo insertNegativeKeyword(String keywordV) {
		return stockMarketService.insertNegativeKeyword(keywordV);
	}

	@Override
	public StatusInfo insertPositiveKeyword(String keywordV) {
		return stockMarketService.insertPositiveKeyword(keywordV);
	}

	@Override
	public List<KeywordInfo> retriveNegativeKeywords() {
		return stockMarketService.retriveNegativeKeywords();
	}

	@Override
	public List<KeywordInfo> retrivePositiveKeywords() {
		return stockMarketService.retrivePositiveKeywords();
	}

	@Override
	public List<CompanyModel> rankPolarity(int type) {
		return stockMarketService.rankPolarity(type);
	}

	@Override
	public List<PolarityModel> retrivePolarity(int type) {
		return stockMarketService.retrivePolarity(type);
	}

	@Override
	public List<PolarityModel> retriveTotalPolarity(int type) {
		return stockMarketService.retriveTotalPolarity(type);
	}

	@Override
	public StatusInfo removePositiveKeyword(String stopWord) {
		return stockMarketService.removePositiveKeyword(stopWord);
	}

	@Override
	public StatusInfo removeNegativeKeyword(String stopWord) {
		return stockMarketService.removeNegativeKeyword(stopWord);
	}

	@Override
	public List<PolarityModel> viewTotalPolarityByType(String type) {
		return stockMarketService.viewTotalPolarityByType(type);
	}

	@Override
	public List<HashTagsVO> viewHashTags() {
		return stockMarketService.viewHashTags();

	}

	@Override
	public StatusInfo retriveTweetsForAllUsers() {
		return stockMarketService.retriveTweetsForAllUsers();
	}

	@Override
	public StatusInfo addHashTag(String hashTag, String collegeId) {
		return stockMarketService.addHashTag(hashTag, collegeId);
	}

	@Override
	public StatusInfo performSentiments() {
		return stockMarketService.performSentiments();
	}

	@Override
	public List<SentimentIndexModel> retriveSentimentIndexModelList() {
		return stockMarketService.retriveSentimentIndexModelList();
	}

	@Override
	public StatusInfo performPrediction() {
		return stockMarketService.performPrediction();
	}

	@Override
	public List<PredictionValue> retrivePredictionValueList() {
		return stockMarketService.retrivePredictionValueList();
	}

	@Override
	public StatusInfo retriveFromFinance(FinanceProVO yahooFinanceVO) {
		return stockMarketService.retriveFromFinance(yahooFinanceVO);
	}

	@Override
	public List<StockDataProVO> retriveStockDataList() {
		return stockMarketService.retriveStockDataList();
	}

	@Override
	public List<CompanyInfo> retriveSpecficCompanysInfoFromFinance() {
		return stockMarketService.retriveSpecficCompanysInfoFromFinance();
	}

	@Override
	public StatusInfo retriveReviewsForPagination(PaginationConfigVO paginationConfigVO) {
		return stockMarketService.retriveReviewsForPagination(paginationConfigVO);
	}

	
}
