package com.habin.marketboro_mileage_task.mileage_event.dto;


import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.validation.ValidEnum;
import com.habin.marketboro_mileage_task.mileage_event.exception.MileageEventInvalidException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageEventCreateRequestDto {

    @NotBlank(message = "회원번호를 입력해주세요.")
    @Size(max = 50, message = "회원번호는 50자 이하로 입력해주세요.")
    String memberNo;
    @NotNull(message = "거래 금액을 입력해주세요.")
    @Max(value = 9_999_999_9, message = "거래 금액은 8자 이하로 입력해주세요.")
    Integer amount;
    @NotNull(message = "거래 상태를 입력해주세요.")
    @ValidEnum(enumClass = MileageStatus.class,
            message = "거래 상태는 SAVE(\"적립\"), USE(\"사용\"), DEDUCTED(\"차감\"), EXPIRED(\"만료\") 중 하나여야 합니다.")
    MileageStatus mileageStatus;

    public void isAmountValid() {
        if (amount < 0) {
            throw new MileageEventInvalidException("amount", "거래 금액은 0보다 커야 합니다.");
        } else if (amount > 10_000_000_0) {
            throw new MileageEventInvalidException("amount", "거래 금액은 10,000,000원을 초과 할 수 없습니다.");
        }
    }

    public void isMileageStatusValid() {
        if (mileageStatus != MileageStatus.SAVE && mileageStatus != MileageStatus.USE) {
            throw new MileageEventInvalidException("mileageStatus", "거래 상태는 SAVE(\"적립\"), USE(\"사용\") 중 하나여야 합니다.");
        }
    }

}
