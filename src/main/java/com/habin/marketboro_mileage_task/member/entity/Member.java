package com.habin.marketboro_mileage_task.member.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeDeserializer;
import com.habin.marketboro_mileage_task.common.json.CustomLocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * @author : 김하빈(danny9643@naver.com)
 * @description : Member(사용자) Entity 클래스<p>
 * ! <p>
 * ? <p>
 * TODO
 * @Date : 2022-12-26, Mon, 18;0
 */
@EntityListeners(AuditingEntityListener.class)
@Builder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"memberNm"}))
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Comment("회원 번호")
    private UUID memberNo;

    @Column(length = 10)
    @Comment("회원명")
    private String memberNm;

    @Column(nullable = false, length = 8)
    @Comment("적립금 총액")
    private Integer totalAmount;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("가입일")
    private LocalDateTime registerDtm;

}
