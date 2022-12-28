package com.habin.marketboro_mileage_task.entity.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileageBaseEntity is a Querydsl query type for MileageBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QMileageBaseEntity extends EntityPathBase<MileageBaseEntity> {

    private static final long serialVersionUID = -729463612L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageBaseEntity mileageBaseEntity = new QMileageBaseEntity("mileageBaseEntity");

    public final com.habin.marketboro_mileage_task.entity.QMember member;

    public final EnumPath<com.habin.marketboro_mileage_task.entity.enums.MileageStatus> mileageStatus = createEnum("mileageStatus", com.habin.marketboro_mileage_task.entity.enums.MileageStatus.class);

    public final DateTimePath<java.time.LocalDateTime> remainMileageExpireDtm = createDateTime("remainMileageExpireDtm", java.time.LocalDateTime.class);

    public final NumberPath<Integer> sum = createNumber("sum", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> transactionDtm = createDateTime("transactionDtm", java.time.LocalDateTime.class);

    public QMileageBaseEntity(String variable) {
        this(MileageBaseEntity.class, forVariable(variable), INITS);
    }

    public QMileageBaseEntity(Path<? extends MileageBaseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileageBaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileageBaseEntity(PathMetadata metadata, PathInits inits) {
        this(MileageBaseEntity.class, metadata, inits);
    }

    public QMileageBaseEntity(Class<? extends MileageBaseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.habin.marketboro_mileage_task.entity.QMember(forProperty("member")) : null;
    }

}

