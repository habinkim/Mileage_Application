package com.habin.marketboro_mileage_task.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record MileageRequestDto(@NotBlank(message = "회원번호를 입력해주세요.")
                                @Size(max = 50, message = "회원번호는 50자 이하로 입력해주세요.")
                                String memberNo,
                                @NotNull(message = "적립금 적립 금액을 입력해주세요.")
                                @Max(value = 999999, message = "적립금 적립 금액은 6자 이하로 입력해주세요.")
                                Integer sum) {

}