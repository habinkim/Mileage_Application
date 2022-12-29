package com.habin.marketboro_mileage_task.mileage_event.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeDeserializer;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeSerializer;
import com.habin.marketboro_mileage_task.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
* @author : 김하빈(danny9643@naver.com)
* @description : 적립금 이력 Entity 클래스 <p>
* ! <p>
* ? <p>
* TODO <p>
* @Date : 2022-12-26, Mon, 23;6
*/
@EntityListeners(AuditingEntityListener.class)
@Builder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class MileageEvent {

    @Id
    @Column(length = 30)
    @Comment("적립금 이력 아이디")
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "memberNo", referencedColumnName = "memberNo")
    @Comment("회원 번호")
    private Member member;

    @Column(nullable = false, length = 15)
    @Comment("거래 상태")
    private MileageStatus mileageStatus;

    @Column(nullable = false, length = 6)
    @Comment("거래 금액")
    private Integer amount;

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
