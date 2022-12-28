package com.habin.marketboro_mileage_task.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record SignUpRequestDto(@NotBlank(message = "회원명을 입력해주세요.")
                               @Size(min = 2, max = 10, message = "회원명은 2자 이상 10자 이하로 입력해주세요.")
                               String memberNm) {

}
