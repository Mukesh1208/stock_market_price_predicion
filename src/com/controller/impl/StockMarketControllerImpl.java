package com.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.constants.StockMarketConstantsIF;
import com.controller.inter.StockMarketControllerIF;
import com.delegate.inter.StockMarketDelegateIF;
import com.model.AJAXResponse;
import com.model.HashTagInput;
import com.model.HashTagsVO;
import com.model.KeywordInfo;
import com.model.Message;
import com.model.PaginationConfigVO;
import com.model.PolarityModel;
import com.model.CompanyInfo;
import com.model.CompanyModel;
import com.model.CompanyTypeInfo;
import com.model.PredictionValue;
import com.model.ReviewDetailModel;
import com.model.SentimentIndexModel;
import com.model.StatusInfo;
import com.model.StockDataProVO;
import com.model.FinanceProVO;

@Controller
public class StockMarketControllerImpl implements StockMarketControllerIF {

	@Autowired
	private StockMarketDelegateIF stockMarketDelegate;

	public StockMarketDelegateIF getStockMarketDelegate() {
		return stockMarketDelegate;
	}

	public void setStockMarketDelegate(StockMarketDelegateIF stockMarketDelegate) {
		this.stockMarketDelegate = stockMarketDelegate;
	}

	@Override
	@RequestMapping(value = "/retriveAllReviews.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse obtainAllReviews(@ModelAttribute PaginationConfigVO paginationConfigVO) {
		AJAXResponse ajaxRes = null;

		try {
			ajaxRes = new AJAXResponse();

			StatusInfo statusInfo = stockMarketDelegate
					.retriveReviewsForPagination(paginationConfigVO);


			if (statusInfo.isStatus()) {
				ajaxRes.setModel(statusInfo.getModel());
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(StockMarketConstantsIF.Message.REVIEW_RETRIVE_SUCESSFUL);
				ajaxRes.setTotal(statusInfo.getTotal());
				return ajaxRes;
			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.NO_REVIEWS_FOUND);
				ebErrors.add(msg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.MESSAGE_INTERNAL);
			ebErrors.add(msg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;
		}
	}

	@Override
	@RequestMapping(value = "/retriveAllCompaniesForCompanyType.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<CompanyInfo> retriveSpecficCompanysInfo(
			@RequestParam String type) {

		List<CompanyInfo> productInfoList = null;
		try {

			productInfoList = stockMarketDelegate
					.retriveSpecficCompanysInfo(Integer.parseInt(type));

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return productInfoList;

	}
	
	@Override
	@RequestMapping(value = "/retriveAllCompaniesForFinance.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<CompanyInfo> retriveSpecficCompanysInfoFromFinance() {

		List<CompanyInfo> productInfoList = null;
		try {

			productInfoList = stockMarketDelegate
					.retriveSpecficCompanysInfoFromFinance();

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return productInfoList;

	}

	@Override
	@RequestMapping(value = "/retriveAllCompanyTypes.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody List<CompanyTypeInfo> retriveAllCompanyTypes() {
		List<CompanyTypeInfo> collegeTypeInfoList = null;
		try {

			collegeTypeInfoList = stockMarketDelegate.retriveAllCompanyTypes();

		} catch (Exception e) {
			System.out.println("EXCEPTION IS" + e.getMessage());
		}

		return collegeTypeInfoList;
	}

	@Override
	@RequestMapping(value = "/addNegativeKeyword.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addNegativeKeyword(HttpServletRequest request,
			@ModelAttribute KeywordInfo keyword) {
		try {

			String keywordV = keyword.getKeyword();
			if (null == keywordV || keywordV.isEmpty()
					|| keywordV.trim().length() == 0) {
				AJAXResponse ajax = new AJAXResponse();
				ajax.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.KEYWORD_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajax.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.ADD_NEGATIVEKEYWORD_VIEW,
						StockMarketConstantsIF.Keys.OBJ, ajax);
			}

			StatusInfo statusInfo = stockMarketDelegate
					.insertNegativeKeyword(keywordV);

			if (!statusInfo.isStatus()) {
				AJAXResponse ajax = new AJAXResponse();
				ajax.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.KEYWORD_STORAGE_FAILED);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajax.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.ADD_NEGATIVEKEYWORD_VIEW,
						StockMarketConstantsIF.Keys.OBJ, ajax);
			}

			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.ADD_NEGATIVEKEYWORD_SUCESS);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_ADMIN_SUCESS_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.ADD_NEGATIVEKEYWORD_VIEW,
					StockMarketConstantsIF.Keys.OBJ, ajax);
		}
	}

	@Override
	@RequestMapping(value = "/addPositiveKeyword.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addPositiveKeyword(HttpServletRequest request,
			@ModelAttribute KeywordInfo keyword) {
		try {

			String keywordV = keyword.getKeyword();
			if (null == keywordV || keywordV.isEmpty()
					|| keywordV.trim().length() == 0) {
				AJAXResponse ajax = new AJAXResponse();
				ajax.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.KEYWORD_EMPTY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajax.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.ADD_POSITIVEKEYWORD_VIEW,
						StockMarketConstantsIF.Keys.OBJ, ajax);
			}

			StatusInfo statusInfo = stockMarketDelegate
					.insertPositiveKeyword(keywordV);

			if (!statusInfo.isStatus()) {
				AJAXResponse ajax = new AJAXResponse();
				ajax.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.KEYWORD_STORAGE_FAILED);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajax.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.ADD_POSITIVEKEYWORD_VIEW,
						StockMarketConstantsIF.Keys.OBJ, ajax);
			}

			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.ADD_POSITIVEKEYWORD_SUCESS);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_ADMIN_SUCESS_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.ADD_POSITIVEKEYWORD_VIEW,
					StockMarketConstantsIF.Keys.OBJ, ajax);
		}
	}

	@Override
	@RequestMapping(value = "/viewNegativeKeywords.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewNegativeKeywords(
			HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<KeywordInfo> keyWordList = stockMarketDelegate
					.retriveNegativeKeywords();
			if (null == keyWordList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_NEGATIVEKEYWORDS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(keyWordList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.NEGATIVE_KEYWORDS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/viewPositiveKeywords.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewPositiveKeywords(
			HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<KeywordInfo> keyWordList = stockMarketDelegate
					.retrivePositiveKeywords();
			if (null == keyWordList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_POSITIVEKEYWORDS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(keyWordList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.POSITIVE_KEYWORDS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/rankPolarity.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView rankBasedOnPolarity(HttpServletRequest request,
			@RequestParam String type) {

		try {

			System.out.println("TYPE" + type);
			List<CompanyModel> productUIModel = stockMarketDelegate
					.rankPolarity(Integer.parseInt(type));
			if (null == productUIModel) {
				AJAXResponse ajax = new AJAXResponse();
				ajax.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.RANKING_FAILED_POLARITY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajax.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.VIEW_ADMIN_ERROR_PAGE,
						StockMarketConstantsIF.Keys.OBJ, ajax);
			}

			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.RANK_POLARITY_SUCESS);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			ajax.setModel(productUIModel);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_POLARITY_PACK,
					StockMarketConstantsIF.Keys.OBJ, ajax);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_ADMIN_ERROR_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);
		}

	}

	@Override
	@RequestMapping(value = "/viewPolarity.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewPolarity(HttpServletRequest request,
			@RequestParam String type) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			System.out.println("type" + type);

			List<PolarityModel> polarityList = stockMarketDelegate
					.retrivePolarity(Integer.parseInt(type));
			if (null == polarityList || polarityList.isEmpty()) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_POLARITY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(polarityList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.POLARITY_KEYWORDS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/viewTotalPolarity.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewTotalPolarity(
			HttpServletRequest request, @RequestParam String type) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			System.out.println("type" + type);

			List<PolarityModel> polarityList = stockMarketDelegate
					.retriveTotalPolarity(Integer.parseInt(type));
			if (null == polarityList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_POLARITY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(polarityList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.POLARITY_KEYWORDS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/viewTotalPolarityForType.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewTotalPolarityByType(
			HttpServletRequest request, @RequestParam String type) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			System.out.println("type" + type);

			List<PolarityModel> polarityList = stockMarketDelegate
					.viewTotalPolarityByType((type));
			if (null == polarityList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_POLARITY);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(polarityList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.POLARITY_KEYWORDS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/removePositiveKeyword.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView removePositiveKeyword(
			@RequestParam String positivekeyword) {
		ModelAndView mv = null;
		AJAXResponse ajaxRes = null;
		String stopWord = positivekeyword;
		try {
			ajaxRes = new AJAXResponse();
			if (null == stopWord || stopWord.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.EMPTY_POSITIVEKEYWORD);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.REMOVESTOPWORD_INPUT,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = stockMarketDelegate
					.removePositiveKeyword(stopWord);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.POSITIVEKEYWORD_REMOVE_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.REMOVEPOSITIVEKEYWORD_INPUT,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.KEYWORD_REMOVE_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.VIEW_ADMIN_SUCESS_PAGE,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.REMOVEPOSITIVEKEYWORD_INPUT,
					StockMarketConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/removeNegativeKeyword.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView removeNegativeKeyword(
			@RequestParam String negativekeyword) {
		ModelAndView mv = null;
		AJAXResponse ajaxRes = null;
		String stopWord = negativekeyword;
		try {
			ajaxRes = new AJAXResponse();
			if (null == stopWord || stopWord.isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.EMPTY_NEGATIVEKEYWORD);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.REMOVENEGATIVEKEYWORD_INPUT,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);

			}
			StatusInfo statusInfo = stockMarketDelegate
					.removeNegativeKeyword(stopWord);
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.NEGATIVEKEYWORD_REMOVE_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.REMOVENEGATIVEKEYWORD_INPUT,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.KEYWORD_REMOVE_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.VIEW_ADMIN_SUCESS_PAGE,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.REMOVENEGATIVEKEYWORD_INPUT,
					StockMarketConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/addHashTag.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse addHashTag(
			@RequestBody HashTagInput hashTagInput) {
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			if (null == hashTagInput.getHashtag()
					|| hashTagInput.getHashtag().isEmpty()) {
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.EMPTY_HASHTAG);
				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;
			}

			StatusInfo statusInfo = stockMarketDelegate.addHashTag(
					hashTagInput.getHashtag(), hashTagInput.getCompanyId());
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message statusMessage = new Message();

				if (statusInfo.getErrMsg() != null) {
					statusMessage.setMsg(statusInfo.getErrMsg());
					ebErrors.add(statusMessage);
				} else if (statusInfo.getExceptionMsg() != null) {
					statusMessage.setMsg(statusInfo.getExceptionMsg());
					ebErrors.add(statusMessage);
				} else {
					statusMessage
							.setMsg(StockMarketConstantsIF.Message.HASHTAG_ADD_FAILED);
					ebErrors.add(statusMessage);
				}

				ajaxRes.setEbErrors(ebErrors);
				return ajaxRes;

			} else {
				ajaxRes = new AJAXResponse();
				ajaxRes.setStatus(true);
				ajaxRes.setMessage(StockMarketConstantsIF.Message.HASHTAG_ADD_SUCESS);
				return ajaxRes;
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return ajaxRes;

		}
	}

	@Override
	@RequestMapping(value = "/viewHashTags.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse viewHashTags(HttpServletRequest request) {
		AJAXResponse ajaxResponse;
		try {
			ajaxResponse = new AJAXResponse();

			List<HashTagsVO> keyWordList = stockMarketDelegate.viewHashTags();
			if (null == keyWordList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_HASHTAGS);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(keyWordList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.HASHTAGS_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/retriveTweets.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView retriveTweetsForAllUsers() {
		ModelAndView mv = null;
		AJAXResponse ajaxRes = null;
		try {
			ajaxRes = new AJAXResponse();
			StatusInfo statusInfo = stockMarketDelegate
					.retriveTweetsForAllUsers();
			if (!statusInfo.isStatus()) {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.RETRIVE_TWEETS_FAILED);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.RETRIVETWEETS_VIEW,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);

			} else {
				ajaxRes = new AJAXResponse();
				List<Message> ebErrors = new ArrayList<Message>();
				ajaxRes.setStatus(false);
				Message webSiteUrlMsg = new Message();
				webSiteUrlMsg
						.setMsg(StockMarketConstantsIF.Message.RETRIVE_TWEETS_SUCESS);

				ebErrors.add(webSiteUrlMsg);
				ajaxRes.setEbErrors(ebErrors);
				return new ModelAndView(
						StockMarketConstantsIF.Views.VIEW_SUCESS_PAGE,
						StockMarketConstantsIF.Keys.OBJ, ajaxRes);
			}

		} catch (Exception e) {
			ajaxRes = new AJAXResponse();
			List<Message> ebErrors = new ArrayList<Message>();
			ajaxRes.setStatus(false);
			Message webSiteUrlMsg = new Message();
			webSiteUrlMsg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);

			ebErrors.add(webSiteUrlMsg);
			ajaxRes.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.RETRIVETWEETS_VIEW,
					StockMarketConstantsIF.Keys.OBJ, ajaxRes);
		}
	}

	@Override
	@RequestMapping(value = "/performSentiments.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView computeTheSentimentIndex(HttpServletRequest request) {
		try {

			StatusInfo statusInfo = stockMarketDelegate.performSentiments();

			if (!statusInfo.isStatus()) {

				if (statusInfo.getErrMsg() != null) {

					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(statusInfo.getErrMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return new ModelAndView(
							StockMarketConstantsIF.Views.ERROR_VIEW,
							StockMarketConstantsIF.Keys.OBJ, ajax);
				} else if (statusInfo.getExceptionMsg() != null) {
					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(statusInfo.getExceptionMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return new ModelAndView(
							StockMarketConstantsIF.Views.ERROR_VIEW,
							StockMarketConstantsIF.Keys.OBJ, ajax);
				} else {

					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(StockMarketConstantsIF.Message.COULD_NOT_COMPUTE_SENTIMENT_INDEX);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return new ModelAndView(
							StockMarketConstantsIF.Views.ERROR_VIEW,
							StockMarketConstantsIF.Keys.OBJ, ajax);

				}

			}

			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(true);
			ajax.setMessage(StockMarketConstantsIF.Message.SENTIMENTS_COMPUTED_SUCESSFULLY);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_SUCESS_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_ADMIN_ERROR_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);
		}
	}

	@Override
	@RequestMapping(value = "/viewSentimentIndex.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewSentimentIndex(
			HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<SentimentIndexModel> sentimentIndexModelList = stockMarketDelegate
					.retriveSentimentIndexModelList();

			if (null == sentimentIndexModelList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_SENTIMENTINDEX_LIST);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(sentimentIndexModelList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.SENTIMENT_INDEX_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/performPrediction.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public ModelAndView performPrediction(HttpServletRequest request) {
		try {

			StatusInfo statusInfo = stockMarketDelegate.performPrediction();

			if (!statusInfo.isStatus()) {

				if (statusInfo.getErrMsg() != null) {

					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(statusInfo.getErrMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return new ModelAndView(
							StockMarketConstantsIF.Views.ERROR_VIEW,
							StockMarketConstantsIF.Keys.OBJ, ajax);
				} else if (statusInfo.getExceptionMsg() != null) {
					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(statusInfo.getExceptionMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return new ModelAndView(
							StockMarketConstantsIF.Views.ERROR_VIEW,
							StockMarketConstantsIF.Keys.OBJ, ajax);
				} else {

					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(StockMarketConstantsIF.Message.COULD_NOT_COMPUTE_PREDICTION);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return new ModelAndView(
							StockMarketConstantsIF.Views.ERROR_VIEW,
							StockMarketConstantsIF.Keys.OBJ, ajax);

				}

			}

			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(true);
			ajax.setMessage(StockMarketConstantsIF.Message.PREDICTION_COMPUTED_SUCESSFULLY);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_SUCESS_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return new ModelAndView(
					StockMarketConstantsIF.Views.VIEW_ADMIN_ERROR_PAGE,
					StockMarketConstantsIF.Keys.OBJ, ajax);
		}
	}

	@Override
	@RequestMapping(value = "/viewPrediction.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewPrediction(HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<PredictionValue> predictionValueList = stockMarketDelegate
					.retrivePredictionValueList();

			if (null == predictionValueList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_PREDICTION_LIST);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(predictionValueList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.PREDICTION_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

	@Override
	@RequestMapping(value = "/performFinance.do", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody AJAXResponse performFinance(
			@RequestBody FinanceProVO yahooFinanceVO) {
		try {

			

			StatusInfo statusInfo = stockMarketDelegate
					.retriveFromFinance(yahooFinanceVO);

			if (!statusInfo.isStatus()) {

				if (statusInfo.getErrMsg() != null) {

					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(statusInfo.getErrMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return ajax;
				} else if (statusInfo.getExceptionMsg() != null) {
					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(statusInfo.getExceptionMsg());
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return ajax;
				} else {

					AJAXResponse ajax = new AJAXResponse();
					ajax.setStatus(false);
					Message msg = new Message();
					msg.setMsg(StockMarketConstantsIF.Message.COULD_NOT_RETRIVE_YAHOOFINANCE);
					List<Message> ebErrors = new ArrayList<Message>();
					ebErrors.add(msg);
					ajax.setEbErrors(ebErrors);
					return ajax;

				}

			}

			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(true);
			ajax.setMessage(StockMarketConstantsIF.Message.YAHOOFINANCE_COMPUTED_SUCESSFULLY);
			return ajax;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			AJAXResponse ajax = new AJAXResponse();
			ajax.setStatus(false);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajax.setEbErrors(ebErrors);
			return ajax;
		}
	}
	
	@Override
	@RequestMapping(value = "/viewYahoo.do", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody AJAXResponse viewStockMarket(HttpServletRequest request) {
		AJAXResponse ajaxResponse = null;
		try {
			ajaxResponse = new AJAXResponse();

			List<StockDataProVO> stockDataList = stockMarketDelegate
					.retriveStockDataList(); 

			if (null == stockDataList) {
				ajaxResponse = new AJAXResponse();
				ajaxResponse.setStatus(false);
				Message msg = new Message();
				msg.setMsg(StockMarketConstantsIF.Message.EMPTY_STOCKDATA_LIST);
				List<Message> ebErrors = new ArrayList<Message>();
				ebErrors.add(msg);
				ajaxResponse.setEbErrors(ebErrors);
				return ajaxResponse;
			}
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			ajaxResponse.setModel(stockDataList);
			ajaxResponse
					.setMessage(StockMarketConstantsIF.Message.STOCKDATA_SUCESS);
			return ajaxResponse;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			ajaxResponse = new AJAXResponse();
			ajaxResponse.setStatus(true);
			Message msg = new Message();
			msg.setMsg(StockMarketConstantsIF.Message.INTERNAL_ERROR);
			List<Message> ebErrors = new ArrayList<Message>();
			ebErrors.add(msg);
			ajaxResponse.setEbErrors(ebErrors);
			return ajaxResponse;
		}
	}

}
