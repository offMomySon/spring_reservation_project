package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import static kr.or.connect.reservation.sql.ReservationSql.*;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationRequestRs;
import kr.or.connect.reservation.dto.ReservationResponseRs;
import kr.or.connect.reservation.dto.Ticket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ReservationDao {

	private SimpleJdbcInsert reservationInfoInsert;
	private SimpleJdbcInsert reservationInfoPriceInsert;
	private NamedParameterJdbcTemplate jdbcTemplate;

	private RowMapper<ReservationResponseRs> rsvResponseMapper = BeanPropertyRowMapper
			.newInstance(ReservationResponseRs.class);
	private RowMapper<ReservationRequestRs> rsvRequestMapper = BeanPropertyRowMapper
			.newInstance(ReservationRequestRs.class);
	private RowMapper<Price> priceMapper = BeanPropertyRowMapper.newInstance(Price.class);
	private RowMapper<Ticket> ticketMapper = BeanPropertyRowMapper.newInstance(Ticket.class);

	public ReservationDao(DataSource dataSource) {
		reservationInfoInsert = new SimpleJdbcInsert(dataSource).withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
		reservationInfoPriceInsert = new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Long insertRservationInfo(ReservationRequestRs reservationRequestRs) {
		log.info("insert RservationInfo = {}", reservationRequestRs);

		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(reservationRequestRs);
		return reservationInfoInsert.executeAndReturnKey(parameterSource).longValue();
	}

	public ReservationRequestRs selectRsvInfoAtId(Long rsvId) {
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

	public List<ReservationResponseRs> selectAtEmail(String email) {
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
