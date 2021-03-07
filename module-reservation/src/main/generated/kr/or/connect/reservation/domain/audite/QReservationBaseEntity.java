package kr.or.connect.reservation.domain.audite;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationBaseEntity is a Querydsl query type for ReservationBaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QReservationBaseEntity extends EntityPathBase<ReservationBaseEntity> {

    private static final long serialVersionUID = -602581892L;

    public static final QReservationBaseEntity reservationBaseEntity = new QReservationBaseEntity("reservationBaseEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final DateTimePath<java.util.Date> reservationDate = createDateTime("reservationDate", java.util.Date.class);

    public QReservationBaseEntity(String variable) {
        super(ReservationBaseEntity.class, forVariable(variable));
    }

    public QReservationBaseEntity(Path<? extends ReservationBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationBaseEntity(PathMetadata metadata) {
        super(ReservationBaseEntity.class, metadata);
    }

}

