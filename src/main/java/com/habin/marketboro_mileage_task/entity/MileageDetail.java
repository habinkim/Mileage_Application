package com.habin.marketboro_mileage_task.entity;

import com.habin.marketboro_mileage_task.entity.base.MileageBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

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
    private String mileageDetailId;

    @Column(nullable = false, length = 30)
    @Comment("취소 원본 상세 아이디")
    private String cancelMileageDetailId;

    @Column(nullable = false, length = 30)
    @Comment("적립 원본 상세 아이디")
    private String saveMileageDetailId;

    @ManyToOne
    @JoinColumn(nullable = false, name = "mileageEventId", referencedColumnName = "mileageEventId")
    @Comment("적립금 내역 번호")
    private MileageEvent mileageEvent;

}
