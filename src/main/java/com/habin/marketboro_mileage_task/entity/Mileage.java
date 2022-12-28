package com.habin.marketboro_mileage_task.entity;

import jakarta.persistence.*;
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
public class Mileage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("적립금 내역 번호")
    private Long mileageIdx;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Comment("적립금 내역 타입")
    private MileageType mileageType;

    @Column(nullable = false, length = 6)
    @Comment("적립금 적립/사용 금액")
    private Integer sum;

    @ManyToOne
    @JoinColumn(nullable = false, name = "memberNo", referencedColumnName = "memberNo")
    @Comment("적립금 적립/사용 회원 번호")
    private Member member;


}
