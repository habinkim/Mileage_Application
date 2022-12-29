package com.habin.marketboro_mileage_task.mileage_detail.entity;

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

    private static final long serialVersionUID = -809681360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageDetail mileageDetail = new QMileageDetail("mileageDetail");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath cancelMileageDetailId = createString("cancelMileageDetailId");

    public final StringPath id = createString("id");

    public final com.habin.marketboro_mileage_task.member.entity.QMember member;

    public final com.habin.marketboro_mileage_task.mileage_event.entity.QMileageEvent mileageEvent;

    public final EnumPath<com.habin.marketboro_mileage_task.common.MileageStatus> mileageStatus = createEnum("mileageStatus", com.habin.marketboro_mileage_task.common.MileageStatus.class);

    public final DateTimePath<java.time.LocalDateTime> remainMileageExpireDtm = createDateTime("remainMileageExpireDtm", java.time.LocalDateTime.class);

    public final StringPath saveMileageDetailId = createString("saveMileageDetailId");

    public final DateTimePath<java.time.LocalDateTime> transactionDtm = createDateTime("transactionDtm", java.time.LocalDateTime.class);

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
        this.member = inits.isInitialized("member") ? new com.habin.marketboro_mileage_task.member.entity.QMember(forProperty("member")) : null;
        this.mileageEvent = inits.isInitialized("mileageEvent") ? new com.habin.marketboro_mileage_task.mileage_event.entity.QMileageEvent(forProperty("mileageEvent"), inits.get("mileageEvent")) : null;
    }

}

