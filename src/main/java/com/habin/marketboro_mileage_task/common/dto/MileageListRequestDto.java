package com.habin.marketboro_mileage_task.common.dto;

import com.habin.marketboro_mileage_task.common.MileageStatus;
import com.habin.marketboro_mileage_task.common.validation.ValidEnum;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MileageListRequestDto extends PageRequestDto {

    @Size(max = 40, message = "회원번호는 최대 40자까지 입력 가능합니다.")
    private String memberNo;

    @ValidEnum(enumClass = MileageStatus.class,
            message = "거래 상태는 SAVE(\"적립\"), USED(\"사용\"), DEDUCTED(\"차감\"), EXPIRED(\"만료\") 중 하나여야 합니다.")
    private MileageStatus mileageStatus;

}
