package kr.or.connect.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDisplayInfo is a Querydsl query type for DisplayInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDisplayInfo extends EntityPathBase<DisplayInfo> {

    private static final long serialVersionUID = -652061744L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDisplayInfo displayInfo = new QDisplayInfo("displayInfo");

    public final kr.or.connect.reservation.domain.audite.QBaseEntity _super = new kr.or.connect.reservation.domain.audite.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath openingHours = createString("openingHours");

    public final StringPath placeLot = createString("placeLot");

    public final StringPath placeName = createString("placeName");

    public final StringPath placeStreet = createString("placeStreet");

    public final QProduct product;

    public final StringPath tel = createString("tel");

    public QDisplayInfo(String variable) {
        this(DisplayInfo.class, forVariable(variable), INITS);
    }

    public QDisplayInfo(Path<? extends DisplayInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDisplayInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDisplayInfo(PathMetadata metadata, PathInits inits) {
        this(DisplayInfo.class, metadata, inits);
    }

    public QDisplayInfo(Class<? extends DisplayInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

