package com.habin.marketboro_mileage_task.entity;

import com.habin.marketboro_mileage_task.entity.base.MileageBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* @description : 적립금 상세 Entity 클래스
* @!
* @?
* @TODO
* @Date : 2022-12-28, 수, 21;21
*/
@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class MileageDetail extends MileageBaseEntity {

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

}
