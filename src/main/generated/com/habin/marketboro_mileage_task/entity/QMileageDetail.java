package com.habin.marketboro_mileage_task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileageDetail is a Querydsl query type for MileageDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileageDetail extends EntityPathBase<MileageDetail> {

    private static final long serialVersionUID = 617897464L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageDetail mileageDetail = new QMileageDetail("mileageDetail");

    public final com.habin.marketboro_mileage_task.entity.base.QMileageBaseEntity _super;

    public final StringPath cancelMileageDetailId = createString("cancelMileageDetailId");

    // inherited
    public final QMember member;

    public final StringPath mileageDetailId = createString("mileageDetailId");

    public final QMileageEvent mileageEvent;

    //inherited
    public final EnumPath<com.habin.marketboro_mileage_task.entity.enums.MileageStatus> mileageStatus;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> remainMileageExpireDtm;

    public final StringPath saveMileageDetailId = createString("saveMileageDetailId");

    //inherited
    public final NumberPath<Integer> sum;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> transactionDtm;

    public QMileageDetail(String variable) {
        this(MileageDetail.class, forVariable(variable), INITS);
    }

    public QMileageDetail(Path<? extends MileageDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileageDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileageDetail(PathMetadata metadata, PathInits inits) {
        this(MileageDetail.class, metadata, inits);
    }

    public QMileageDetail(Class<? extends MileageDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.habin.marketboro_mileage_task.entity.base.QMileageBaseEntity(type, metadata, inits);
        this.member = _super.member;
        this.mileageEvent = inits.isInitialized("mileageEvent") ? new QMileageEvent(forProperty("mileageEvent"), inits.get("mileageEvent")) : null;
        this.mileageStatus = _super.mileageStatus;
        this.remainMileageExpireDtm = _super.remainMileageExpireDtm;
        this.sum = _super.sum;
        this.transactionDtm = _super.transactionDtm;
    }

}

