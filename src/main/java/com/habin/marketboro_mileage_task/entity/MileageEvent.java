package com.habin.marketboro_mileage_task.entity;

import com.habin.marketboro_mileage_task.entity.base.MileageBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;


/**
* @author : 김하빈(danny9643@naver.com)
* @description : 적립금 내역 Entity 클래스 <p>
* ! <p>
* ? <p>
* TODO <p>
* @Date : 2022-12-26, Mon, 23;6
*/
@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class MileageEvent extends MileageBaseEntity {

    @Id
    @Column(length = 30)
    @Comment("적립금 이벤트 아이디")
    private String mileageEventId;

}
