package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.sql.DisplayInfoSql.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.CommentRs;
import kr.or.connect.reservation.dto.CommentImageRs;
import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.DisplayInfoImageRs;
import kr.or.connect.reservation.dto.ProductImageRs;
import kr.or.connect.reservation.dto.ProductPriceRs;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	private RowMapper<DisplayInfoRs> displayInfoMapper = BeanPropertyRowMapper.newInstance(DisplayInfoRs.class);
	private RowMapper<ProductImageRs> productImageMapper = BeanPropertyRowMapper.newInstance(ProductImageRs.class);
	private RowMapper<DisplayInfoImageRs> displayInfoImagMapper = BeanPropertyRowMapper
			.newInstance(DisplayInfoImageRs.class);
	private RowMapper<ProductPriceRs> productPriceMapper = BeanPropertyRowMapper.newInstance(ProductPriceRs.class);
	private RowMapper<CommentRs> commentMapper = BeanPropertyRowMapper.newInstance(CommentRs.class);
	private RowMapper<CommentImageRs> commentImageMapper = BeanPropertyRowMapper.newInstance(CommentImageRs.class);

	public DisplayInfoDao(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfoRs selectDisplayInfo(long displayInfoId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("displayInfoId", displayInfoId);

		return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO, paramMap, displayInfoMapper);
	}

	public List<ProductImageRs> selectProductImage(long displayInfoId, long limit) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("displayInfoId", displayInfoId);
		paramMap.put("limit", limit);

		return jdbcTemplate.query(SELECT_PRODUCT_IMAGE, paramMap, productImageMapper);
	}

	public DisplayInfoImageRs selectDisplayInfoImage(long displayInfoId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("displayInfoId", displayInfoId);

		return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO_IMAGE, paramMap, displayInfoImagMapper);
	}

	public List<ProductPriceRs> selectProductPrice(long displayInfoId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("displayInfoId", displayInfoId);

		return jdbcTemplate.query(SELECT_PRODUCT_PRICE, paramMap, productPriceMapper);
	}

	public List<CommentRs> selectComment(long displayInfoId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("displayInfoId", displayInfoId);

		return jdbcTemplate.query(SELECT_COMMENT, paramMap, commentMapper);
	}

	public List<CommentImageRs> selectCommentImage(long userCommnetId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("userCommnetId", userCommnetId);

		return jdbcTemplate.query(SELECT_COMMENT_IMAGE, paramMap, commentImageMapper);
	}

	public List<Double> selectScore(long displayInfoId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("displayInfoId", displayInfoId);

		return jdbcTemplate.queryForList(SELECT_SCORE, paramMap, Double.class);
	}
}
