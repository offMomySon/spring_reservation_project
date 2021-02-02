package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.ProductRs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.sql.ProductSql.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<ProductRs> rowMapper = BeanPropertyRowMapper.newInstance(ProductRs.class);


	public ProductDao(@Qualifier("dataSource") DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductRs> selectAll(Long start, Long limit) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("start", start);
		paramMap.put("end", limit);

		return jdbcTemplate.query(SELECT_ALL_PRODUCT, paramMap, rowMapper);
	}

	public List<ProductRs> selectAllAtCategory(long categoryId, long start, long limit) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("start", start);
		paramMap.put("end", limit);
		paramMap.put("categoryId", categoryId);

		return jdbcTemplate.query(SELECT_SPECIFIC_CATEGORY_PRODUCT, paramMap, rowMapper);
	}

	public long selectCount() {
		return jdbcTemplate.queryForObject(SELECT_COUNT, Collections.emptyMap(), Long.class);
	}

	public long selectCountAtCategory(long categoryId) {
		Map<String, Long> paramMap = new HashMap<String, Long>();
		paramMap.put("categoryId", categoryId);

		return jdbcTemplate.queryForObject(SELECT_COUNT_SPECIFIC_CATEGORY_PRODUCT, paramMap, Long.class);
	}
}
