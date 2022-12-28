package com.habin.marketboro_mileage_task.mileage_detail.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeDeserializer;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeSerializer;
import com.habin.marketboro_mileage_task.entity.enums.MileageStatus;
import com.habin.marketboro_mileage_task.member.entity.Member;
import com.habin.marketboro_mileage_task.mileage_event.entity.MileageEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* @description : 적립금 상세 Entity 클래스
* @!
* @?
* @TODO
* @Date : 2022-12-28, 수, 21;21
*/
@Builder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class MileageDetail {

    @Id
    @Column(length = 30)
    @Comment("마일리지 상세 아이디")
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "mileageEventId", referencedColumnName = "id")
    @Comment("적립금 이벤트 아이디")
    private MileageEvent mileageEvent;

    @Column(nullable = false, length = 30)
    @Comment("취소 원본 상세 아이디")
    private String cancelMileageDetailId;

    @Column(nullable = false, length = 30)
    @Comment("적립 원본 상세 아이디")
    private String saveMileageDetailId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "memberNo", referencedColumnName = "memberNo")
    @Comment("회원 번호")
    private Member member;

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
