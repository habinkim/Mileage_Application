package com.habin.mileage.member.entity;

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

    private static final long serialVersionUID = 228645250L;

    public static final QMember member = new QMember("member1");

    public final StringPath memberNm = createString("memberNm");

    public final ComparablePath<java.util.UUID> memberNo = createComparable("memberNo", java.util.UUID.class);

    public final DateTimePath<java.time.LocalDateTime> registerDtm = createDateTime("registerDtm", java.time.LocalDateTime.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

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

