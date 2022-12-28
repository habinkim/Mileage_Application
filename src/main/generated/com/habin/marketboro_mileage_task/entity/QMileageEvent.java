package com.habin.marketboro_mileage_task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileageEvent is a Querydsl query type for MileageEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileageEvent extends EntityPathBase<MileageEvent> {

    private static final long serialVersionUID = -809935853L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageEvent mileageEvent = new QMileageEvent("mileageEvent");

    public final com.habin.marketboro_mileage_task.entity.base.QMileageBaseEntity _super;

    // inherited
    public final QMember member;

    public final ListPath<MileageDetail, QMileageDetail> mileageDetail = this.<MileageDetail, QMileageDetail>createList("mileageDetail", MileageDetail.class, QMileageDetail.class, PathInits.DIRECT2);

    public final StringPath mileageEventId = createString("mileageEventId");

    //inherited
    public final EnumPath<com.habin.marketboro_mileage_task.entity.enums.MileageStatus> mileageStatus;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> remainMileageExpireDtm;

    //inherited
    public final NumberPath<Integer> sum;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> transactionDtm;

    public QMileageEvent(String variable) {
        this(MileageEvent.class, forVariable(variable), INITS);
    }

    public QMileageEvent(Path<? extends MileageEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileageEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileageEvent(PathMetadata metadata, PathInits inits) {
        this(MileageEvent.class, metadata, inits);
    }

    public QMileageEvent(Class<? extends MileageEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.habin.marketboro_mileage_task.entity.base.QMileageBaseEntity(type, metadata, inits);
        this.member = _super.member;
        this.mileageStatus = _super.mileageStatus;
        this.remainMileageExpireDtm = _super.remainMileageExpireDtm;
        this.sum = _super.sum;
        this.transactionDtm = _super.transactionDtm;
    }

}

