package com.controller.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.AJAXResponse;
import com.model.CompanyInfo;
import com.model.HashTagInput;
import com.model.KeywordInfo;
import com.model.PaginationConfigVO;
import com.model.CompanyTypeInfo;
import com.model.FinanceProVO;

public interface StockMarketControllerIF {

	
	public ModelAndView addPositiveKeyword(HttpServletRequest request,
			@ModelAttribute KeywordInfo keyword);

	public ModelAndView addNegativeKeyword(HttpServletRequest request,
			@ModelAttribute KeywordInfo keyword);

	public @ResponseBody AJAXResponse viewPositiveKeywords(
			HttpServletRequest request);

	public @ResponseBody AJAXResponse viewNegativeKeywords(
			HttpServletRequest request);

	public ModelAndView rankBasedOnPolarity(HttpServletRequest request,
			@RequestParam String type);
	
	public ModelAndView computeTheSentimentIndex(HttpServletRequest request);

	public @ResponseBody AJAXResponse viewPolarity(HttpServletRequest request,
			String type);

	public @ResponseBody AJAXResponse viewTotalPolarity(
			HttpServletRequest request, String type);

	public ModelAndView removePositiveKeyword(String stopWord);

	public ModelAndView removeNegativeKeyword(String stopWord);

	AJAXResponse viewTotalPolarityByType(HttpServletRequest request, String type);

	AJAXResponse viewHashTags(HttpServletRequest request);

	ModelAndView retriveTweetsForAllUsers();

	AJAXResponse addHashTag(HashTagInput hashTagInput);

	List<CompanyTypeInfo> retriveAllCompanyTypes();

	List<CompanyInfo> retriveSpecficCompanysInfo(String type);

	AJAXResponse viewSentimentIndex(HttpServletRequest request);

	ModelAndView performPrediction(HttpServletRequest request);

	AJAXResponse viewPrediction(HttpServletRequest request);

	
	AJAXResponse viewStockMarket(HttpServletRequest request);

	List<CompanyInfo> retriveSpecficCompanysInfoFromFinance();

	AJAXResponse performFinance(FinanceProVO yahooFinanceVO);

	AJAXResponse obtainAllReviews(PaginationConfigVO paginationConfigVO);

}
