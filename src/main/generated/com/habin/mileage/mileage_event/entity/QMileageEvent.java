package com.habin.mileage.mileage_event.entity;

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

    private static final long serialVersionUID = 67407009L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageEvent mileageEvent = new QMileageEvent("mileageEvent");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath id = createString("id");

    public final com.habin.mileage.member.entity.QMember member;

    public final EnumPath<com.habin.mileage.common.MileageStatus> mileageStatus = createEnum("mileageStatus", com.habin.mileage.common.MileageStatus.class);

    public final DateTimePath<java.time.LocalDateTime> remainMileageExpireDtm = createDateTime("remainMileageExpireDtm", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> transactionDtm = createDateTime("transactionDtm", java.time.LocalDateTime.class);

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
        this.member = inits.isInitialized("member") ? new com.habin.mileage.member.entity.QMember(forProperty("member")) : null;
    }

}

