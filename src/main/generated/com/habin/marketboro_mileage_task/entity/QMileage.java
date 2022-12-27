package com.habin.marketboro_mileage_task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileage is a Querydsl query type for Mileage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileage extends EntityPathBase<Mileage> {

    private static final long serialVersionUID = -900937017L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileage mileage = new QMileage("mileage");

    public final com.habin.marketboro_mileage_task.entity.base.QBaseTimeEntity _super = new com.habin.marketboro_mileage_task.entity.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> insDtm = _super.insDtm;

    public final QMember member;

    public final NumberPath<Long> mileageIdx = createNumber("mileageIdx", Long.class);

    public final EnumPath<com.habin.marketboro_mileage_task.entity.enums.MileageType> mileageType = createEnum("mileageType", com.habin.marketboro_mileage_task.entity.enums.MileageType.class);

    public final NumberPath<Integer> sum = createNumber("sum", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDtm = _super.updDtm;

    public QMileage(String variable) {
        this(Mileage.class, forVariable(variable), INITS);
    }

    public QMileage(Path<? extends Mileage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileage(PathMetadata metadata, PathInits inits) {
        this(Mileage.class, metadata, inits);
    }

    public QMileage(Class<? extends Mileage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

