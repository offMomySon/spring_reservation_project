package kr.or.connect.reservation.infrastructure.dao;

import kr.or.connect.reservation.core.presentation.dto.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.infrastructure.sql.DisplayInfoSql.*;

@Repository
public class DisplayInfoDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<DisplayInfoResult> displayInfoMapper = BeanPropertyRowMapper.newInstance(DisplayInfoResult.class);
    private RowMapper<ProductImageResult> productImageMapper = BeanPropertyRowMapper.newInstance(ProductImageResult.class);
    private RowMapper<DisplayInfoImageResult> displayInfoImagMapper = BeanPropertyRowMapper
            .newInstance(DisplayInfoImageResult.class);
    private RowMapper<ProductPriceResult> productPriceMapper = BeanPropertyRowMapper.newInstance(ProductPriceResult.class);
    private RowMapper<CommentResult> commentMapper = BeanPropertyRowMapper.newInstance(CommentResult.class);
    private RowMapper<CommentImageResult> commentImageMapper = BeanPropertyRowMapper.newInstance(CommentImageResult.class);

    public DisplayInfoDao(@Qualifier("dataSource") DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public DisplayInfoResult selectDisplayInfo(long displayInfoId) {
        Map<String, Long> paramMap = new HashMap<String, Long>();
        paramMap.put("displayInfoId", displayInfoId);

        return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO, paramMap, displayInfoMapper);
    }

    public List<ProductImageResult> selectProductImage(long displayInfoId, long limit) {
        Map<String, Long> paramMap = new HashMap<String, Long>();
        paramMap.put("displayInfoId", displayInfoId);
        paramMap.put("limit", limit);

        return jdbcTemplate.query(SELECT_PRODUCT_IMAGE, paramMap, productImageMapper);
    }

    public DisplayInfoImageResult selectDisplayInfoImage(long displayInfoId) {
        Map<String, Long> paramMap = new HashMap<String, Long>();
        paramMap.put("displayInfoId", displayInfoId);

        return jdbcTemplate.queryForObject(SELECT_DISPLAY_INFO_IMAGE, paramMap, displayInfoImagMapper);
    }

    public List<ProductPriceResult> selectProductPrice(long displayInfoId) {
        Map<String, Long> paramMap = new HashMap<String, Long>();
        paramMap.put("displayInfoId", displayInfoId);

        return jdbcTemplate.query(SELECT_PRODUCT_PRICE, paramMap, productPriceMapper);
    }

    public List<CommentResult> selectComment(long displayInfoId) {
        Map<String, Long> paramMap = new HashMap<String, Long>();
        paramMap.put("displayInfoId", displayInfoId);

        return jdbcTemplate.query(SELECT_COMMENT, paramMap, commentMapper);
    }

    public List<CommentImageResult> selectCommentImage(long userCommnetId) {
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
