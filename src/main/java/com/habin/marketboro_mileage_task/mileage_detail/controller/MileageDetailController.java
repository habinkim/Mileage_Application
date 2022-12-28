package com.habin.marketboro_mileage_task.mileage_detail.controller;

import com.habin.marketboro_mileage_task.mileage_detail.service.MileageDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage/detail")
public class MileageDetailController {

    private final MileageDetailService mileageDetailService;

}
