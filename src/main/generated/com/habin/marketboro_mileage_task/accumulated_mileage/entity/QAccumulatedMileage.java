package com.habin.marketboro_mileage_task.accumulated_mileage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccumulatedMileage is a Querydsl query type for AccumulatedMileage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccumulatedMileage extends EntityPathBase<AccumulatedMileage> {

    private static final long serialVersionUID = -1154174166L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccumulatedMileage accumulatedMileage = new QAccumulatedMileage("accumulatedMileage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.habin.marketboro_mileage_task.member.entity.QMember member;

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public QAccumulatedMileage(String variable) {
        this(AccumulatedMileage.class, forVariable(variable), INITS);
    }

    public QAccumulatedMileage(Path<? extends AccumulatedMileage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccumulatedMileage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccumulatedMileage(PathMetadata metadata, PathInits inits) {
        this(AccumulatedMileage.class, metadata, inits);
    }

    public QAccumulatedMileage(Class<? extends AccumulatedMileage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.habin.marketboro_mileage_task.member.entity.QMember(forProperty("member")) : null;
    }

}

