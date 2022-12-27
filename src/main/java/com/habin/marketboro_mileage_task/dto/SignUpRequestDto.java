package com.habin.marketboro_mileage_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "회원명을 입력해주세요.")
    @Size(min = 2, max = 10, message = "회원명은 2자 이상 10자 이하로 입력해주세요.")
    private String memberNm;

}
