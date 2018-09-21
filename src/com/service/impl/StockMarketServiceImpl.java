package com.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import com.constants.StockMarketConstantsIF;
import com.dao.inter.StockMarketDAOIF;
import com.helper.FormulaHelper;
import com.model.CategoryInfo;
import com.model.HashTagsVO;
import com.model.KeywordInfo;
import com.model.PaginationConfigVO;
import com.model.PolarityModel;
import com.model.PolarityOrderInfo;
import com.model.CompanyInfo;
import com.model.CompanyModel;
import com.model.CompanyRankInfo;
import com.model.CompanyTypeInfo;
import com.model.PredictionValue;
import com.model.ReviewDetailModel;
import com.model.ReviewModel;
import com.model.ReviewModelObj;
import com.model.SentimentIndexModel;
import com.model.StatusInfo;
import com.model.StockDataProVO;
import com.model.TotalPolarityModel;
import com.model.FinanceProVO;
import com.service.inter.StockMarketServiceIF;
import com.model.TweetInfo;
import com.noisecleaner.NoiseCleaner;

public class StockMarketServiceImpl implements StockMarketServiceIF {

	@Autowired
	private StockMarketDAOIF stockMarketDao;

	public StockMarketDAOIF getStockMarketDao() {
		return stockMarketDao;
	}

	public void setStockMarketDao(StockMarketDAOIF stockMarketDao) {
		this.stockMarketDao = stockMarketDao;
	}

	@Override
	public StatusInfo storeReviews(ReviewModel reviewModel) {

		StatusInfo statusInfo = null;
		try {

			statusInfo = stockMarketDao.insertReview(reviewModel);

		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

	public String getTextDivData(String url) {

		StringBuilder stringBuilder = new StringBuilder();
		try {
			Document doc;
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("div");
			for (Element link : links) {

				String linkText = link.text();
				stringBuilder.append(linkText);
				stringBuilder.append(StockMarketConstantsIF.CONSTANTS.SPACE);
			}
		} catch (Exception e) {
			System.out.println("EXCEPTION--->" + e);
		}
		return stringBuilder.toString();

	}

	@Override
	public List<ReviewDetailModel> obtainAllReviews() {

		List<ReviewDetailModel> reviewModelList = null;
		try {

			List<ReviewModel> reviewModelListTemp = stockMarketDao.retriveAllReviews();
			if (null == reviewModelListTemp || reviewModelListTemp.isEmpty() || reviewModelListTemp.size() == 0) {

				return null;

			}
			reviewModelList = new ArrayList<ReviewDetailModel>();
			for (ReviewModel reviewModelTemp : reviewModelListTemp) {

				CompanyTypeInfo companyTypeInfo = stockMarketDao
						.retriveSpecificCompanyTypeName(reviewModelTemp.getCompanyType());

				String companyName = stockMarketDao.retriveCompanyNameForId(reviewModelTemp.getCompanyId());

				ReviewDetailModel reviewDetailModel = new ReviewDetailModel();

				reviewDetailModel.setTweetId(reviewModelTemp.getTweetId());

				reviewDetailModel.setCompanyType(companyTypeInfo.getCompanyTypeId());
				reviewDetailModel.setCompanyName(companyName);

				reviewDetailModel.setCompanyId(reviewModelTemp.getCompanyId());
				reviewDetailModel.setReviewDetails(reviewModelTemp.getReviewDetails());

				reviewDetailModel.setHashTag(reviewModelTemp.getHashTag());

				reviewDetailModel.setLanguage(reviewModelTemp.getLanguage());

				reviewDetailModel.setTweetScreenName(reviewModelTemp.getTweetScreenName());

				reviewDetailModel.setUserId(reviewModelTemp.getUserId());

				reviewModelList.add(reviewDetailModel);

			}

			return reviewModelList;

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());

		}
		return reviewModelList;

	}

	@Override
	public List<CompanyTypeInfo> retriveAllCompanyTypes() {

		List<CompanyTypeInfo> collegeTypeInfos = null;
		try {
			return stockMarketDao.retriveAllCompanyTypes();
		} catch (Exception e) {
			return collegeTypeInfos;
		}

	}

	@Override
	public List<CompanyInfo> retriveSpecficCompanysInfo(int companyType) {

		List<CompanyInfo> collegeTypeInfos = null;
		try {
			return stockMarketDao.retriveSpecficCompanyInfo(companyType);
		} catch (Exception e) {
			return collegeTypeInfos;
		}

	}

	@Override
	public StatusInfo insertNegativeKeyword(String keywordV) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			List<String> keyWordList = stockMarketDao.retriveNegativeKeywordsOnly();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = stockMarketDao.insertNegativeKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}
			if (keyWordList.contains(keywordV)) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.NEGATIVE_KEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = stockMarketDao.insertNegativeKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public StatusInfo insertPositiveKeyword(String keywordV) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = stockMarketDao.retrivePositiveKeywordsOnly();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = stockMarketDao.insertPositiveKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}

			if (keyWordList.contains(keywordV)) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.POSITIVE_KEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = stockMarketDao.insertPositiveKeywords(keywordV);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public List<KeywordInfo> retriveNegativeKeywords() {
		List<KeywordInfo> negativeKeyList = null;
		try {
			negativeKeyList = stockMarketDao.retriveNegativeKeywords();
			if (null == negativeKeyList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return negativeKeyList;
	}

	@Override
	public List<KeywordInfo> retrivePositiveKeywords() {
		List<KeywordInfo> positiveKeyList = null;
		try {
			positiveKeyList = stockMarketDao.retrivePositiveKeywords();
			if (null == positiveKeyList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return positiveKeyList;
	}

	@Override
	public List<CompanyModel> rankPolarity(int type) {
		List<CompanyModel> companyModelList = null;

		try {

			// Clear All Polarities

			StatusInfo statusInfo = stockMarketDao.deleteSentimentAnalyzer(type);

			if (!statusInfo.isStatus()) {
				return null;
			}

			// Clear All Polarities

			statusInfo = stockMarketDao.deleteTotalPolarity(type);

			if (!statusInfo.isStatus()) {
				return null;
			}

			List<ReviewModelObj> reviewModelObjList = retriveReviewList(type);

			if (null == reviewModelObjList || reviewModelObjList.isEmpty() || reviewModelObjList.size() == 0) {
				return null;
			}

			List<String> positiveKeyWordList = stockMarketDao.retrivePositiveKeywordsOnly();

			if (null == positiveKeyWordList) {
				return null;
			}

			List<String> negativeKeyWordList = stockMarketDao.retriveNegativeKeywordsOnly();

			List<String> featureTypeList = stockMarketDao.retriveFeatureTypes();

			if (null == negativeKeyWordList) {
				return null;
			}

			for (ReviewModelObj revModelObj : reviewModelObjList) {

				String reviewDetails = revModelObj.getReviewDetails();

				String[] reviewArray = reviewDetails.split("\\.");

				int positivePolarity = 0;

				int negativePolarity = 0;

				int neutralPolarity = 0;

				if (null == reviewArray || reviewArray.length == 0) {
					reviewArray[0] = reviewDetails;
				}

				for (String featureType : featureTypeList) {

					for (String pWord : positiveKeyWordList) {

						for (String reviewStatement : reviewArray) {

							String[] value = reviewStatement.split(" ");

							if (value != null) {

								for (String value1 : value) {

									if (value1 != null && !value1.isEmpty()) {
										value1 = value1.toUpperCase();
									}

									if (value1.contains(featureType)) {
										if (reviewStatement.contains(pWord)) {
											positivePolarity = positivePolarity + 1;
										}
									}
								}
							}
						}
					}

					for (String nWord : negativeKeyWordList) {

						for (String reviewStatement : reviewArray) {
							String[] value = reviewStatement.split(" ");

							if (value != null) {
								for (String value1 : value) {

									if (value1 != null && !value1.isEmpty()) {
										value1 = value1.toUpperCase();
									}

									if (value1.equalsIgnoreCase(featureType)) {

										if (reviewStatement.contains(nWord)) {
											negativePolarity = negativePolarity + 1;
										}
									}
								}
							}
						}

					}

					if (positivePolarity == 0 && negativePolarity == 0) {
						neutralPolarity = neutralPolarity + 1;
					}

					// Creating an Object of Polarity and Inserting

					PolarityModel polarityModel = new PolarityModel();

					polarityModel.setReviewId(revModelObj.getReviewId());
					polarityModel.setPositiveRating(positivePolarity);
					polarityModel.setNegativeRating(negativePolarity);
					polarityModel.setNeutralRating(neutralPolarity);
					polarityModel.setCompanyId(revModelObj.getCompanyId());
					polarityModel.setCompanyType(revModelObj.getCompanyType());
					polarityModel.setFeatureType(featureType);
					// Now Do an Insertion

					statusInfo = stockMarketDao.insertPolarity(polarityModel);

					if (!statusInfo.isStatus()) {
						return null;
					}

				}

			}

			// RETRIVE SENTIMENT ANALYZER

			List<Integer> companyIdList = stockMarketDao.retriveUniqueCompanysIdsFromSentimentAnalyzer(type);

			if (null == companyIdList || companyIdList.isEmpty() || companyIdList.size() == 0) {

				return null;
			}

			for (Integer companyId : companyIdList) {

				for (String featureType : featureTypeList) {
					int totalPositivePolarity = stockMarketDao
							.retriveTotalPositiveRatingForCompanyAndFeatureType(companyId, type, featureType);

					if (totalPositivePolarity < 0) {
						return null;
					}

					int totalNegativePolarity = stockMarketDao
							.retriveTotalNegativeRatingForCompanyAndFeatureType(companyId, type, featureType);

					if (totalNegativePolarity < 0) {
						return null;
					}

					int totalNeutralPolarity = stockMarketDao
							.retriveTotalNeutralRatingForCompanyAndFeatureType(companyId, type, featureType);

					if (totalNeutralPolarity < 0) {
						return null;
					}

					int featureInfo = stockMarketDao.retriveTotalFeatureForCompany(companyId, type, featureType);

					TotalPolarityModel totalPolarityModel = new TotalPolarityModel();
					totalPolarityModel.setNegativeRating(totalNegativePolarity);
					totalPolarityModel.setPositiveRating(totalPositivePolarity);
					totalPolarityModel.setNeutralRating(totalNeutralPolarity);
					totalPolarityModel.setCompanyId(companyId);
					totalPolarityModel.setCompanyType(type);
					totalPolarityModel.setFeatureType(featureType);
					totalPolarityModel.setTotalFeature(featureInfo);
					statusInfo = stockMarketDao.insertTotalPolarity(totalPolarityModel);

					if (!statusInfo.isStatus()) {
						return null;
					}

				}

			}

			// Now DO a ORDER CLAUSE

			List<Integer> companyIds = stockMarketDao.retriveCompanysIdsIDSORderBy(type);

			if (null == companyIds) {
				return null;
			} else {
				companyModelList = new LinkedList<CompanyModel>();
				for (Integer companyId : companyIds) {
					String companyName = stockMarketDao.retriveCompanyNameForId(companyId);
					CompanyModel companyModel = new CompanyModel();
					companyModel.setCompanyId(companyId);
					companyModel.setCompanyName(companyName);
					companyModel.setCompanyType(type);
					companyModelList.add(companyModel);
				}
				return companyModelList;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION:" + e.getMessage());
			return null;
		}
	}

	private List<ReviewModelObj> retriveReviewList(int type) {
		List<ReviewModelObj> reviewModelList = null;
		try {
			List<ReviewModelObj> reviewModel = stockMarketDao.retriveReviewList(type);

			if (null == reviewModel) {
				return null;
			} else {
				reviewModelList = new ArrayList<ReviewModelObj>();

				for (ReviewModelObj revModelTemp : reviewModel) {
					ReviewModelObj reviewModelObj = new ReviewModelObj();
					reviewModelObj.setCompanyId(revModelTemp.getCompanyId());
					reviewModelObj.setCompanyType(revModelTemp.getCompanyType());
					reviewModelObj.setReviewId(revModelTemp.getReviewId());
					String reviewDetails = convertFromBlobToString(revModelTemp.getReviewDetailsBlob());
					reviewModelObj.setReviewDetails(reviewDetails);
					reviewModelList.add(reviewModelObj);
				}

				return reviewModelList;

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
	}

	private String convertFromBlobToString(Blob blob) {
		System.out.println("BLOB---" + blob);

		byte[] bdata = null;
		try {
			bdata = blob.getBytes(1, (int) blob.length());

			System.out.println("BDATA" + bdata);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
		}
		String text = new String(bdata);
		System.out.println("TEXT---" + text);
		return text;
	}

	@Override
	public List<PolarityModel> retrivePolarity(int type) {
		List<PolarityModel> polarityList = null;
		try {
			polarityList = stockMarketDao.retrivePolarity(type);
			if (null == polarityList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return polarityList;

	}

	@Override
	public List<PolarityModel> retriveTotalPolarity(int type) {
		List<PolarityModel> polarityList = null;
		try {
			polarityList = stockMarketDao.retriveTotalPolarity(type);
			if (null == polarityList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return polarityList;
	}

	private Blob covertFromStringToBlob(String reviewDetails) {

		byte[] byteContent = reviewDetails.getBytes();
		Blob blob = null;
		try {
			blob = new SerialBlob(byteContent);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}

	@Override
	public List<CompanyModel> retriveFVForCompanyType(int type) {
		List<CompanyModel> productTypeInfos = null;
		try {
			return stockMarketDao.retriveFVForCompanyType(type);
		} catch (Exception e) {
			return productTypeInfos;
		}
	}

	private CompanyRankInfo obtainCollegeRankInfoSingleFeature(int type, String tokensToSearch) {

		CompanyRankInfo productRankInfo = new CompanyRankInfo();

		try {
			List<String> obtainFeatures = new ArrayList<String>();

			obtainFeatures.add(tokensToSearch);

			if (obtainFeatures != null && !obtainFeatures.isEmpty()) {

				int totalPositive = 0;
				int totalNegative = 0;
				int totalNeutral = 0;
				int totalFeature = 0;

				List<Integer> companyIdList = stockMarketDao.retriveDistinctCompanyIdsFromReviews(type);

				for (Integer companyIdTemp : companyIdList) {

					for (String featureType : obtainFeatures) {

						int positive = stockMarketDao.retriveTotalNeutralRatingForCompanyAndFeatureType(companyIdTemp,
								type, featureType);

						totalPositive = totalPositive + positive;

						int negative = stockMarketDao.retriveTotalNegativeRatingForCompanyAndFeatureType(companyIdTemp,
								type, featureType);

						totalNegative = totalNegative + negative;

						int neutral = stockMarketDao.retriveTotalNeutralRatingForCompanyAndFeatureType(companyIdTemp,
								type, featureType);

						totalNeutral = totalNeutral + neutral;

						int feature = stockMarketDao.retriveTotalFeatureForCompany(companyIdTemp, type, featureType);

						totalFeature = totalFeature + feature;
					}

					// Now insert into Database

					populateObjectAndStore(totalPositive, totalNegative, totalNeutral, totalFeature, companyIdTemp);

				}

				List<PolarityOrderInfo> polarityOrderInfos = stockMarketDao.retriveAllPolarityOrderInfoRankBy();

				List<PolarityOrderInfo> polarityOrderInfosListDisplay = new LinkedList<PolarityOrderInfo>();

				if (polarityOrderInfos != null && !polarityOrderInfos.isEmpty()) {

					for (PolarityOrderInfo polarityOrderInfo : polarityOrderInfos) {

						PolarityOrderInfo polarityOrderInfoNew = populatePolarityOrderInfo(polarityOrderInfo);

						polarityOrderInfosListDisplay.add(polarityOrderInfoNew);

					}

				}

				productRankInfo.setBasedOnFeature(true);

				productRankInfo.setPolarityOrderInfos(polarityOrderInfosListDisplay);

			}
		} catch (Exception e) {

			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();

		}

		return productRankInfo;
	}

	private CompanyRankInfo obtainCompanyRankInfo(int type, String tokensToSearch[]) {

		CompanyRankInfo collegeRankInfo = new CompanyRankInfo();

		try {
			List<String> obtainFeatures = new ArrayList<String>();

			for (int i = 0; i < tokensToSearch.length; i++) {

				obtainFeatures.add(tokensToSearch[i]);
			}
			if (obtainFeatures != null && !obtainFeatures.isEmpty()) {

				int totalPositive = 0;
				int totalNegative = 0;
				int totalNeutral = 0;
				int totalFeature = 0;

				List<Integer> companyIds = stockMarketDao.retriveDistinctCompanyIdsFromReviews(type);

				for (Integer companyIdTemp : companyIds) {

					for (String featureType : obtainFeatures) {

						int positive = stockMarketDao.retriveTotalNeutralRatingForCompanyAndFeatureType(companyIdTemp,
								type, featureType);

						totalPositive = totalPositive + positive;

						int negative = stockMarketDao.retriveTotalNegativeRatingForCompanyAndFeatureType(companyIdTemp,
								type, featureType);

						totalNegative = totalNegative + negative;

						int neutral = stockMarketDao.retriveTotalNeutralRatingForCompanyAndFeatureType(companyIdTemp,
								type, featureType);

						totalNeutral = totalNeutral + neutral;

						int feature = stockMarketDao.retriveTotalFeatureForCompany(companyIdTemp, type, featureType);

						totalFeature = totalFeature + feature;
					}

					// Now insert into Database

					populateObjectAndStore(totalPositive, totalNegative, totalNeutral, totalFeature, companyIdTemp);

				}

				List<PolarityOrderInfo> polarityOrderInfos = stockMarketDao.retriveAllPolarityOrderInfoRankBy();

				List<PolarityOrderInfo> polarityOrderInfosListDisplay = new LinkedList<PolarityOrderInfo>();

				if (polarityOrderInfos != null && !polarityOrderInfos.isEmpty()) {

					for (PolarityOrderInfo polarityOrderInfo : polarityOrderInfos) {

						PolarityOrderInfo polarityOrderInfoNew = populatePolarityOrderInfo(polarityOrderInfo);

						polarityOrderInfosListDisplay.add(polarityOrderInfoNew);

					}

				}

				collegeRankInfo.setBasedOnFeature(true);

				collegeRankInfo.setPolarityOrderInfos(polarityOrderInfosListDisplay);

			}
		} catch (Exception e) {

			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();

		}

		return collegeRankInfo;
	}

	private PolarityOrderInfo populatePolarityOrderInfo(PolarityOrderInfo polarityOrderInfo) {
		PolarityOrderInfo polarityOrderInfoNew = new PolarityOrderInfo();

		polarityOrderInfoNew.setCompanyId(polarityOrderInfo.getCompanyId());
		polarityOrderInfoNew.setTotalFeature(polarityOrderInfo.getTotalFeature());
		polarityOrderInfoNew.setTotalNegative(polarityOrderInfo.getTotalNegative());
		polarityOrderInfoNew.setTotalNeutral(polarityOrderInfo.getTotalNeutral());
		polarityOrderInfoNew.setTotalPositive(polarityOrderInfo.getTotalPositive());
		polarityOrderInfoNew.setType(polarityOrderInfo.getType());

		String companyName = stockMarketDao.retriveCompanyNameForId(polarityOrderInfo.getCompanyId());

		polarityOrderInfoNew.setCompanyName(companyName);

		return polarityOrderInfoNew;
	}

	private void populateObjectAndStore(int totalPositive, int totalNegative, int totalNeutral, int totalFeature,
			Integer companyIdTemp) {
		PolarityOrderInfo polarityOrderInfo = new PolarityOrderInfo();

		polarityOrderInfo.setCompanyId(companyIdTemp);
		polarityOrderInfo.setTotalFeature(totalFeature);
		polarityOrderInfo.setTotalNegative(totalNegative);
		polarityOrderInfo.setTotalPositive(totalPositive);
		polarityOrderInfo.setTotalNeutral(totalNeutral);

		StatusInfo statusInfo2 = stockMarketDao.insertPolarityOrderInfo(polarityOrderInfo);
	}

	private List<String> obtainFeaturesBasedOnQuery(String[] tokensToSearch) {

		List<String> obtainFeatures = new ArrayList<String>();
		try {

			List<String> list = stockMarketDao.retriveFeatureTypes();

			if (tokensToSearch != null) {

				for (int i = 0; i < tokensToSearch.length; i++) {

					String token = tokensToSearch[i];

					if (list.contains(token)) {
						obtainFeatures.add(token);
					}

				}
			}

		} catch (Exception e) {

		}

		return obtainFeatures;

	}

	private boolean checkTokensHasFeatures(String[] tokensToSearch) {
		boolean contains = false;

		List<String> list = stockMarketDao.retriveFeatureTypes();

		if (tokensToSearch != null) {

			for (int i = 0; i < tokensToSearch.length; i++) {

				String token = tokensToSearch[i];

				if (list.contains(token)) {
					contains = true;
				}

			}
		}

		return contains;

	}

	private int checkReviewDetailsCount(String reviewDetails, String word) {

		int count = 0;

		String words[] = reviewDetails.split(" ");

		for (String wordTemp : words) {
			if (wordTemp.equalsIgnoreCase(word)) {
				count = count + 1;
			}
		}

		return count;
	}

	@Override
	public StatusInfo removePositiveKeyword(String stopWord) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = stockMarketDao.removePositiveKeywordOnly();

			if (!keyWordList.contains(stopWord)) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.NO_POSITIVEKEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = stockMarketDao.removePositiveKeyword(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public StatusInfo removeNegativeKeyword(String stopWord) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = stockMarketDao.removeNegativeKeywordOnly();

			if (!keyWordList.contains(stopWord)) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.NO_NEGATIVEKEYWORD_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = stockMarketDao.removeNegativeKeyword(stopWord);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}
	}

	@Override
	public List<PolarityModel> viewTotalPolarityByType(String type) {
		List<PolarityModel> polarityList = null;
		try {
			polarityList = stockMarketDao.viewTotalPolarityByType(type);
			if (null == polarityList) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return polarityList;
	}

	@Override
	public StatusInfo addHashTag(String hashTag, String collegeId) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();

			List<String> keyWordList = stockMarketDao.viewHashTags();

			if (null == keyWordList || keyWordList.isEmpty()) {
				statusInfo = stockMarketDao.insertHashTag(hashTag, collegeId);
				statusInfo.setStatus(true);
				return statusInfo;
			}

			if (keyWordList.contains(hashTag)) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.HASHTAG_EXIST);
				statusInfo.setStatus(false);
				return statusInfo;
			} else {
				statusInfo = stockMarketDao.insertHashTag(hashTag, collegeId);
				statusInfo.setStatus(true);
				return statusInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}

	}

	@Override
	public List<HashTagsVO> viewHashTags() {
		List<HashTagsVO> hashT = null;
		try {
			hashT = stockMarketDao.retriveHashTags();
			if (null == hashT) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return hashT;
	}

	@Override
	public StatusInfo retriveTweetsAndStore() {

		StatusInfo statusInfo = new StatusInfo();
		try {

			List<String> hashtags = stockMarketDao.viewHashTags();

			if (null == hashtags || hashtags.isEmpty()) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.HASHTAGS_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			// gets Twitter instance with default credentials
			/*
			 * Twitter twitter = new TwitterFactory().getInstance();
			 * twitter.setOAuthConsumer(
			 * TwitterConstants.TweetOAuthConstants.OAUTHKEY1,
			 * TwitterConstants.TweetOAuthConstants.OAUTHKEY2);
			 * twitter.setOAuthAccessToken(new AccessToken(
			 * TwitterConstants.TweetOAuthConstants.APIKEY,
			 * TwitterConstants.TweetOAuthConstants.SECRETKEY));
			 */

			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer("GXvmth0FawnOZS1g8JlrWK54Q", "wnR1pVIsOaPnLzJZLi2cYUD505SHkHp7AzXHIJGYxlIJzi468A");
			twitter.setOAuthAccessToken(new AccessToken("1048908211-qD3Dxr5hk0hZEQC7jqUkNStc04C3F1XtjcJhjVH",
					"3btbAZ2Pd4UCSE1mlE1i46NqkOsydK5UBnAxHGs67jXns"));

			// User user = twitter.verifyCredentials();
			List<TweetInfo> tweetInfoList = new ArrayList<TweetInfo>();
			for (String hashTag : hashtags) {
				Query query = new Query(hashTag);
				query.count(StockMarketConstantsIF.TweetOAuthConstants.TWEETS_NOS);
				QueryResult result = twitter.search(query);

				List<Status> statusList = result.getTweets();
				if (statusList != null) {
					for (Status status : statusList) {

						TweetInfo tweetInfo = new TweetInfo();
						if (checkValidTweet(status)) {
							tweetInfo.setHashTag(hashTag);
							tweetInfo.setLanguage(status.getLang());
							tweetInfo.setScreenName(status.getUser().getScreenName());
							tweetInfo.setTweetDesc(status.getText());

							StringBuffer buffer = new StringBuffer();
							buffer.append(tweetInfo.getTweetDesc());
							if (tweetInfo.getTweetDesc() != null) {

								List<CategoryInfo> keywords = stockMarketDao.retriveAllCategory();

								if (keywords != null) {

									int size = keywords.size();

									for (int i = 0; i < 5; i++) {
										int randInt = new Random().nextInt(size);

										if (randInt < size) {
											String word = keywords.get(randInt).getCatWord();

											buffer.append(" ");
											buffer.append(word);

										}
									}

									tweetInfo.setTweetDesc(buffer.toString());
								}

							}

							tweetInfo.setUserId((status.getUser().getName()));
						}
						tweetInfoList.add(tweetInfo);
					}
				}
			}

			statusInfo = stockMarketDao.insertBlockTweetInfo(tweetInfoList);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
		}
		// TODO Auto-generated method stub
		return statusInfo;
	}

	private boolean checkValidTweet(Status status) {
		return status.getText() != null && (status.getUser() != null && status.getUser().getName() != null)
				&& status.getLang() != null && (status.getUser() != null && status.getUser().getScreenName() != null);
	}

	@Override
	public StatusInfo retriveTweetsForAllUsers() {
		StatusInfo statusInfo = new StatusInfo();
		try {

			List<HashTagsVO> hashtags = stockMarketDao.retriveHashTags();

			if (null == hashtags || hashtags.isEmpty()) {
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.HASHTAGS_EMPTY);
				statusInfo.setStatus(false);
				return statusInfo;
			}

			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(StockMarketConstantsIF.Keys.CONSUMER_KEY,
					StockMarketConstantsIF.Keys.CONSUMER_TOKEN);
			twitter.setOAuthAccessToken(
					new AccessToken(StockMarketConstantsIF.Keys.OAUTH_KEY, StockMarketConstantsIF.Keys.OAUTH_TOKEN));

			// User user = twitter.verifyCredentials();
			List<TweetInfo> tweetInfoList = new ArrayList<TweetInfo>();
			NoiseCleaner noiseCleaner = new NoiseCleaner();
			for (HashTagsVO hashTagsVOTemp : hashtags) {
				String hashTag = hashTagsVOTemp.getHashtag();
				Query query = new Query(hashTag);
				query.count(StockMarketConstantsIF.TweetOAuthConstants.TWEETS_NOS);
				QueryResult result = twitter.search(query);

				List<Status> statusList = result.getTweets();
				if (statusList != null) {
					for (Status status : statusList) {

						TweetInfo tweetInfo = new TweetInfo();
						if (checkValidTweet(status) && status.getLang() != null && status.getLang().equals("en")) {
							tweetInfo.setHashTag(hashTag);
							tweetInfo.setLanguage(status.getLang());
							tweetInfo.setScreenName(status.getUser().getScreenName());
							tweetInfo.setTweetDesc(status.getText());

							tweetInfo.setCompanyId(hashTagsVOTemp.getCompanyId());

							StringBuffer buffer = new StringBuffer();

							String cleanedTweet = noiseCleaner.performCleaningOfData(tweetInfo.getTweetDesc());

							buffer.append(cleanedTweet);

							tweetInfo.setTweetDesc(buffer.toString());

							tweetInfo.setUserId((status.getUser().getName()));
						}

						if (tweetInfo != null && tweetInfo.getTweetDesc() != null
								&& !tweetInfo.getTweetDesc().isEmpty()) {

							tweetInfoList.add(tweetInfo);
						}
					}
				}
			}

			statusInfo = stockMarketDao.insertBlockTweetInfo(tweetInfoList);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
		}
		return statusInfo;
	}

	@Override
	public StatusInfo performSentiments() {

		StatusInfo statusInfo = new StatusInfo();
		try {

			statusInfo = stockMarketDao.deleteSentimentIndexInfo();

			if (!statusInfo.isStatus()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.DELETE_SENTIMENT_FAILED);
				return statusInfo;
			}

			List<Integer> companyIds = stockMarketDao.retriveDistinctCompanyIdsFromTotalPolarity();

			if (null == companyIds || companyIds.isEmpty()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.PLEASE_PERFORM_SENTIMENT_ANALYSIS);
				return statusInfo;

			}

			List<SentimentIndexModel> sentimentIndexModelList = new ArrayList<SentimentIndexModel>();

			FormulaHelper formulaHelper = new FormulaHelper();

			for (Integer companyId : companyIds) {

				int totalPositivePolarity = stockMarketDao
						.retriveTotalPositiveRatingForCompanyFromSentimentTotal(companyId);

				int totalNegativePolarity = stockMarketDao
						.retriveTotalNegativeRatingForCompanyFromSentimentTotal(companyId);

				SentimentIndexModel sentimentIndexModel = new SentimentIndexModel();

				String company = stockMarketDao.retriveCompanyNameForId(companyId);

				sentimentIndexModel.setCompany(company);
				sentimentIndexModel.setCompanyId(companyId);
				sentimentIndexModel.setNegativeRating(totalNegativePolarity);
				sentimentIndexModel.setPositiveRating(totalPositivePolarity);

				double sentimentIndex = formulaHelper.computeSentimentIndex(totalPositivePolarity,
						totalNegativePolarity);

				sentimentIndexModel.setSentimentIndex(sentimentIndex);

				double sentimentDescIndex = formulaHelper.computeSentimentDiscIndex(totalPositivePolarity,
						totalNegativePolarity);
				sentimentIndexModel.setSentimentDescIndex(sentimentDescIndex);

				sentimentIndexModelList.add(sentimentIndexModel);

			}

			if (sentimentIndexModelList != null && !sentimentIndexModelList.isEmpty()) {

				statusInfo = stockMarketDao.insertSentimentIndex(sentimentIndexModelList);

				if (!statusInfo.isStatus()) {

					statusInfo.setStatus(false);
					statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_STORE_SENTIMENT_INDEX);

					return statusInfo;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}

		statusInfo.setStatus(true);
		return statusInfo;
	}

	@Override
	public List<SentimentIndexModel> retriveSentimentIndexModelList() {
		List<SentimentIndexModel> sentimentIndexModelList = null;
		try {
			sentimentIndexModelList = stockMarketDao.retriveSentimentIndexModelList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return sentimentIndexModelList;
	}

	@Override
	public StatusInfo performPrediction() {

		StatusInfo statusInfo = new StatusInfo();
		try {

			statusInfo = stockMarketDao.deleteFromPrediction();

			List<StockDataProVO> stockDataList = stockMarketDao.retriveStockMarket();

			if (null == stockDataList || stockDataList.isEmpty()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_FIND_STOCKDATA);

				return statusInfo;
			}

			List<SentimentIndexModel> sentimentIndexList = stockMarketDao.retriveSentimentIndexModelList();

			if (null == sentimentIndexList || sentimentIndexList.isEmpty()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_FIND_SENINDEX);

				return statusInfo;
			}

			List<Integer> companyIdsStock = new ArrayList<Integer>();

			for (StockDataProVO stockData : stockDataList) {
				companyIdsStock.add(stockData.getCompanyId());
			}

			List<Integer> companyIdsSentimentIndexList = new ArrayList<Integer>();

			for (SentimentIndexModel sentimentIndex : sentimentIndexList) {
				companyIdsSentimentIndexList.add(sentimentIndex.getCompanyId());
			}

			// Now Find the Intersection
			companyIdsStock.retainAll(companyIdsSentimentIndexList);

			double totalValueVolume = 0;

			double totalValueMI = 0;

			double totalValuePrice = 0;

			double totalValueTurnOverRate = 0;

			if (null == companyIdsStock || companyIdsStock.isEmpty()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_FIND_COMMON_COMPANYIDS);

				return statusInfo;
			}

			Map<Integer, StockDataProVO> stockDataMap = new HashMap<Integer, StockDataProVO>();
			Map<Integer, SentimentIndexModel> sentIndexMap = new HashMap<Integer, SentimentIndexModel>();

			for (StockDataProVO stockData : stockDataList) {
				stockDataMap.put(stockData.getCompanyId(), stockData);

				totalValueVolume = totalValueVolume + stockData.getVolume();
				totalValueMI = totalValueMI + stockData.getMarketIndex();
				totalValuePrice = totalValuePrice + stockData.getPrice();
				totalValueTurnOverRate = totalValueTurnOverRate + stockData.getDailyTurnOver();

			}

			for (SentimentIndexModel sentIndex : sentimentIndexList) {
				sentIndexMap.put(sentIndex.getCompanyId(), sentIndex);
			}

			List<PredictionValue> predictionValues = new ArrayList<PredictionValue>();

			FormulaHelper formulaHelper = new FormulaHelper();

			for (Integer companyId : companyIdsStock) {

				PredictionValue predictionValue = new PredictionValue();

				predictionValue.setCompanyId(companyId);

				StockDataProVO stockData = stockDataMap.get(companyId);

				predictionValue
						.setDailyTurnOver((double) stockData.getDailyTurnOver() / (double) totalValueTurnOverRate);
				predictionValue.setMarketIndex((double) stockData.getMarketIndex() / (double) totalValueMI);
				predictionValue.setPrice((double) stockData.getPrice() / (double) totalValuePrice);
				predictionValue.setVolume((double) stockData.getVolume() / (double) totalValueVolume);

				predictionValue.setBeta(0.5);

				SentimentIndexModel sentimentIndexModel = sentIndexMap.get(companyId);
				predictionValue.setSentimentDescIndex(sentimentIndexModel.getSentimentDescIndex());

				predictionValue.setCompany(sentimentIndexModel.getCompany());

				double prediction = formulaHelper.computePrediction(predictionValue);

				predictionValue.setPredictionValue(prediction);

				predictionValues.add(predictionValue);

			}

			if (predictionValues != null && !predictionValues.isEmpty()) {

				statusInfo = stockMarketDao.insertPredictionList(predictionValues);

				if (!statusInfo.isStatus()) {

					statusInfo.setStatus(false);
					statusInfo.setErrMsg(StockMarketConstantsIF.Message.INSERT_PREDICTION_FAILED);
					return statusInfo;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
		statusInfo.setStatus(true);
		return statusInfo;
	}

	@Override
	public List<PredictionValue> retrivePredictionValueList() {
		List<PredictionValue> predictionValueList = null;
		try {
			predictionValueList = stockMarketDao.retrivePredictionValueList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return predictionValueList;
	}

	@Override
	public StatusInfo retriveFromFinance(FinanceProVO financeVO) {

		StatusInfo statusInfo = new StatusInfo();
		try {

			int companyId = financeVO.getCompanyId();

			statusInfo = stockMarketDao.checkCompanyExistInStockData(companyId);

			if (statusInfo.isStatus()) {

				statusInfo = stockMarketDao.deleteFromStockData(companyId);

				if (!statusInfo.isStatus()) {

					statusInfo.setStatus(false);
					statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_REMOVE_OLDSTOCKDATA);
					return statusInfo;
				}

			}

			String yahooKey = stockMarketDao.retriveFinanceKeyForCompanyId(companyId);

			if (null == yahooKey || yahooKey.isEmpty()) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.FINANCE_KEY_DOES_NOT_EXIST);
				return statusInfo;
			}

			StockDataProVO stockData = obtainStockDataFromFinance(companyId, yahooKey);

			if (!stockData.isSucess()) {

				statusInfo.setStatus(false);
				if (stockData.getError() != null) {
					statusInfo.setErrMsg(stockData.getError());
				} else {
					statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_RETRIVE_DATA_FROM_FINANCE_API);
				}

				return statusInfo;
			}

			statusInfo = stockMarketDao.insertStockData(stockData);

			if (!statusInfo.isStatus()) {

				statusInfo.setStatus(false);
				statusInfo.setErrMsg(StockMarketConstantsIF.Message.COULD_NOT_SAVE_FINANCE_DATA);
				return statusInfo;

			}

		} catch (Exception e) {

			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			return statusInfo;
		}

		statusInfo.setStatus(true);

		return statusInfo;
	}

	private StockDataProVO obtainStockDataFromFinance(int companyId, String companyTickerKey) throws IOException {

		StockDataProVO stockDataProVO = new StockDataProVO();

		// Using the Finance Real API to get the Data

		String apiKey = StockMarketConstantsIF.Keys.FINANCE_API_KEY;
		int timeout = 3000;
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
		TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

		try {

			IntraDay response = stockTimeSeries.intraDay(companyTickerKey, Interval.ONE_MIN, OutputSize.COMPACT);

			double open = 0;

			double high = 0;

			double low = 0;

			double close = 0;

			double volume = 0;

			List<StockData> stockData = response.getStockData();

			int noOfRecords = 0;

			for (StockData stock : stockData) {
				noOfRecords = noOfRecords + 1;
				open = open + stock.getOpen();
				high = high + stock.getHigh();
				low = low + stock.getLow();
				close = close + stock.getClose();
				volume = volume + stock.getVolume();
			}

			if (open > 0) {
				open = ((double) open) / ((double) noOfRecords);
			}

			if (high > 0) {
				high = ((double) high) / ((double) noOfRecords);
			}

			if (low > 0) {
				low = ((double) low) / ((double) noOfRecords);
			}

			if (close > 0) {
				close = ((double) close) / ((double) noOfRecords);
			}

			if (volume > 0) {
				volume = ((double) volume) / ((double) noOfRecords);
			}

			stockDataProVO.setCompanyId(companyId);
			stockDataProVO.setVolume(volume);
			stockDataProVO.setPrice(close);
			stockDataProVO.setDailyTurnOver(Math.abs(open - close));
			stockDataProVO.setMarketIndex(Math.abs(high - low));

		} catch (AlphaVantageException e) {
			System.out.println("something went wrong");
			stockDataProVO.setSucess(false);
			stockDataProVO.setError(e.getMessage());
			return stockDataProVO;
		}
		stockDataProVO.setSucess(true);

		return stockDataProVO;
	}

	@Override
	public List<StockDataProVO> retriveStockDataList() {
		List<StockDataProVO> stockDataList = null;
		try {
			stockDataList = stockMarketDao.retriveStockDataList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			return null;
		}
		return stockDataList;
	}

	@Override
	public List<CompanyInfo> retriveSpecficCompanysInfoFromFinance() {
		List<CompanyInfo> companyInformationList = null;
		try {
			return stockMarketDao.retriveSpecficCompanyInfoFromFinance();
		} catch (Exception e) {
			return companyInformationList;
		}
	}

	@Override
	public StatusInfo retriveReviewsForPagination(PaginationConfigVO paginationConfigVO) {
		StatusInfo stat = new StatusInfo();

		try {

			stat = stockMarketDao.retriveAllReviewsForPaginationConfig(paginationConfigVO);

		} catch (Exception e) {
			System.out.println("Exception e" + e.getMessage());
			stat.setStatus(false);
			stat.setExceptionMsg(e.getMessage());

			return stat;

		}

		stat.setStatus(true);
		return stat;

	}

}
