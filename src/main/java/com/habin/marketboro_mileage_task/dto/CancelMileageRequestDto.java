package com.habin.marketboro_mileage_task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelMileageRequestDto {

    @NotBlank(message = "회원번호를 입력해주세요.")
    private String memberNo;

    @NotBlank(message = "적립금 이력 아이디를 입력해주세요.")
    private String mileageEventId;

}
