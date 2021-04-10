package kr.or.connect.reservation.infrastructure.sql;

public class ReservationSql {
    public final static String SELECT_ALL_AT_EMAIL = "SELECT id as reservationInfoId, product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, cancel_flag, create_date, modify_date FROM connectdb.reservation_info as rsv_info where reservation_email = :email";

    public final static String SELECT_RSV_INFO_AT_RSVID =
            "SELECT "
                    + "id as reservation_info_id, "
                    + "product_id, "
                    + "display_info_id, "
                    + "reservation_name, "
                    + "reservation_tel, "
                    + "reservation_email, "
                    + "reservation_date, "
                    + "cancel_flag, "
                    + "create_date, "
                    + "modify_date "
                    + "FROM connectdb.reservation_info "
                    + "	where id = :rsvId";

    public final static String SELECT_PRICE_AT_RSVID = "SELECT id as reservation_info_price_id, reservation_info_id, product_price_id, count FROM connectdb.reservation_info_price where reservation_info_id = :rsvId";

    public final static String SELECT_ALL_TICKET_AT_RSVINFOID =
            "SELECT "
                    + "rip.count as count, "
                    + "pp.price as price "
                    + "FROM connectdb.reservation_info_price as rip "
                    + "join product_price as pp on pp.id = rip.product_price_id "
                    + "where reservation_info_id = :rsvInfoId";

    public final static String DELETE_RSV_INFO_PRICE_AT_RSVID = "DELETE FROM reservation_info_price WHERE reservation_info_id = :rsvInfoId";

    public final static String UPDATE_RSV_INFO_AT_RSVID = "UPDATE reservation_info set cancel_flag = true WHERE id = :rsvId";
}
