package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;

import com.constants.StockMarketConstantsIF;
import com.dao.inter.StockMarketDAOIF;
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

public class StockMarketDAOImpl implements StockMarketDAOIF {

	protected SimpleJdbcTemplate template;
	protected NamedParameterJdbcTemplate namedJdbcTemplate;
	private DataSource dataSource;
	@Autowired
	protected MessageSource sqlProperties;
	protected JdbcTemplate jdbcTemplate;

	/**
	 * 
	 */
	public void init() {
		template = new SimpleJdbcTemplate(dataSource);
		namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected boolean update(String sqlKey, Map<String, Object> map) {
		System.out.println("Class-->RoutingDaoImpl:Method-->update");
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println("SQL" + sql);
		boolean value = false;
		try {
			namedJdbcTemplate.update(sql, map);
			value = true;
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		return value;
	}

	/**
	 * @param sqlKey
	 * @param map
	 * @return true if sucessfully updated
	 */
	protected boolean insert(String sqlKey, Map<String, Object> map) {
		System.out.println("Class-->RoutingDaoImpl:Method-->update");
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println("SQL" + sql);
		boolean value = false;
		try {
			namedJdbcTemplate.update(sql, map);
			value = true;
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		return value;
	}

	/**
	 * @param sqlQuery
	 * @param sqlKey
	 * @param map
	 * @return true if sucessfully updated
	 */
	protected boolean insertBasedOnQuery(String sqlQuery, Map<String, Object> map) {
		System.out.println("Class-->RoutingDaoImpl:Method-->update");
		boolean insertQuery = false;
		try {
			namedJdbcTemplate.update(sqlQuery, map);
			insertQuery = true;
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
		return insertQuery;
	}

	/**
	 * @param <T>
	 * @param sqlKey
	 * @param paramMap
	 * @param rowMapper
	 * @return Object
	 */
	protected <T> T executeForObject(String sqlKey, Map<String, ? extends Object> paramMap, RowMapper<T> rowMapper) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		return namedJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
	}

	protected <T> T executeForObjectUsingQuery(String sql, Map<String, ? extends Object> paramMap,
			RowMapper<T> rowMapper) {
		return namedJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
	}

	/**
	 * @param sqlKey
	 * @param params
	 * @param whereClause
	 * @return int once the statement gets executed
	 */
	protected int executeForInt(String sqlKey, Map<String, String> params, String whereClause) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		sql = sql.concat(whereClause);
		System.out.println(sql);

		return namedJdbcTemplate.queryForInt(sql, params);
	}

	/**
	 * @param sqlKey
	 * @param params
	 * @return List of String objects
	 */
	protected List<String> executeForListOfString(String sqlKey, Map<String, Object> params) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println(sql);
		System.out.println(params);

		return namedJdbcTemplate.queryForList(sql, params, String.class);
	}

	/**
	 * @param sqlKey
	 * @param params
	 * @return List of integer values
	 */
	protected List<Integer> executeForListOfInt(String sqlKey, Map<String, Object> params) {
		String sql = sqlProperties.getMessage(sqlKey, null, null);
		System.out.println(sql);
		System.out.println(params);

		return namedJdbcTemplate.queryForList(sql, params, Integer.class);
	}

	/**
	 * @return template
	 */
	public SimpleJdbcTemplate getTemplate() {
		return template;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return Named Parameter JDBC Template
	 */
	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	/**
	 * @return the SQL properties
	 */
	public MessageSource getSqlProperties() {
		return sqlProperties;
	}

	/**
	 * @param sqlProperties
	 */
	public void setSqlProperties(MessageSource sqlProperties) {
		this.sqlProperties = sqlProperties;
	}

	@Override
	public StatusInfo insertReview(ReviewModel reviewModel) {

		StatusInfo statusInfo = new StatusInfo();
		try {
			String sqlKey = StockMarketConstantsIF.SQLS.INSERT_REVIEW_SQL;
			String sql = sqlProperties.getMessage(sqlKey, null, null);
			jdbcTemplate
					.update(sql,
							new Object[] { new SqlLobValue(reviewModel.getReviewDetails()), reviewModel.getCompanyId(),
									reviewModel.getCompanyName() },
							new int[] { Types.BLOB, Types.INTEGER, Types.INTEGER });
			statusInfo.setStatus(true);

		} catch (Exception e) {
			System.out.println("Exception is:");
			System.out.println(e.getMessage());
			e.printStackTrace();
			statusInfo.setErrMsg(StockMarketConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
		return statusInfo;
	}

	@Override
	public List<ReviewModel> retriveAllReviews() {

		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_REVIEWS_SQL, null, null);
			return jdbcTemplate.query(sql, new ReviewModelVOMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	// HASHTAG,TWEETSCREENNAME,USERID,LANGUAGE

	private final class ReviewModelVOMapper implements RowMapper<ReviewModel> {

		public ReviewModel mapRow(ResultSet rs, int arg1) throws SQLException {

			ReviewModel reviewModel = new ReviewModel();

			reviewModel.setCompanyId(rs.getInt(StockMarketConstantsIF.COLUMNNAMES.COMPANYID_COL));
			reviewModel.setCompanyType(rs.getInt(StockMarketConstantsIF.COLUMNNAMES.COMPANYTYPE_COL));

			String reviewDetailsStr = rs.getString(StockMarketConstantsIF.COLUMNNAMES.REVIEWDETAILS_COL);
			reviewModel.setReviewDetails(reviewDetailsStr);

			reviewModel.setHashTag(rs.getString(StockMarketConstantsIF.COLUMNNAMES.HASTAG_COL));

			reviewModel.setLanguage(rs.getString(StockMarketConstantsIF.COLUMNNAMES.LANGUAGE_COL));

			reviewModel.setTweetScreenName(rs.getString(StockMarketConstantsIF.COLUMNNAMES.TWEETSCREENNAME_COL));

			reviewModel.setUserId(rs.getString(StockMarketConstantsIF.COLUMNNAMES.USERID_COL));

			reviewModel.setTweetId(rs.getInt(StockMarketConstantsIF.COLUMNNAMES.REVIEWID_COL));

			return reviewModel;

		}

	}

	@Override
	public List<CompanyInfo> retriveAllCompanyInfo() {
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYINFO_SQL, null, null);
			return jdbcTemplate.query(sql, new COMPANYINFOListMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class COMPANYINFOListMapper implements RowMapper<CompanyInfo> {

		public CompanyInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyId(rs.getInt(StockMarketConstantsIF.COLUMNNAMES.COMPANYID__PK_COL));
			companyInfo.setCompanyName(rs.getString(StockMarketConstantsIF.COLUMNNAMES.COMPANYNAME_COL));
			companyInfo.setCompanyType(rs.getInt(StockMarketConstantsIF.COLUMNNAMES.COMPANYTYPE_FK_COL));
			return companyInfo;

		}

	}

	@Override
	public List<CompanyInfo> retriveSpecficCompanyInfo(int companyType) {
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYINFO_FOR_COMPANYTYPE_SQL,
					null, null);
			return jdbcTemplate.query(sql, new COMPANYINFOListMapper(), companyType);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public List<CompanyTypeInfo> retriveAllCompanyTypes() {
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYTYPEINFO_SQL, null, null);
			return jdbcTemplate.query(sql, new COMPANYTYPEInfoListMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class COMPANYTYPEInfoListMapper implements RowMapper<CompanyTypeInfo> {

		public CompanyTypeInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			CompanyTypeInfo collegeTypeInfo = new CompanyTypeInfo();

			collegeTypeInfo.setCompanyTypeId(rs.getInt(StockMarketConstantsIF.COLUMNNAMES.COMPANYTYPEID_PK_COL));
			collegeTypeInfo.setCompanyName(rs.getString(StockMarketConstantsIF.COLUMNNAMES.COMPANYTYPENAME_COL));

			return collegeTypeInfo;

		}

	}

	@Override
	public CompanyTypeInfo retriveSpecificCompanyTypeName(int COMPANYType) {
		try {

			String sql = sqlProperties
					.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYTYPEINFO_FOR_COMPANYTYPEID_SQL, null, null);
			return jdbcTemplate.queryForObject(sql, new COMPANYTYPEInfoListMapper(), COMPANYType);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public String retriveCompanyNameForId(int COMPANYId) {
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYNAME_FOR_COMPANYID, null,
					null);
			return jdbcTemplate.queryForObject(sql, String.class, COMPANYId);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public StatusInfo insertNegativeKeywords(String keywordV) {
		StatusInfo negativeKeywords = null;
		try {
			negativeKeywords = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_NEGATIVEKEYWORDS_SQL, null, null);
			jdbcTemplate.update(sql, new Object[] { keywordV }, new int[] { Types.VARCHAR });
			negativeKeywords.setStatus(true);
			return negativeKeywords;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			negativeKeywords = new StatusInfo();
			negativeKeywords.setErrMsg(e.getMessage());
			negativeKeywords.setStatus(false);
			return negativeKeywords;

		}
	}

	@Override
	public StatusInfo insertPositiveKeywords(String keywordV) {
		StatusInfo insertPositiveKeywords = null;
		try {
			insertPositiveKeywords = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_POSTIVEKEYWORDS_SQL, null, null);
			jdbcTemplate.update(sql, new Object[] { keywordV }, new int[] { Types.VARCHAR });
			insertPositiveKeywords.setStatus(true);
			return insertPositiveKeywords;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertPositiveKeywords = new StatusInfo();
			insertPositiveKeywords.setErrMsg(e.getMessage());
			insertPositiveKeywords.setStatus(false);
			return insertPositiveKeywords;
		}
	}

	@Override
	public List<KeywordInfo> retriveNegativeKeywords() {
		List<KeywordInfo> packAssociationList = null;
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_NEGATIVEKEYWORDS_SQL, null, null);
			return jdbcTemplate.query(sql, new NegativeKeywordInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class NegativeKeywordInfoMapper implements RowMapper<KeywordInfo> {

		public KeywordInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			KeywordInfo keywordInfo = new KeywordInfo();
			keywordInfo.setKeywordId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.NEGATIVEKEYID_COL));
			keywordInfo.setKeyword(rs.getString(StockMarketConstantsIF.DatabaseColumns.NEGATIVEKEYWORD_COL));
			return keywordInfo;
		}

	}

	@Override
	public List<String> retriveNegativeKeywordsOnly() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_NEGATIVE_KEYWORDS_SQL, null,
					null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}

	}

	@Override
	public List<KeywordInfo> retrivePositiveKeywords() {
		List<KeywordInfo> packAssociationList = null;
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_POSITIVEKEYWORDS_SQL, null, null);
			return jdbcTemplate.query(sql, new PositiveKeywordInfoMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class PositiveKeywordInfoMapper implements RowMapper<KeywordInfo> {

		public KeywordInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			KeywordInfo keywordInfo = new KeywordInfo();
			keywordInfo.setKeywordId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.PKEYID_COL));
			keywordInfo.setKeyword(rs.getString(StockMarketConstantsIF.DatabaseColumns.PKEYWORDS_COL));
			return keywordInfo;
		}

	}

	@Override
	public List<String> retrivePositiveKeywordsOnly() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_POSITIVE_KEYWORDS_SQL, null,
					null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo deleteSentimentAnalyzer(int type) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.DELETE_SENTIMENTANALYZER_SQL, null, null);
			jdbcTemplate.update(sql, type);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo deleteTotalPolarity(int type) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.DELETE_SENTIMENTTOTAL_SQL, null, null);
			jdbcTemplate.update(sql, type);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<ReviewModelObj> retriveReviewList(int type) {

		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_ALLREVIEWS_SQL, null, null);
			return jdbcTemplate.query(sql, new ReviewModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class ReviewModelMapper implements RowMapper<ReviewModelObj> {

		public ReviewModelObj mapRow(ResultSet rs, int arg1) throws SQLException {
			ReviewModelObj reviewModel = new ReviewModelObj();
			reviewModel.setCompanyId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYID_COL));
			reviewModel.setReviewId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.REVIEWID_COL));
			reviewModel.setReviewDetailsBlob(rs.getBlob(StockMarketConstantsIF.DatabaseColumns.REVIEWDETAILS_COL));
			reviewModel.setCompanyType(rs.getInt(StockMarketConstantsIF.DatabaseColumns.CATID_COL));

			return reviewModel;
		}

	}

	@Override
	public StatusInfo insertPolarity(PolarityModel polarityModel) {
		StatusInfo insertPolarity = null;
		try {
			insertPolarity = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_POLARITY_SQL, null, null);
			System.out.println("SQL----" + sql);

			jdbcTemplate.update(sql, new Object[] { polarityModel.getReviewId(), polarityModel.getPositiveRating(),
					polarityModel.getNegativeRating(), polarityModel.getNeutralRating(), polarityModel.getCompanyId(),
					polarityModel.getCompanyType(), polarityModel.getFeatureType() },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR });
			insertPolarity.setStatus(true);
			return insertPolarity;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertPolarity = new StatusInfo();
			insertPolarity.setErrMsg(e.getMessage());
			insertPolarity.setStatus(false);
			return insertPolarity;

		}
	}

	@Override
	public List<Integer> retriveUniqueCompanysIdsFromSentimentAnalyzer(int type) {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_UNIQUE_COMPANYIDS_SQL, null,
					null);

			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public int retriveTotalPositiveRatingForCompany(Integer COMPANYIDTemp, int COMPANYTYPE) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, COMPANYTYPE);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}

	}

	@Override
	public int retriveTotalNegativeRatingForCompany(Integer COMPANYIDTemp, int COMPANYTYPE) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, COMPANYTYPE);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNeutralRatingForCompany(Integer COMPANYIDTemp, int COMPANYTYPE) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALNEUTRALPOLARITY_WHERE_COMPANYID_COMPANYTYPE_SQL, null,
					null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, COMPANYTYPE);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public StatusInfo insertTotalPolarity(TotalPolarityModel totalPolarityModel) {
		StatusInfo insertPackAssociationStatus = null;
		try {
			insertPackAssociationStatus = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_TOTALPOLARITY_SQL, null, null);

			jdbcTemplate.update(sql,
					new Object[] { totalPolarityModel.getCompanyId(), totalPolarityModel.getPositiveRating(),
							totalPolarityModel.getNegativeRating(), totalPolarityModel.getNeutralRating(),
							totalPolarityModel.getCompanyType(), totalPolarityModel.getTotalFeature(),
							totalPolarityModel.getFeatureType() },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR });
			insertPackAssociationStatus.setStatus(true);
			return insertPackAssociationStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertPackAssociationStatus = new StatusInfo();
			insertPackAssociationStatus.setErrMsg(e.getMessage());
			insertPackAssociationStatus.setStatus(false);
			return insertPackAssociationStatus;
		}
	}

	@Override
	public List<Integer> retriveCompanysIdsIDSORderBy(int type) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_COMPANYIDS_ORDERBY_WHERE_COMPANYTYPE_SQL, null, null);
			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<PolarityModel> retrivePolarity(int type) {
		List<PolarityModel> hotelModelList = null;
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_ALLPOLARITY_WHERE_COMPANYTYPE_SQL,
					null, null);
			return jdbcTemplate.query(sql, new PolarityModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class PolarityModelMapper implements RowMapper<PolarityModel> {

		public PolarityModel mapRow(ResultSet rs, int arg1) throws SQLException {
			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setReviewId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.REVIEWID_COL));

			polarityModel.setCompanyId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYID_COL));

			polarityModel.setPositiveRating(rs.getInt(StockMarketConstantsIF.DatabaseColumns.POSITIVERATING_COL));
			polarityModel.setNegativeRating(rs.getInt(StockMarketConstantsIF.DatabaseColumns.NEGATIVERATING_COL));
			polarityModel.setNeutralRating(rs.getInt(StockMarketConstantsIF.DatabaseColumns.NEUTRALRATING_COL));

			polarityModel.setCompanyType(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYTYPE_COL));

			polarityModel.setFeatureType(rs.getString(StockMarketConstantsIF.DatabaseColumns.FEATURETYPE_COL));

			return polarityModel;
		}
	}

	@Override
	public List<PolarityModel> retriveTotalPolarity(int type) {
		List<PolarityModel> polarityModelList = null;
		try {
			String sql = sqlProperties
					.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_TOTALPOLARITY_WHERE_COMPANYTYPE_SQL, null, null);
			return jdbcTemplate.query(sql, new PolarityTotalModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class PolarityTotalModelMapper implements RowMapper<PolarityModel> {

		public PolarityModel mapRow(ResultSet rs, int arg1) throws SQLException {
			PolarityModel polarityModel = new PolarityModel();

			polarityModel.setCompanyId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYID_COL));

			polarityModel.setPositiveRating(rs.getInt(StockMarketConstantsIF.DatabaseColumns.POSITIVEPOLARITY_COL));
			polarityModel.setNegativeRating(rs.getInt(StockMarketConstantsIF.DatabaseColumns.NEGATIVEPOLARITY_COL));
			polarityModel.setNeutralRating(rs.getInt(StockMarketConstantsIF.DatabaseColumns.NEUTRALPOLARITY_COL));

			polarityModel.setCompanyType(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYTYPE_COL));

			polarityModel.setTotalFeature(rs.getInt(StockMarketConstantsIF.DatabaseColumns.TOTALFEATURE_COL));

			polarityModel.setFeatureType(rs.getString(StockMarketConstantsIF.DatabaseColumns.FEATURETYPE_COL));

			return polarityModel;
		}
	}

	@Override
	public List<Integer> retriveDistinctCompanyIdsFromFV(int type) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_DISTINCTCOMPANYSIDS_FROM_FV_WHERE_COMPANYTYPE_SQL, null, null);
			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Integer> retriveDistinctReviewIDSForCompanyIdsFromFV(Integer COMPANYIdTemp, int type) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_DISTINCTREVIEWSIDS_WHERE_COMPANYID_COMPANYTYPE_FROM_FV_SQL,
					null, null);
			return jdbcTemplate.queryForList(sql, Integer.class, COMPANYIdTemp, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<CompanyModel> retriveFVForCompanyType(int type) {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYINFO_WHERE_COMPANYTYPE_SQL,
					null, null);

			return jdbcTemplate.query(sql, new ProductModelGraphMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class ProductModelGraphMapper implements RowMapper<CompanyModel> {

		public CompanyModel mapRow(ResultSet rs, int arg1) throws SQLException {
			CompanyModel COMPANYModelVO = new CompanyModel();
			COMPANYModelVO.setCompanyId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYID_COL));
			COMPANYModelVO.setFeatureVector(rs.getDouble(StockMarketConstantsIF.DatabaseColumns.FEATUREVECTOR_COL));
			COMPANYModelVO.setCompanyType(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYTYPE_COL));
			return COMPANYModelVO;

		}
	}

	@Override
	public List<Integer> retriveDistinctCompanyIdsFromReviews(int type) {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_UNIQUE_COMPANYIDS_FROM_REVIEW_SQL,
					null, null);

			return jdbcTemplate.queryForList(sql, Integer.class, type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<String> retriveFeatureTypes() {
		List<String> featureTypes = null;
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_FEATURETYPES_SQL, null, null);

			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;

		}
	}

	@Override
	public List<String> removePositiveKeywordOnly() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_POSITIVEKEYWORDS_ONLY_SQL, null,
					null);
			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo removePositiveKeyword(String stopWord) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.REMOVE_POSITIVEKEYWORD_SQL, null, null);
			jdbcTemplate.update(sql, stopWord);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<String> removeNegativeKeywordOnly() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_NEGATIVEKEYWORDS_ONLY_SQL, null,
					null);
			return jdbcTemplate.queryForList(sql, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public StatusInfo removeNegativeKeyword(String stopWord) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.REMOVE_NEGATIVEKEYWORD_SQL, null, null);
			jdbcTemplate.update(sql, stopWord);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public int retriveTotalPositiveRatingForCompanyAndFeatureType(Integer COMPANYIDTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, type, featureType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNegativeRatingForCompanyAndFeatureType(Integer COMPANYIDTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, type, featureType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNeutralRatingForCompanyAndFeatureType(Integer COMPANYIDTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALNEUTRALPOLARITY_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, type, featureType);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalFeatureForCompany(Integer COMPANYIDTemp, int type, String featureType) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_FREQ_FROM_FREQ_WHERE_COMPANYID_COMPANYTYPE_FEATURETYPE_SQL,
					null, null);
			return jdbcTemplate.queryForInt(sql, COMPANYIDTemp, type, featureType);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public StatusInfo insertPolarityOrderInfo(PolarityOrderInfo polarityOrderInfo) {
		StatusInfo insertTokenStatus = null;
		try {
			insertTokenStatus = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_POLARITYORDER_SQL, null, null);

			jdbcTemplate.update(sql,
					new Object[] { polarityOrderInfo.getCompanyId(), polarityOrderInfo.getTotalFeature(),
							polarityOrderInfo.getTotalNegative(), polarityOrderInfo.getTotalNeutral(),
							polarityOrderInfo.getTotalPositive(), polarityOrderInfo.getType() },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
							Types.INTEGER });
			insertTokenStatus.setStatus(true);
			return insertTokenStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTokenStatus = new StatusInfo();
			insertTokenStatus.setErrMsg(e.getMessage());
			insertTokenStatus.setStatus(false);
			return insertTokenStatus;

		}
	}

	@Override
	public List<PolarityOrderInfo> retriveAllPolarityOrderInfoRankBy() {

		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_POLARITYORDERINFO_RANK_SQL, null,
					null);
			return jdbcTemplate.query(sql, new PolarityOrderInfoMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class PolarityOrderInfoMapper implements RowMapper<PolarityOrderInfo> {

		public PolarityOrderInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			PolarityOrderInfo freqVO = new PolarityOrderInfo();

			freqVO.setCompanyId(rs.getInt("COMPANYID"));
			freqVO.setTotalFeature(rs.getInt("TOTALFEATURE"));
			freqVO.setTotalNegative(rs.getInt("TOTALNEGATIVE"));
			freqVO.setTotalNeutral(rs.getInt("TOTALNEUTRAL"));
			freqVO.setTotalPositive(rs.getInt("TOTALPOSITIVE"));
			freqVO.setType(rs.getInt("COMPANYTYPE"));

			return freqVO;

		}

	}

	@Override
	public List<PolarityModel> viewTotalPolarityByType(String type) {
		List<PolarityModel> polarityModelList = null;
		try {
			String sql = sqlProperties
					.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_TOTALPOLARITY_WHERE_FEATURETYPE_SQL, null, null);
			return jdbcTemplate.query(sql, new PolarityTotalModelMapper(), type);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<HashTagsVO> retriveHashTags() {
		List<HashTagsVO> stopWordList = null;
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_HASHTAGS_FULL_SQL, null, null);
			Map<String, Object> paramMap = null;
			return namedJdbcTemplate.query(sql, paramMap, new HashTagVOMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class HashTagVOMapper implements RowMapper<HashTagsVO> {

		public HashTagsVO mapRow(ResultSet rs, int arg1) throws SQLException {
			HashTagsVO hashTagVO = new HashTagsVO();
			hashTagVO.setHashTagId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.HASHTAGID_COL));
			hashTagVO.setHashtag(rs.getString(StockMarketConstantsIF.DatabaseColumns.HASHTAG_COL));
			hashTagVO.setCompanyId(rs.getInt(StockMarketConstantsIF.DatabaseColumns.COMPANYID_COL));

			return hashTagVO;
		}
	}

	@Override
	public StatusInfo insertHashTag(String hashTag, String companyId) {
		StatusInfo insertStopWordStatus = null;
		try {
			insertStopWordStatus = new StatusInfo();

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_HASHTAG_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("hashtag", hashTag);
			paramMap.put("COMPANYID", Integer.parseInt(companyId));

			namedJdbcTemplate.update(sql, paramMap);
			insertStopWordStatus.setStatus(true);
			return insertStopWordStatus;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertStopWordStatus = new StatusInfo();
			insertStopWordStatus.setErrMsg(e.getMessage());
			insertStopWordStatus.setStatus(false);
			return insertStopWordStatus;

		}
	}

	@Override
	public List<String> viewHashTags() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_HASHTAGS_SQL, null, null);
			SqlParameterSource paramMap = null;
			return namedJdbcTemplate.queryForList(sql, paramMap, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<CategoryInfo> retriveAllCategory() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_CATINFO_SQL, null, null);
			Map<String, Object> paramMap = null;
			return namedJdbcTemplate.query(sql, paramMap, new CategoryInfoVOMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class CategoryInfoVOMapper implements RowMapper<CategoryInfo> {

		public CategoryInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			CategoryInfo webSiteDataVO = new CategoryInfo();
			webSiteDataVO.setCatId((rs.getInt(StockMarketConstantsIF.DatabaseColumns.CATID_COL)));
			webSiteDataVO.setCatName(rs.getString(StockMarketConstantsIF.DatabaseColumns.CATNAME_COL));
			webSiteDataVO.setCatWord(rs.getString(StockMarketConstantsIF.DatabaseColumns.CATWORD_COL));
			return webSiteDataVO;

		}
	}

	@Override
	public StatusInfo insertBlockTweetInfo(List<TweetInfo> tweetInfoList) {
		StatusInfo insertTweetInfo = null;
		try {
			insertTweetInfo = new StatusInfo();

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_TWEETINFO_SQL, null, null);

			for (TweetInfo tweetInfo : tweetInfoList) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put(StockMarketConstantsIF.Keys.TWEET_DESC_KEY, tweetInfo.getTweetDesc());
				paramMap.put(StockMarketConstantsIF.Keys.TWEET_USERID_KEY, tweetInfo.getUserId());
				paramMap.put(StockMarketConstantsIF.Keys.TWEET_HASHTAG_KEY, tweetInfo.getHashTag());
				paramMap.put(StockMarketConstantsIF.Keys.TWEET_LANG_KEY, tweetInfo.getLanguage());
				paramMap.put(StockMarketConstantsIF.Keys.TWEET_SCREENNAME_KEY, tweetInfo.getScreenName());
				paramMap.put(StockMarketConstantsIF.Keys.COMPANYID_KEY, tweetInfo.getCompanyId());
				namedJdbcTemplate.update(sql, paramMap);
			}
			insertTweetInfo.setStatus(true);
			return insertTweetInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			insertTweetInfo = new StatusInfo();
			insertTweetInfo.setErrMsg(e.getMessage());
			insertTweetInfo.setStatus(false);
			return insertTweetInfo;

		}
	}

	@Override
	public StatusInfo deletePolarityOrder() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.DELETE_POLARITYORDER_SQL, null, null);
			jdbcTemplate.update(sql);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public StatusInfo deleteSentimentIndexInfo() {
		StatusInfo statusInfo = new StatusInfo();
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.DELETE_SENTIMENTINDEX_SQL, null, null);
			Map<String, Object> paramMap = null;
			namedJdbcTemplate.update(sql, paramMap);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	@Override
	public List<Integer> retriveDistinctCompanyIdsFromTotalPolarity() {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_DISTINCTCOMPANYSIDS_FROM_TOTALPOLARITY_SQL, null, null);

			Map<String, Object> paramMap = null;

			return namedJdbcTemplate.queryForList(sql, paramMap, Integer.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	@Override
	public int retriveTotalPositiveRatingForCompanyFromSentimentTotal(Integer companyId) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALPOSITIVEPOLARITY_WHERE_COMPANYID_SQL, null, null);
			return jdbcTemplate.queryForInt(sql, companyId);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	@Override
	public int retriveTotalNegativeRatingForCompanyFromSentimentTotal(Integer companyId) {
		try {
			String sql = sqlProperties.getMessage(
					StockMarketConstantsIF.SQLS.RETRIVE_TOTALNEGATIVEPOLARITY_WHERE_COMPANYID_SQL, null, null);
			return jdbcTemplate.queryForInt(sql, companyId);

		} catch (Exception e) {
			System.out.println("Exception" + e);
			return -1;
		}
	}

	// INSERT INTO
	// SENTIMENTINDEX(SENTIMENTINDEX,SENTIMENTDISINDEX,POSITIVERATING,NEGATIVERATING,COMPANYID,COMPANY)
	// VALUES(:SENTIMENTINDEX,:SENTIMENTDISINDEX,:POSITIVERATING,:NEGATIVERATING,:COMPANYID,:COMPANY)
	@Override
	public StatusInfo insertSentimentIndex(List<SentimentIndexModel> sentimentIndexModelList) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_SENTIMENTINDEX_SQL, null, null);

			for (SentimentIndexModel sentimentIndexModel : sentimentIndexModelList) {

				Map<String, Object> paramMap = new HashMap<String, Object>();

				paramMap.put("SENTIMENTINDEX", sentimentIndexModel.getSentimentIndex());
				paramMap.put("SENTIMENTDISINDEX", sentimentIndexModel.getSentimentDescIndex());
				paramMap.put("POSITIVERATING", sentimentIndexModel.getPositiveRating());
				paramMap.put("NEGATIVERATING", sentimentIndexModel.getNegativeRating());
				paramMap.put("COMPANYID", sentimentIndexModel.getCompanyId());
				paramMap.put("COMPANY", sentimentIndexModel.getCompany());

				namedJdbcTemplate.update(sql, paramMap);

			}

			statusInfo.setStatus(true);
			return statusInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION" + e.getMessage());
			statusInfo = new StatusInfo();
			statusInfo.setErrMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
	}

	// SELECT
	// SENTIMENTINDEXID,SENTIMENTINDEX,SENTIMENTDISINDEX,POSITIVERATING,NEGATIVERATING,COMPANYID,COMPANY
	// FROM SENTIMENTINDEX
	@Override
	public List<SentimentIndexModel> retriveSentimentIndexModelList() {
		List<SentimentIndexModel> sentimentIndexModelList = null;
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_SENTIMENTINDEX_SQL, null, null);
			Map<String, Object> paramMap = null;

			return namedJdbcTemplate.query(sql, paramMap, new SentimentIndexMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class SentimentIndexMapper implements RowMapper<SentimentIndexModel> {

		public SentimentIndexModel mapRow(ResultSet rs, int arg1) throws SQLException {
			SentimentIndexModel sentimentIndexModel = new SentimentIndexModel();

			sentimentIndexModel.setCompany(rs.getString("COMPANY"));
			sentimentIndexModel.setCompanyId(rs.getInt("COMPANYID"));
			sentimentIndexModel.setNegativeRating(rs.getInt("NEGATIVERATING"));
			sentimentIndexModel.setPositiveRating(rs.getInt("POSITIVERATING"));
			sentimentIndexModel.setSentimentIndex(rs.getDouble("SENTIMENTINDEX"));
			sentimentIndexModel.setSentimentDescIndex(rs.getDouble("SENTIMENTDISINDEX"));
			sentimentIndexModel.setSentimentIndexId(rs.getInt("SENTIMENTINDEXID"));

			return sentimentIndexModel;
		}
	}

	@Override
	public List<StockDataProVO> retriveStockMarket() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_STOCKMARKET_SQL, null, null);
			Map<String, Object> paramMap = null;

			return namedJdbcTemplate.query(sql, paramMap, new StockDataMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class StockDataMapper implements RowMapper<StockDataProVO> {

		public StockDataProVO mapRow(ResultSet rs, int arg1) throws SQLException {
			StockDataProVO stockData = new StockDataProVO();

			stockData.setCompanyId(rs.getInt("COMPANYID"));
			stockData.setDailyTurnOver(rs.getDouble("DAILYTURNOVER"));
			stockData.setMarketIndex(rs.getDouble("MARKETINDEX"));
			stockData.setPrice(rs.getDouble("PRICE"));
			stockData.setVolume(rs.getDouble("VOLUME"));

			return stockData;
		}
	}

	@Override
	public StatusInfo deleteFromPrediction() {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.DELETE_PREDICTION_SQL, null, null);
			jdbcTemplate.update(sql);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	// INSERT INTO
	// PREDICTION(SDI,VOLUME,MARKETINDEX,DAILYTURNOVER,COMPANYID,PRICE,PREDICTION,COMPANY)
	// VALUES(:SDI,:VOLUME,:MARKETINDEX,:DAILYTURNOVER,:COMPANYID,:PRICE,:PREDICTION,:COMPANY)
	@Override
	public StatusInfo insertPredictionList(List<PredictionValue> predictionValues) {
		StatusInfo statusInfo = new StatusInfo();
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_PREDICTION_SQL, null, null);
			for (PredictionValue predictionValue : predictionValues) {

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("SDI", predictionValue.getSentimentDescIndex());
				paramMap.put("VOLUME", predictionValue.getVolume());
				paramMap.put("MARKETINDEX", predictionValue.getMarketIndex());
				paramMap.put("DAILYTURNOVER", predictionValue.getDailyTurnOver());
				paramMap.put("COMPANYID", predictionValue.getCompanyId());
				paramMap.put("PRICE", predictionValue.getPrice());
				paramMap.put("PREDICTION", predictionValue.getPredictionValue());
				paramMap.put("COMPANY", predictionValue.getCompany());

				namedJdbcTemplate.update(sql, paramMap);

			}
			statusInfo.setStatus(true);

		} catch (Exception e) {
			System.out.println("Exception is:");
			System.out.println(e.getMessage());
			e.printStackTrace();
			statusInfo.setErrMsg(StockMarketConstantsIF.Message.MESSAGE_INTERNAL);
			statusInfo.setExceptionMsg(e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;

		}
		return statusInfo;
	}

	@Override
	public List<PredictionValue> retrivePredictionValueList() {
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_PREDICTION_SQL, null, null);
			Map<String, Object> paramMap = null;

			return namedJdbcTemplate.query(sql, paramMap, new PredictionValueMapper());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			return null;
		}
	}

	private final class PredictionValueMapper implements RowMapper<PredictionValue> {

		public PredictionValue mapRow(ResultSet rs, int arg1) throws SQLException {
			PredictionValue predictionValue = new PredictionValue();

			predictionValue.setCompanyId(rs.getInt("COMPANYID"));
			predictionValue.setDailyTurnOver(rs.getDouble("DAILYTURNOVER"));
			predictionValue.setMarketIndex(rs.getDouble("MARKETINDEX"));
			predictionValue.setPrice(rs.getDouble("PRICE"));
			predictionValue.setVolume(rs.getDouble("VOLUME"));
			predictionValue.setSentimentDescIndex(rs.getDouble("SDI"));
			predictionValue.setPredictionValue(rs.getDouble("PREDICTION"));
			predictionValue.setCompany(rs.getString("COMPANY"));

			return predictionValue;
		}
	}

	@Override
	public List<StockDataProVO> retriveStockDataList() {
		return retriveStockMarket();
	}

	@Override
	public List<CompanyInfo> retriveSpecficCompanyInfoFromFinance() {
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_COMPANYINFO_FROM_FINANCE_SQL,
					null, null);
			return jdbcTemplate.query(sql, new YahooFinanceMapper());
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	private final class YahooFinanceMapper implements RowMapper<CompanyInfo> {

		public CompanyInfo mapRow(ResultSet rs, int arg1) throws SQLException {

			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyId(rs.getInt("COMPANYID"));
			companyInfo.setCompanyName(rs.getString("COMPANYNAME"));
			companyInfo.setYahooFinanceName(rs.getString("FINANCENAME"));

			return companyInfo;

		}

	}

	@Override
	public StatusInfo checkCompanyExistInStockData(int companyId) {
		StatusInfo statusInfo = new StatusInfo();
		try {
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_STOCKMARKET_WHERE_COMPANYID_SQL,
					null, null);
			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("COMPANYID", companyId);

			StockDataProVO stockData = namedJdbcTemplate.queryForObject(sql, paramMap, new StockDataMapper());

			if (null == stockData) {
				statusInfo.setStatus(false);
				return statusInfo;
			} else {

				int companyIdValue = stockData.getCompanyId();
				if (companyIdValue > 0) {
					statusInfo.setStatus(true);
					return statusInfo;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION ----->" + e.getMessage());
			statusInfo.setStatus(false);
			return statusInfo;
		}

		statusInfo.setStatus(true);
		return null;
	}

	@Override
	public String retriveFinanceKeyForCompanyId(int companyId) {
		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_FINANCEKEY_FOR_COMPANYID, null,
					null);
			return jdbcTemplate.queryForObject(sql, String.class, companyId);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			return null;
		}
	}

	@Override
	public StatusInfo deleteFromStockData(int companyId) {
		StatusInfo statusInfo = null;

		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.DELETE_STOCKDATA_WHERE_COMPANYID_SQL,
					null, null);
			jdbcTemplate.update(sql, companyId);
			statusInfo.setStatus(true);
			return statusInfo;
		} catch (Exception e) {
			statusInfo = new StatusInfo();
			statusInfo.setStatus(false);
			statusInfo.setErrMsg(e.getMessage());
			System.out.println("Exception" + e);
			e.printStackTrace();
			return statusInfo;
		}
	}

	// INSERT INTO STOCKDATA(VOLUME,MARKETINDEX,DAILYTURNOVER,COMPANYID,PRICE)
	// VALUES(:VOLUME,:MARKETINDEX,:DAILYTURNOVER,:COMPANYID,:PRICE)
	@Override
	public StatusInfo insertStockData(StockDataProVO stockData) {
		StatusInfo statusInfo = null;
		try {
			statusInfo = new StatusInfo();
			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.INSERT_STOCKDATA_SQL, null, null);

			Map<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("VOLUME", stockData.getVolume());
			paramMap.put("MARKETINDEX", stockData.getMarketIndex());
			paramMap.put("DAILYTURNOVER", stockData.getDailyTurnOver());
			paramMap.put("COMPANYID", stockData.getCompanyId());
			paramMap.put("PRICE", stockData.getPrice());

			namedJdbcTemplate.update(sql, paramMap);

			statusInfo.setStatus(true);
			return statusInfo;

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
	public StatusInfo retriveAllReviewsForPaginationConfig(PaginationConfigVO paginationConfigVO) {
		StatusInfo statusInfo = new StatusInfo();

		try {

			String sql = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_REVIEWS_PAGINATED_SQL, null,
					null);
			List<ReviewModel> reviewModels = jdbcTemplate.query(sql, new ReviewModelVOMapper(),
					new Object[] { paginationConfigVO.getLimit(), paginationConfigVO.getStart() });

			if (null == reviewModels) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;

			}
			// Once it is done get the total as well
			String sql1 = sqlProperties.getMessage(StockMarketConstantsIF.SQLS.RETRIVE_TOTAL_COUNT_FOR_TWEETS_SQL, null,
					null);
	
			int total = jdbcTemplate.queryForInt(sql1);

			if (total <= 0) {
				statusInfo.setStatus(false);
				statusInfo.setErrMsg("No Reviews Found");
				return statusInfo;
			}

			statusInfo.setStatus(true);
			statusInfo.setModel(reviewModels);
			statusInfo.setTotal(total);
		} catch (Exception e) {
			System.out.println("Exception" + e);
			statusInfo.setStatus(false);
			statusInfo.setExceptionMsg(e.getMessage());
			return statusInfo;
		}
		return statusInfo;
	}

}
