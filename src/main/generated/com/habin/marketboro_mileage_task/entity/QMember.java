package com.habin.marketboro_mileage_task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1768385789L;

    public static final QMember member = new QMember("member1");

    public final com.habin.marketboro_mileage_task.entity.base.QBaseTimeEntity _super = new com.habin.marketboro_mileage_task.entity.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> insDtm = _super.insDtm;

    public final StringPath memberNm = createString("memberNm");

    public final StringPath memberNo = createString("memberNo");

    public final NumberPath<Integer> totalMileageAmount = createNumber("totalMileageAmount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDtm = _super.updDtm;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

