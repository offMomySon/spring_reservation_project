package kr.or.connect.reservation.infrastructure.dao;

import kr.or.connect.reservation.core.presentation.dto.CategoryResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import static kr.or.connect.reservation.infrastructure.sql.CategorySql.SELECT_ALL_WITH_COUNT;

@Repository
public class CategoryDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<CategoryResult> rowMapper = BeanPropertyRowMapper.newInstance(CategoryResult.class);

    public CategoryDao(@Qualifier("dataSource") DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<CategoryResult> selectAll() {
        try {
            return jdbc.query(SELECT_ALL_WITH_COUNT, Collections.emptyMap(), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
