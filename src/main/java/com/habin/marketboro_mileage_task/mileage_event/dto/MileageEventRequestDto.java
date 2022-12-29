package com.habin.marketboro_mileage_task.mileage_event.dto;


import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.validation.ValidEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MileageEventRequestDto(@NotBlank(message = "회원번호를 입력해주세요.")
                                     @Size(max = 50, message = "회원번호는 50자 이하로 입력해주세요.")
                                     String memberNo,
                                     @NotNull(message = "거래 금액을 입력해주세요.")
                                     @Max(value = 9_999_999_9, message = "거래 금액은 8자 이하로 입력해주세요.")
                                     Integer amount,
                                     @NotNull(message = "거래 상태를 입력해주세요.")
                                     @ValidEnum(enumClass = MileageStatus.class,
                                             message = "거래 상태는 SAVE(\"적립\"), USED(\"사용\"), DEDUCTED(\"차감\"), EXPIRED(\"만료\") 중 하나여야 합니다.")
                                     MileageStatus mileageStatus) {
}
