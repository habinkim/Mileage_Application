package com.habin.marketboro_mileage_task.accumulated_mileage.entity;

import com.habin.marketboro_mileage_task.member.entity.Member;
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
public class AccumulatedMileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("누적 적립금 아이디")
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, name = "memberNo", referencedColumnName = "memberNo")
    @Comment("회원 번호")
    private Member member;

    @Column(nullable = false, length = 6)
    @Comment("총액")
    private Integer totalAmount;

}
