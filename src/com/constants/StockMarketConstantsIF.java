package com.constants;

public interface StockMarketConstantsIF {

	interface SQLS {
		public static final String INSERT_REVIEW_SQL = "INSERT_REVIEW_SQL";
		public static final String RETRIVE_REVIEWS_SQL = "RETRIVE_REVIEWS_SQL";
		public static final String RETRIVE_COMPANYINFO_SQL = "RETRIVE_COMPANYINFO_SQL";
		public static final String RETRIVE_COMPANYINFO_FOR_COMPANYTYPE_SQL = "RETRIVE_COMPANYINFO_FOR_COMPANYTYPE_SQL";
		public static final String RETRIVE_COMPANYTYPEINFO_SQL = "RETRIVE_COMPANYTYPEINFO_SQL";
		public static final String RETRIVE_COMPANYTYPEINFO_FOR_COMPANYTYPEID_SQL = "RETRIVE_COMPANYTYPEINFO_FOR_COMPANYTYPEID_SQL";
		public static final String RETRIVE_COMPANYNAME_FOR_COMPANYID = "RETRIVE_COMPANYNAME_FOR_COMPANYID";
		public static final String RETRIVE_POSITIVE_KEYWORDS_SQL = "RETRIVE_POSITIVE_KEYWORDS_SQL";
		public static final String RETRIVE_NEGATIVE_KEYWORDS_SQL = "RETRIVE_NEGATIVE_KEYWORDS_SQL";
		public static final String INSERT_NEGATIVEKEYWORDS_SQL = "INSERT_NEGATIVEKEYWORDS_SQL";
		public static final String INSERT_POSTIVEKEYWORDS_SQL = "INSERT_POSTIVEKEYWORDS_SQL";
		public static final String RETRIVE_NEGATIVEKEYWORDS_SQL = "RETRIVE_NEGATIVEKEYWORDS_SQL";
		public static final String RETRIVE_POSITIVEKEYWORDS_SQL = "RETRIVE_POSITIVEKEYWORDS_SQL";
		public static final String DELETE_SENTIMENTANALYZER_SQL = "DELETE_SENTIMENTANALYZER_SQL";
		public static final String DELETE_SENTIMENTTOTAL_SQL = "DELETE_SENTIMENTTOTAL_SQL";
		public static final String RETRIVE_ALLREVIEWS_SQL = "RETRIVE_ALLREVIEWS_SQL";
		public static final String INSERT_POLARITY_SQL = "INSERT_POLARITY_SQL";
		public static final String RETRIVE_UNIQUE_COMPANYIDS_SQL = "RETRIVE_UNIQUE_COMPANYIDS_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL";
		public static final String RETRIVE_TOTALNEUTRALPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL = "RETRIVE_TOTALNEUTRALPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL";
		public static final String INSERT_TOTALPOLARITY_SQL = "INSERT_TOTALPOLARITY_SQL";
		public static final String RETRIVE_COMPANYIDS_ORDERBY_WHERE_COMPANYTYPE_SQL = "RETRIVE_COMPANYIDS_ORDERBY_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_ALLPOLARITY_WHERE_COMPANYTYPE_SQL = "RETRIVE_ALLPOLARITY_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_TOTALPOLARITY_WHERE_COMPANYTYPE_SQL = "RETRIVE_TOTALPOLARITY_WHERE_COMPANYTYPE_SQL";
		public static final String INSERT_STOPWORD_SQL = "INSERT_STOPWORD_SQL";
		public static final String RETRIVE_STOPWORDS_FULL_SQL = "RETRIVE_STOPWORDS_FULL_SQL";
		public static final String RETRIVE_STOPWORDS_SQL = "RETRIVE_STOPWORDS_SQL";
		public static final String INSERT_CLEANDETAILS_SQL = "INSERT_CLEANDETAILS_SQL";
		public static final String DELETE_CLEANREVIEWS_SQL = "DELETE_CLEANREVIEWS_SQL";
		public static final String DELETE_ALLCLEANREVIEWS_SQL = "DELETE_ALLCLEANREVIEWS_SQL";
		public static final String RETRIVE_ALLCLEANREVIEWS_WHERE_COMPANYTYPE_SQL = "RETRIVE_ALLCLEANREVIEWS_WHERE_COMPANYTYPE_SQL";
		public static final String INSERT_TOKENS_SQL = "INSERT_TOKENS_SQL";
		public static final String DELETE_ALLTOKENS_WHERE_COMPANYTYPE_SQL = "DELETE_ALLTOKENS_WHERE_COMPANYTYPE_SQL";
		public static final String DELETE_FREQUENCY_WHERE_COMPANYTYPE_SQL = "DELETE_FREQUENCY_WHERE_COMPANYTYPE_SQL";
		public static final String INSERT_FREQUENCY_WHERE_COMPANYTYPE_SQL = "INSERT_FREQUENCY_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_ALLTOKENS_WHERE_COMPANYTYPE_SQL = "RETRIVE_ALLTOKENS_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_REVIEWIDS_WHERE_COMPANYTYPE_SQL = "RETRIVE_REVIEWIDS_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_COUNT_WHERE_TOKENNAME_REVIEWID_COMPANYTYPE_SQL = "RETRIVE_COUNT_WHERE_TOKENNAME_REVIEWID_COMPANYTYPE_SQL";
		public static final String RETRIVE_DISTINCTTOKENS_WHERE_REVIEWID_COMPANYTYPE_SQL = "RETRIVE_DISTINCTTOKENS_WHERE_REVIEWID_COMPANYTYPE_SQL";
		public static final String RETRIVE_COMPANYID_WHERE_REVIEWID_COMPANYTYPE_SQL = "RETRIVE_COMPANYID_WHERE_REVIEWID_COMPANYTYPE_SQL";
		public static final String INSERT_FEATUREVECTOR_SQL = "INSERT_FEATUREVECTOR_SQL";
		public static final String RETRIVE_FEATUREVO_WHERE_TYPE_SQL = "RETRIVE_FEATUREVO_WHERE_TYPE_SQL";
		public static final String DELETE_FV_WHERE_COMPANYTYPE_SQL = "DELETE_FV_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_COUNT_WHERE_COMPANYID_TOKENNAME_COMPANYTYPE_SQL = "RETRIVE_COUNT_WHERE_COMPANYID_TOKENNAME_COMPANYTYPE_SQL";
		public static final String RETRIVE_FREQ_FROM_FREQ_WHERE_REVIEWID_COMPANYID_TOKENNAME_COMPANYTYPE_SQL = "RETRIVE_FREQ_FROM_FREQ_WHERE_REVIEWID_COMPANYID_TOKENNAME_COMPANYTYPE_SQL";
		public static final String RETRIVE_TOKENS_WHERE_REVIEWID_COMPANYTYPE_FROM_FREQ_SQL = "RETRIVE_TOKENS_WHERE_REVIEWID_COMPANYTYPE_FROM_FREQ_SQL";
		public static final String RETRIVE_UNIQUE_COMPANYIDS_FROM_FREQ_WHERE_COMPANYTYPE_SQL = "RETRIVE_UNIQUE_COMPANYIDS_FROM_FREQ_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_UNIQUE_REVIEWIDS_FROM_FREQ_WHERE_COMPANYID_COMPANYTYPE_SQL = "RETRIVE_UNIQUE_REVIEWIDS_FROM_FREQ_WHERE_COMPANYID_COMPANYTYPE_SQL";
		public static final String RETRIVE_ALLFREQUENCY_WHERE_COMPANYTYPE_SQL = "RETRIVE_ALLFREQUENCY_WHERE_COMPANYTYPE_SQL";
		public static final String DELETE_BESTFV_WHERE_COMPANYTYPE_SQL = "DELETE_BESTFV_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_DISTINCTCOMPANYSIDS_FROM_FV_WHERE_COMPANYTYPE_SQL = "RETRIVE_DISTINCTCOMPANYSIDS_FROM_FV_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_DISTINCTREVIEWSIDS_WHERE_COMPANYID_COMPANYTYPE_FROM_FV_SQL = "RETRIVE_DISTINCTREVIEWSIDS_WHERE_COMPANYID_COMPANYTYPE_FROM_FV_SQL";
		public static final String RETRIVE_FV_FROM_FV_WHERE_TOEKNNAME_REVIEWID_COMPANYTYPE_SQL = "RETRIVE_FV_FROM_FV_WHERE_TOEKNNAME_REVIEWID_COMPANYTYPE_SQL";
		public static final String RETRIVE_FREQ_FROM_FV_WHERE_TOKENNAME_REVIEWID_COMPANYTYPE_SQL = "RETRIVE_FREQ_FROM_FV_WHERE_TOKENNAME_REVIEWID_COMPANYTYPE_SQL";
		public static final String INSERT_BESTFV_SQL = "INSERT_BESTFV_SQL";
		public static final String RETRIVE_BESTPRODUCT_WHERE_COMPANYTYPE_SQL = "RETRIVE_BESTPRODUCT_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_COMPANYINFO_WHERE_COMPANYTYPE_SQL = "RETRIVE_COMPANYINFO_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_UNIQUE_COMPANYIDS_FROM_REVIEW_SQL = "RETRIVE_UNIQUE_COMPANYIDS_FROM_REVIEW_SQL";
		public static final String DELETE_FEATUREBASED_FV_WHERE_COMPANYTYPE_SQL = "DELETE_FEATUREBASED_FV_WHERE_COMPANYTYPE_SQL";
		public static final String RETRIVE_FEATURETYPES_SQL = "RETRIVE_FEATURETYPES_SQL";
		public static final String REMOVE_STOPWORD_SQL = "REMOVE_STOPWORD_SQL";
		public static final String RETRIVE_FREQ_WHERE_TOKENNAMELIKE_FEATURETYPE_AND_REVIEWID_AND_COMPANYTYPE_SQL = "RETRIVE_FREQ_WHERE_TOKENNAMELIKE_FEATURETYPE_AND_REVIEWID_AND_COMPANYTYPE_SQL";
		public static final String REMOVE_POSITIVEKEYWORD_SQL = "REMOVE_POSITIVEKEYWORD_SQL";
		public static final String REMOVE_NEGATIVEKEYWORD_SQL = "REMOVE_NEGATIVEKEYWORD_SQL";
		public static final String RETRIVE_POSITIVEKEYWORDS_ONLY_SQL = "RETRIVE_POSITIVEKEYWORDS_ONLY_SQL";
		public static final String RETRIVE_NEGATIVEKEYWORDS_ONLY_SQL = "RETRIVE_NEGATIVEKEYWORDS_ONLY_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL";
		public static final String RETRIVE_TOTALNEUTRALPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL = "RETRIVE_TOTALNEUTRALPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL";
		public static final String RETRIVE_FREQ_FROM_FREQ_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL = "RETRIVE_FREQ_FROM_FREQ_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL";
		public static final String INSERT_POLARITYORDER_SQL = "INSERT_POLARITYORDER_SQL";
		public static final String RETRIVE_POLARITYORDERINFO_RANK_SQL = "RETRIVE_POLARITYORDERINFO_RANK_SQL";
		public static final String RETRIVE_TOTALPOLARITY_WHERE_FEATURETYPE_SQL = "RETRIVE_TOTALPOLARITY_WHERE_FEATURETYPE_SQL";
		public static final String RETRIVE_HASHTAGS_FULL_SQL = "RETRIVE_HASHTAGS_FULL_SQL";
		public static final String INSERT_HASHTAG_SQL = "INSERT_HASHTAG_SQL";
		public static final String RETRIVE_HASHTAGS_SQL = "RETRIVE_HASHTAGS_SQL";
		public static final String RETRIVE_CATINFO_SQL = "RETRIVE_CATINFO_SQL";
		public static final String INSERT_TWEETINFO_SQL = "INSERT_TWEETINFO_SQL";
		public static final String DELETE_RANKCOMPANY_SQL = "DELETE_RANKCOMPANY_SQL";
		public static final String INSERT_RANKCOMPANY_SQL = "INSERT_RANKCOMPANY_SQL";
		public static final String DELETE_POLARITYORDER_SQL = "DELETE_POLARITYORDER_SQL";
		public static final String RETRIVE_RANKCOMPANYS_SQL = "RETRIVE_RANKCOMPANYS_SQL";
		public static final String INSERT_ENHANCE_CONTIGENCY_INFO_SQL = "INSERT_ENHANCE_CONTIGENCY_INFO_SQL";
		public static final String REMOVE_ALL_ENHANCECONTIGENCY_SQL = "REMOVE_ALL_ENHANCECONTIGENCY_SQL";
		public static final String RETRIVE_ENHANCECONTIGENCY_FULL_SQL = "RETRIVE_ENHANCECONTIGENCY_FULL_SQL";
		public static final String RETRIVE_CLASSINFO_FULL_SQL = "RETRIVE_CLASSINFO_FULL_SQL";
		public static final String RETRIVE_CLASSIFYCOUNT_FULL_SQL = "RETRIVE_CLASSIFYCOUNT_FULL_SQL";
		public static final String REMOVE_ALL_CLASSIFIER_SQL = "REMOVE_ALL_CLASSIFIER_SQL";
		public static final String RETRIVE_DISTINCT_TWEETIDS_FROM_ENHANCECONTGENCY_SQL = "RETRIVE_DISTINCT_TWEETIDS_FROM_ENHANCECONTGENCY_SQL";
		public static final String RETRIVE_RANKED_CLASSIFIER_FROM_CONTGENCY_WHERE_TWEETID_SQL = "RETRIVE_RANKED_CLASSIFIER_FROM_CONTGENCY_WHERE_TWEETID_SQL";
		public static final String INSERT_CLASSIFIER_INFO_SQL = "INSERT_CLASSIFIER_INFO_SQL";
		public static final String INSERT_ACTIVATIONPPROBABILITY_SQL = "INSERT_ACTIVATIONPPROBABILITY_SQL";
		public static final String RETRIVE_ACTIVATIONPROBABILITY_SQL = "RETRIVE_ACTIVATIONPROBABILITY_SQL";
		public static final String RETRIVE_USERID_FOR_TWEETID_FROM_REVIEW_SQL = "RETRIVE_USERID_FOR_TWEETID_FROM_REVIEW_SQL";
		public static final String RETRIVE_PRODUCT_FOR_FEATURETYPE_FROM_RANKINGMOBILE_SQL = "RETRIVE_PRODUCT_FOR_FEATURETYPE_FROM_RANKINGMOBILE_SQL";
		public static final String DELETE_GROUPINGUSERS_SQL = "DELETE_GROUPINGUSERS_SQL";
		public static final String RETRIVE_ACTIVATIONPROBABILITY_FEATURE_SQL = "RETRIVE_ACTIVATIONPROBABILITY_FEATURE_SQL";
		public static final String DELETE_SENTIMENTINDEX_SQL = "DELETE_SENTIMENTINDEX_SQL";
		public static final String RETRIVE_DISTINCTCOMPANYSIDS_FROM_TOTALPOLARITY_SQL = "RETRIVE_DISTINCTCOMPANYSIDS_FROM_TOTALPOLARITY_SQL";
		public static final String RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_SQL = "RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_SQL";
		public static final String RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_SQL = "RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_SQL";
		public static final String INSERT_SENTIMENTINDEX_SQL = "INSERT_SENTIMENTINDEX_SQL";
		public static final String RETRIVE_SENTIMENTINDEX_SQL = "RETRIVE_SENTIMENTINDEX_SQL";
		public static final String RETRIVE_STOCKMARKET_SQL = "RETRIVE_STOCKMARKET_SQL";
		public static final String DELETE_PREDICTION_SQL = "DELETE_PREDICTION_SQL";
		public static final String INSERT_PREDICTION_SQL = "INSERT_PREDICTION_SQL";
		public static final String RETRIVE_PREDICTION_SQL = "RETRIVE_PREDICTION_SQL";
		public static final String RETRIVE_STOCKMARKET_WHERE_COMPANYID_SQL = "RETRIVE_STOCKMARKET_WHERE_COMPANYID_SQL";
		public static final String DELETE_STOCKDATA_WHERE_COMPANYID_SQL = "DELETE_STOCKDATA_WHERE_COMPANYID_SQL";
		public static final String INSERT_STOCKDATA_SQL = "INSERT_STOCKDATA_SQL";
		public static final String RETRIVE_COMPANYINFO_FROM_FINANCE_SQL = "RETRIVE_COMPANYINFO_FROM_FINANCE_SQL";
		public static final String RETRIVE_FINANCEKEY_FOR_COMPANYID = "RETRIVE_FINANCEKEY_FOR_COMPANYID";
		public static final String RETRIVE_REVIEWS_PAGINATED_SQL = "RETRIVE_REVIEWS_PAGINATED_SQL";
		public static final String RETRIVE_TOTAL_COUNT_FOR_TWEETS_SQL = "RETRIVE_TOTAL_COUNT_FOR_TWEETS_SQL";   

	}

	interface CONSTANTS {

		public static final String SPACE = "  ";
		public static final String REVIEWDIVID_AMAZON = "div.reviewText";
		public static final String REVIEWDIVID_FLIPKART = "p";
		public static final String REVIEWCLASS_FLIPKART = "line bmargin10";
		public static final String AMAZON = "AMAZON";
		public static final String FLIPKART = "FLIPKART";

	}

	interface Message {

		public static final String REVIEW_FAILED = "Review could not be Stored";
		public static final String MESSAGE_INTERNAL = "An internal has Ocuured. Please Contact System Administrator";
		public static final String NOAMAZON_FLIPKART = "Keyword is neither Amazon nor Flipkart";
		public static final String REVIEW_RETRIVE_SUCESSFUL = "Reviews Retrived Sucessfully";
		public static final String NO_REVIEWS_FOUND = "No Reviews Found at this point of time";
		public static final String INTERNAL_ERROR = "Please Contact System Adminitrator. An Internal Error has Ocuurred";
		public static final String FIRSTNAME_EMPTY = "First Name cannot be Empty";
		public static final String LASTNAME_EMPTY = "Last Name cannot be Empty";
		public static final String USERID_EMPTY = "User ID Cannot be Empty";
		public static final String EMAIL_EMPTY = "Email Cannot be Empty";
		public static final String PASSWORD_EMPTY = "Password Cannot be Empty";
		public static final String USERREGISTERED_SUCESS_MSG = "User Has Registered Sucessfully";
		public static final String USERALREADY_EXIST = "User Already Exist";
		public static final String NO_USER_EXISTS = "No User Already Exist";
		public static final String PASSWORD_WRONG = "Password does not exist";
		public static final String USER_LOGIN_SUCESS = "User Login is Sucessful";
		public static final String ACCNO_EMPTY = "Account No Cannot be Empty";
		public static final String IPIN_EMPTY = "IPIN Cannot be Empty";
		public static final String ACCNOLSIT_EMPTY = "Account No Not Found/List is Empty";
		public static final String INVALID_CREDENTIALS = "Could not Apply the Tourist Package as the Credentails were Invalid";
		public static final String INSUFFICENT_FUNDS = "Insufficient Funds";
		public static final String BALANCE_UPDATE_FAILED = "Balance Could not be Updated";
		public static final String INSERT_TRANS_FAILED = "Transaction Insertion has Failed";
		public static final String REVIEWDETAILS_EMPTY = "Review Details Cannot be Empty";
		public static final String REVIEW_STORED_SUCESSFULLY = "Review Stored Sucessfully";
		public static final String KEYWORD_EMPTY = "Keyword Cannot be Empty";
		public static final String KEYWORD_STORAGE_FAILED = "The Keyword Could not be Stored";
		public static final String ADD_POSITIVEKEYWORD_SUCESS = "Storage of Positive Keyword is Sucessful";
		public static final String ADD_NEGATIVEKEYWORD_SUCESS = "Storage of Negative Keyword is Sucessful";
		public static final String EMPTY_NEGATIVEKEYWORDS = "No Negative Keywords Found in the Application";
		public static final String NEGATIVE_KEYWORDS_SUCESS = "Negative Keyword Retrival is Sucessful";
		public static final String EMPTY_POSITIVEKEYWORDS = "No Positive Keywords Found in the Application";
		public static final String POSITIVE_KEYWORDS_SUCESS = "Positive Keyword Retrival is Sucessful";
		public static final String EMPTY_REVIEWSLIST = "Reviews List is Empty";
		public static final String REVIEWS_FETCH_SUCESS = "Reviews Fetched Sucessfully";
		public static final String POSITIVE_KEYWORD_EXIST = "Positive Keyword Already Exist";
		public static final String NEGATIVE_KEYWORD_EXIST = "Negative Keyword Already Exist";
		public static final String RANKING_FAILED_TOURISTPACK = "Ranking of Products  have Failed using Semantic Analyzer Algorithm";
		public static final String RANK_POLARITY_SUCESS = "Ranking with Respect to Polarity is Sucessful";
		public static final String EMPTY_POLARITY = "Polarity Computation is Empty at this point of Time";
		public static final String POLARITY_KEYWORDS_SUCESS = "Polarity Keyword retrival is Sucessful";
		public static final String EMPTY_STOPWORD = "Stopword Cannot be Empty";
		public static final String STOPWORD_EXIST = "Stopword Already Exist";
		public static final String EMPTY_STOPWORDS = "Stop Words are Empty";
		public static final String STOPWORD_SUCESS = "Retrival of Stop Words is sucessful";
		public static final String STOPWORD_ADD_FAILED = "Failed to Add Stop Word";
		public static final String STOPWORD_ADD_SUCESS = "Stop Word Added Sucessfully";
		public static final String CLEANMODEL_FAILED = "Clean Model Insertion has Falied";
		public static final String CLEANREVIEWS_SUCESS = "Clean of Reviews is Sucessful";
		public static final String TOKENS_SUCESS = "Tokenization Process has been completed Sucessfully";
		public static final String CLEANREVIEWS_EMPTY = "Clean Reviews are Empty";
		public static final String INSERT_TOKENS_FAILED = "Insertion of Tokens has Failed";
		public static final String EMPTY_TOKENS = "Tokens Are Empty";
		public static final String TOKENRETRIVAL_SUCESS = "Retrival of Tokens is Sucessful";
		public static final String FREQ_SUCESS = "Frequency Computation is Sucessful";
		public static final String TOKENS_EMPTY = "Token List  Cannot be Empty";
		public static final String COULDNOT_DELETE_FREQUENCY = "Could not delete the Frequency Contents";
		public static final String COULDNOT_FIND_REVIEWS = "Could not Find Reviews";
		public static final String COULDNOT_FIND_TOKENS = "Could not Find Tokens For the Review";
		public static final String NO_TOKEN_FOUND = "No Token Found";
		public static final String COULDNOT_INSERT_FREQUENCY = "Could not insert into Frequency";
		public static final String FREQ_COMPUTATION_SUCESS = "Frequency Computation is Sucessful";
		public static final String FREQUENCYLIST_EMPTY = "Frequency List is Empty";
		public static final String COULDNOT_COMPUTE_FREQUENCY = "Could not Compute Frequency";
		public static final String INVALID_TOURPACKID = "Tourist Pack ID is Invalid";
		public static final String FEATUREVECTORSUCESS_VIEW = "Feature Vector has been Sucessfully Computed";
		public static final String COULDNOTFIND_TOURPACKS_FREQ = "Could not find Tour Pack Ids From Frequency";
		public static final String COULDNOTFIND_REVIEWIDS_FREQ = "Could not find Review Ids From Frequency ";
		public static final String COULDNOTFIND_TOKENS_FREQ = "Could not find Tokens for the Specfic Review";
		public static final String COULDNOTFIND_REVIEWLIST = "Could not Find Reviews for Token";
		public static final String FEATURE_VECTOR_EMPTY = "Feature Vector cannot be Empty";
		public static final String EMPTY_FEATUREVECTOR_LIST = "Feature Vector List is Empty";
		public static final String FEATUREVECTOR_FETCH_SUCESS = "Feature Vector Fecthced Sucessfully";
		public static final String SEARCH_EMPTY = "Search Criteria is Empty";
		public static final String COULD_NOT_RANK = "Could not Rank at this Point of time";
		public static final String DELETE_FV = "Could not Delete Feature Vector";
		public static final String STOPWORD_REMOVE_FAILED = "Stopword Removal Failed";
		public static final String NO_STOPWORD_EXIST = "Stopword does not exist";
		public static final String STOPWORD_REMOVE_SUCESS = "Stopwords removed sucessfully";
		public static final String EMPTY_POSITIVEKEYWORD = "Positive Keyword Cannot be Empty";
		public static final String POSITIVEKEYWORD_REMOVE_FAILED = "Removal of Positive Keyword has Failed";
		public static final String KEYWORD_REMOVE_SUCESS = "Removal of Keyword is Sucessful";
		public static final String EMPTY_NEGATIVEKEYWORD = "Negative Keyword Cannot be Empty";
		public static final String NEGATIVEKEYWORD_REMOVE_FAILED = "Negative Keyword removal Failed";
		public static final String NO_POSITIVEKEYWORD_EXIST = "No Positive Keyword Exist";
		public static final String NO_NEGATIVEKEYWORD_EXIST = "No Negative Keyword Exist";
		public static final String RANKING_FAILED_POLARITY = "Rank of Review Polarity is Failed";
		public static final String RETRIVE_TWEETS_FAILED = "Retrival of Tweets has Failed";
		public static final String RETRIVE_TWEETS_SUCESS = "Retrival of Tweets is sucessful";
		public static final String DELETE_FV_FAILED = "Delete Feature Vector";
		public static final String DELETE_POLARITYORDER_FAILED = "Deletion of Polarity Order Failed";
		public static final String EMPTY_RANKCOMPANYS = "Rank COMPANYs cannot be Empty";
		public static final String RANKCOMPANY_SUCESS = "Rank COMPANYs is Sucessful";
		public static final String DELETE_ENHANCE_CONTIGENCY_FAILED = "Deletion of Enhanced Contigency has Failed";
		public static final String PLEASE_PERFORM_CONTIGENCY = "Please perform contigency";
		public static final String ENHANCE_CONTIGENCY_INSERT_FAILED = "Enhanced  Contigency insertion has Failed";
		public static final String CONTIGENCY_COMPUTATION_FAILED = "Enhanced Contigency Computation has Failed";
		public static final String CONTIGENCY_COMPUTATION_SUCESS = "Enhanced Contigency Computation is Sucessful";
		public static final String ENHANCED_CONTIGENCY_IS_EMPTY = "Enhanced Contigency is Empty";
		public static final String RETRIVAL_ENHANCED_CONTIGENCY_SUCESS = "Retrival of Enhanced Contigency is Sucessful";
		public static final String EMPTY_CLASSIFIER = "Classifier Results are Empty";
		public static final String CLASSIFY_SUCESS = "Classification is Sucessful";
		public static final String EMPTY_CLASSIFIER_COUNT = "Classification Results are Empty";
		public static final String CLASSIFY_COUNT_SUCESS = "Classification Count is Sucessful";
		public static final String CLASSIFY_FAILED = "Classification has Failed";
		public static final String COULD_NOT_DELETE_OLD_CLASS_INFO = "could not delete old classes";
		public static final String PLEASE_PERFORM_ENHNACE_CONTIGENCY = "Please perform Enhanced Contigency First";
		public static final String NO_TWEETS_ENHANCECONTIGENCY = "No Tweets for Enhanced Contigency";
		public static final String CLASSIFY_NOT_POSSIBLE_AT_THIS_TIME = "Classification not Possible at this Time";
		public static final String PATIAL_CLASSIFICATION_DONE = "Partial Classification is Completed";
		public static final String EMPTY_GROUPING_COUNT = "Empty of Grouping Count";
		public static final String CLASSIFY_GROUP_SUCESS = "Classification of Group Sucessful";
		public static final String NO_CLASSIFIER_COUNT = "No Classifier Count Performed";
		public static final String ACTIVATION_PROBABILITY_NOT_FOUND = "Activation Probability Could not be Found";
		public static final String COULD_NOT_REMOVE_ACTIVATION = "Could not Remove Activation Probability";
		public static final String EMPTY_ACTIVATIONS = "No Activation Probability Found";
		public static final String ACTIVATION_RETRIVAL__SUCESS = "Activation Probability Retrival is Sucessful";
		public static final String GROUPING_FAILED = "Grouping has Failed";
		public static final String GROUPING_SUCESS = "Grouping is Sucessful";
		public static final String COULD_NOT_COMPUTE_SENTIMENT_INDEX = "Could not Compute the Sentiment Index";
		public static final String SENTIMENTS_COMPUTED_SUCESSFULLY = "Sentiments have been Computed Sucessfully";
		public static final String DELETE_SENTIMENT_FAILED = "Could not Delete Old Sentiment Data";
		public static final String PLEASE_PERFORM_SENTIMENT_ANALYSIS = "Please Perform Sentiment Analysis Before Sentiment Index Computation";
		public static final String COULD_NOT_STORE_SENTIMENT_INDEX = "Could not Store Sentiment Index";
		public static final String EMPTY_SENTIMENTINDEX_LIST = "Sentiment Index List is Empty";
		public static final String SENTIMENT_INDEX_SUCESS = "Sentiment Index List has been Retrived Sucessfully";
		public static final String COULD_NOT_COMPUTE_PREDICTION = "Could not Compute Prediction";
		public static final String PREDICTION_COMPUTED_SUCESSFULLY = "Prediction has been Performed Sucessfully";
		public static final String COULD_NOT_FIND_STOCKDATA = "Could not Find Stock Market Data";
		public static final String COULD_NOT_FIND_SENINDEX = "Could not Find Sentiment Index";
		public static final String COULD_NOT_FIND_COMMON_COMPANYIDS = "Could not Find Common Company Ids";
		public static final String INSERT_PREDICTION_FAILED = "Insertion of Prediction has Failed";
		public static final String EMPTY_PREDICTION_LIST = "Empty Prediction List";
		public static final String PREDICTION_SUCESS = "Prediction Retrival is Sucessful";
		public static final String COULD_NOT_RETRIVE_YAHOOFINANCE = "Could not Retrive  Finance at this Point of Time";
		public static final String YAHOOFINANCE_COMPUTED_SUCESSFULLY = "Finance has been Retrived Sucessfully";
		public static final String COMPANY_KEY_CANNOT_BE_EMPTY = "Company Key Cannot be Empty";
		public static final String STOCKDATA_SUCESS = "Stock Data has been Computed Sucessfully";
		public static final String EMPTY_STOCKDATA_LIST = "Stock Data is Empty";
		public static final String COULD_NOT_REMOVE_OLDSTOCKDATA = "Could not Remove Old Stock Data";
		public static final String YAHOO_KEY_DOES_NOT_EXIST = "Yahoo Key Does not Exist";
		public static final String COULD_NOT_SAVE_YAHOO_DATA = "Could not save yahoo data";
		public static final String FINANCE_KEY_DOES_NOT_EXIST = "Finance Key Does not Exist";
		public static final String COULD_NOT_RETRIVE_DATA_FROM_FINANCE_API = "Could not Retrive Data from Finance API";
		public static final String COULD_NOT_SAVE_FINANCE_DATA = "Could not Save Finance Data"; 

		String HASHTAG_ADD_FAILED = "Failed To Add Hash Tag";
		String EMPTY_HASHTAG = "Hash Tag Cannot be Empty";
		String HASHTAG_ADD_SUCESS = "HashTag Added Sucessfully";
		String HASHTAG_INVALID = "Hash Tag is Invalid";
		String HASHTAG_EXIST = "Hash Tag Already exist";
		String EMPTY_HASHTAGS = "Could not Find Any Hash Tags";
		String HASHTAGS_SUCESS = "Hash Tags Retrived Sucessfully";
		String HASHTAGS_EMPTY = "There are no Hash Tags in the System";

	}

	interface Keys {
		public static final String OBJ = "obj";
		public static final int ADMIN_TYPE = 5;
		public static final String STOPWORDS_SYMBOL = "[^a-zA-Z]+";
		public static final String SPACE = "  ";
		public static final String USERIDKEY = "USERID";
		public static final String TWEET_DESC_KEY = "tweetDesc";
		public static final String TWEET_USERID_KEY = "userId";
		public static final String TWEET_HASHTAG_KEY = "hashTag";
		public static final String TWEET_LANG_KEY = "language";
		public static final String TWEET_SCREENNAME_KEY = "screenName";
		public static final String COMPANYID_KEY = "COMPANYID";
		
		
		public static final String FINANCE_API_KEY="50M3AP1K3Y";
		
		final String CONSUMER_KEY="nPFfrhiQhPyAiEu3rAko7hzwU";
		
		final String CONSUMER_TOKEN="jmVHsFkIFgPoDG6akbsKXUJS4IXqIQ1SFtuzYzgTCkdoRMz6nH";
		
		final String OAUTH_KEY="1048908211-qD3Dxr5hk0hZEQC7jqUkNStc04C3F1XtjcJhjVH";
		
		final String OAUTH_TOKEN="3btbAZ2Pd4UCSE1mlE1i46NqkOsydK5UBnAxHGs67jXns";
		

	}

	interface COLUMNNAMES {
		/*
		 * These are the column names for review table
		 */
		public static final String COMPANYID_COL = "COMPANYID";
		public static final String COMPANYTYPE_COL = "CATID";
		public static final String REVIEWDETAILS_COL = "REVIEWDETAILS";
		/*
		 * These are the column names for the product table
		 */
		public static final String COMPANYID__PK_COL = "COMPANYID_PK";
		public static final String COMPANYNAME_COL = "COMPANYNAME";
		public static final String COMPANYTYPE_FK_COL = "COMPANYTYPE_FK";
		/*
		 * These are the column names for the product Type table
		 */
		public static final String COMPANYTYPEID_PK_COL = "COMPANYTYPEID_PK";
		public static final String COMPANYTYPENAME_COL = "COMPANYTYPENAME";
		public static final String HASTAG_COL = "HASHTAG";
		public static final String LANGUAGE_COL = "LANGUAGE";
		public static final String TWEETSCREENNAME_COL = "TWEETSCREENNAME";
		public static final String USERID_COL = "USERID";
		public static final String REVIEWID_COL = "REVIEWID";

	}

	interface Views {
		public static final String VIEW_CUSTOMER_WELCOMEPAGE = "custwelcome";
		public static final String VIEW_LOGIN_INPUT = "login";
		public static final String VIEW_ADMIN_WELCOMEPAGE = "admin";
		public static final String VIEW_BANK_INPUT = "bankinput";
		public static final String VIEW_ERROR_PAGE = "error";
		public static final String APPLICATION_WELCOME_PAGE = "applicationwelcome";
		public static final String VIEW_SUCESS_PAGE = "sucess";
		public static final String APPLYREVIEW_VIEW = "applyReview";
		public static final String ADD_POSITIVEKEYWORD_VIEW = "addpositivekeyword";
		public static final String ADD_NEGATIVEKEYWORD_VIEW = "addnegativekeyword";
		public static final String VIEW_ADMIN_SUCESS_PAGE = "adminsucess";
		public static final String VIEW_ADMIN_ERROR_PAGE = "adminerror";
		public static final String VIEW_POLARITY_PACK = "polarity";
		public static final String STOPWORD_INPUT = "addStopword";
		public static final String RANKFV_INPUT = "rankfv";
		public static final String RANKFV_OUTPUT = "rankfvout";
		public static final String RANKCORRELATION_MOBILE_INPUT = "rankCorrelationMobile";
		public static final String RANKCORRELATION_OUTPUT = "rankCorrelationOutput";
		public static final String REMOVESTOPWORD_INPUT = "removeStopword";
		public static final String REMOVEPOSITIVEKEYWORD_INPUT = "removepositivekeyword";
		public static final String REMOVENEGATIVEKEYWORD_INPUT = "removenegativekeyword";
		public static final String RANKFEATURE_OUTPUT = "rankfeature";
		public static final String RETRIVETWEETS_VIEW = "tweetsubmission";
		public static final String ERROR_VIEW = "error";

		String HASHTAG_INPUT = "hashtagsubmission";

	}

	interface DatabaseColumns {

		public static final String SPOTID_COL = "SPOTID";

		public static final String SPOTNAME_COL = "SPOTNAME";

		public static final String SPOTDESC_COL = "SPOTDESC";

		public static final String SPOTRENT_COL = "SPOTRENT";

		public static final String SPOTIMAGELOC_COL = "SPOTIMAGELOC";

		public static final String PLACE_COL = "PLACE";

		public static final String STATE_COL = "STATE";

		public static final String COUNTRY_COL = "COUNTRY";

		public static final String HOTELPACK_FK_COL = "HOTELPACK_FK";

		public static final String PACKASSID_COL = "PACKASSID";

		public static final String SPOTID_FK_COL = "SPOTID_FK";

		public static final String TOURISTPACK_FK_COL = "TOURISTPACK_FK";

		public static final String HOTELDESC_COL = "HOTELDESC";

		public static final String HOTELID_COL = "HOTELID";

		public static final String HOTELIMAGELOC_COL = "HOTELIMAGELOC";

		public static final String HOTELNAME_COL = "HOTELNAME";

		public static final String HOTELRENT_COL = "HOTELRENT";

		public static final String PACKID_COL = "PACKID";

		public static final String PACKDESC_COL = "PACKDESC";

		public static final String PACKNAME_COL = "PACKNAME";

		public static final String NEGATIVEKEYID_COL = "NEGATIVEKEYID";

		public static final String NEGATIVEKEYWORD_COL = "NEGATIVEKEYWORD";

		public static final String PKEYID_COL = "PKEYID";

		public static final String PKEYWORDS_COL = "PKEYWORDS";

		public static final String TOURISTPACKID_COL = "TOURISTPACKID";

		public static final String REVIEWID_COL = "REVIEWID";

		public static final String REVIEWDETAILS_COL = "REVIEWDETAILS";

		public static final String TOURPACKID_COL = "TOURPACKID";

		public static final String POSITIVERATING_COL = "POSITIVERATING";

		public static final String NEGATIVERATING_COL = "NEGATIVERATING";

		public static final String NEUTRALRATING_COL = "NEUTRALRATING";

		public static final String STOPWORDID_COL = "STOPWORDID";

		public static final String STOPWORD_COL = "STOPWORD";

		public static final String CLEANID_COL = "CLEANID";

		public static final String CLEANREVIEW_COL = "CLEANREVIEW";

		public static final String TOKENID_COL = "TOKENID";

		public static final String TOKENNAME_COL = "TOKENNAME";

		public static final String FREQID_COL = "FREQID";

		public static final String FREQ_COL = "FREQ";

		public static final String NOOFREVIEWS_COL = "NOOFREVIEWS";

		public static final String IDFT_COL = "IDFT";

		public static final String FEATUREVECTOR_COL = "FEATUREVECTOR";

		public static final String FEATUREID_COL = "FEATUREID";

		public static final String COMPANYID_COL = "COMPANYID";

		public static final String CATID_COL = "CATID";

		
		public static final String POSITIVEPOLARITY_COL = "POSITIVEPOLARITY";

		public static final String NEGATIVEPOLARITY_COL = "NEGATIVEPOLARITY";

		public static final String NEUTRALPOLARITY_COL = "NEUTRALPOLARITY";

		public static final String COMPANYTYPE_COL = "COMPANYTYPE";

		public static final String FEATUREBASEDFREQ_COL = "FEATUREBASEDFREQ";

		public static final String FEATURETYPE_COL = "FEATURETYPE";

		public static final String TOTALFEATURE_COL = "FEATURESCORE";

		public static final String HASHTAGID_COL = "HASHTAGID";

		public static final String HASHTAG_COL = "HASHTAG";

		public static final String CATNAME_COL = "CATNAME";

		public static final String CATWORD_COL = "CATKEYWORD";

		public static final String COMPANYNAME_COL = "COMPANYNAME";

		String GROUPTYPE_COL = "GROUPTYPE";
		String ACTIVATIONPROBABILITY_COL = "ACTIVATIONPROBABILITY";
		String PRODUCTTOPUSH_COL = "PRODUCTTOPUSH";
		String COUNT_COL = "COUNT";
		String USERID_COl = "USERID";

	}

	interface TweetOAuthConstants {

		String OAUTHKEY1 = "GXvmth0FawnOZS1g8JlrWK54Q";
		String OAUTHKEY2 = "wnR1pVIsOaPnLzJZLi2cYUD505SHkHp7AzXHIJGYxlIJzi468A";

		String APIKEY = "048908211-qD3Dxr5hk0hZEQC7jqUkNStc04C3F1XtjcJhjVH";
		String SECRETKEY = "3btbAZ2Pd4UCSE1mlE1i46NqkOsydK5UBnAxHGs67jXns";
		int TWEETS_NOS = 10;

	}

	interface DatabaseColumnsKeys {

		String COMPANYID_KEY = "COMPANYID";
		String COMPANYNAME_KEY = "COMPANYNAME";
		String FEATURETYPE_KEY = "FEATURETYPE";
		String POSITIVEPOLARITY_KEY = "POSITIVEPOLARITY";
		String NEGATIVEPOLARITY_KEY = "NEGATIVEPOLARITY";
		String NEUTRALPOLARITY_KEY = "NEUTRALPOLARITY";
		String TOTALFEATURE_KEY = "TOTALFEATURE";

		String GROUPTYPE_KEY = "GROUPTYPE";
		String ACTIVATIONPROBABILITY_KEY = "ACTIVATIONPROBABILITY";
		String PRODUCTTOPUSH_KEY = "PRODUCTTOPUSH";
		String COUNT_KEY = "COUNT";
		String USERID_KEY = "USERID";

	}

}
