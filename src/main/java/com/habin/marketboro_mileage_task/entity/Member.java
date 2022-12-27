package com.habin.marketboro_mileage_task.entity;

import com.habin.marketboro_mileage_task.entity.base.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(danny9643@naver.com)
* @description : Member(사용자) Entity 클래스<p>
* ! <p>
* ? <p>
* TODO
* @Date : 2022-12-26, Mon, 18;0
*/
@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Member extends BaseTimeEntity {

    @Id
    @Column(length = 50)
    private String memberNo;

    @Column(nullable = false, length = 10)
    private String memberNm;

    @Column(nullable = false, length = 10)
    private Integer totalMileageAmount;

}
