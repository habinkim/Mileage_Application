package com.habin.marketboro_mileage_task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;


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
    @Comment("회원 번호")
    private String memberNo;

    @Column(nullable = false, length = 10)
    @Comment("회원명")
    private String memberNm;

    @Column(nullable = false, length = 10)
    @Comment("보유 적립액")
    private Integer totalMileageAmount;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Mileage> mileage = new ArrayList<>();

}
