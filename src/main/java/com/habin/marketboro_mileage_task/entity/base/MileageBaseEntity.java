package com.habin.marketboro_mileage_task.entity.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.marketboro_mileage_task.entity.Member;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.module.json.CustomLocalDateTimeDeserializer;
import com.habin.marketboro_mileage_task.module.json.CustomLocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@ToString
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MileageBaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false, name = "memberNo", referencedColumnName = "memberNo")
    @Comment("회원 번호")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Comment("상태")
    private MileageStatus mileageStatus;

    @Column(nullable = false, length = 6)
    @Comment("거래 적립금")
    private Integer sum;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("거래 일시")
    private LocalDateTime transactionDtm;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @Column(nullable = false)
    @Comment("잔여 적립금 만료 일시")
    private LocalDateTime remainMileageExpireDtm;

}
