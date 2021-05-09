package kr.or.connect.reservation.infrastructure.dao;

import kr.or.connect.reservation.core.presentation.dto.Price;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import kr.or.connect.reservation.core.presentation.dto.ReservationResponseResult;
import kr.or.connect.reservation.core.presentation.dto.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.infrastructure.sql.ReservationSql.*;

@Slf4j
@Repository
public class ReservationDao {

    private SimpleJdbcInsert reservationInfoInsert;
    private SimpleJdbcInsert reservationInfoPriceInsert;
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<ReservationResponseResult> rsvResponseMapper = BeanPropertyRowMapper
            .newInstance(ReservationResponseResult.class);
    private RowMapper<ReservationRequestResult> rsvRequestMapper = BeanPropertyRowMapper
            .newInstance(ReservationRequestResult.class);
    private RowMapper<Price> priceMapper = BeanPropertyRowMapper.newInstance(Price.class);
    private RowMapper<Ticket> ticketMapper = BeanPropertyRowMapper.newInstance(Ticket.class);

    public ReservationDao(@Qualifier("dataSource") DataSource dataSource) {
        reservationInfoInsert = new SimpleJdbcInsert(dataSource).withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
        reservationInfoPriceInsert = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price")
                .usingGeneratedKeyColumns("id");
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long insertRservationInfo(ReservationRequestResult reservationRequestResult) {
        log.info("insert RservationInfo = {}", reservationRequestResult);

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(reservationRequestResult);
        return reservationInfoInsert.executeAndReturnKey(parameterSource).longValue();
    }

    public ReservationRequestResult selectRsvInfoAtId(Long rsvId) {
        Map<String, Long> paramMap = new HashMap();
        paramMap.put("rsvId", rsvId);

        return jdbcTemplate.queryForObject(SELECT_RSV_INFO_AT_RSVID, paramMap, rsvRequestMapper);
    }

    public List<Price> selectPriceAtRsvId(Long rsvId) {
        Map<String, Long> paramMap = new HashMap();
        paramMap.put("rsvId", rsvId);

        return jdbcTemplate.query(SELECT_PRICE_AT_RSVID, paramMap, priceMapper);
    }

    public Long insertReservationInfoPrice(Price price) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(price);
        return reservationInfoPriceInsert.executeAndReturnKey(parameterSource).longValue();
    }

    public List<ReservationResponseResult> selectAtEmail(String email) {
        log.info("select Email = {}", email);

        Map<String, String> paramMap = new HashMap();
        paramMap.put("email", email);

        return jdbcTemplate.query(SELECT_ALL_AT_EMAIL, paramMap, rsvResponseMapper);
    }

    public List<Ticket> selectTicketAtRsvInfoId(Long rsvInfoId) {
        Map<String, Long> paramMap = new HashMap();
        paramMap.put("rsvInfoId", rsvInfoId);

        return jdbcTemplate.query(SELECT_ALL_TICKET_AT_RSVINFOID, paramMap, ticketMapper);
    }

    public int cancleRsvAtId(Long rsvId) {
        log.info("cancel reservation Id = {}", rsvId);

        Map<String, Long> paramMap = new HashMap();
        paramMap.put("rsvId", rsvId);

        return jdbcTemplate.update(UPDATE_RSV_INFO_AT_RSVID, paramMap);
    }

    public int deleteRsvInfoPriceByRsvId(Long rsvInfoId) {
        Map<String, Long> paramMap = new HashMap();
        paramMap.put("rsvInfoId", rsvInfoId);

        return jdbcTemplate.update(DELETE_RSV_INFO_PRICE_AT_RSVID, paramMap);
    }
}
