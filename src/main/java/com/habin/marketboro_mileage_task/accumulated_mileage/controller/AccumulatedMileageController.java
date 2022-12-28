package com.habin.marketboro_mileage_task.accumulated_mileage.controller;

import com.habin.marketboro_mileage_task.accumulated_mileage.service.AccumulatedMileageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage/accumulated")
public class AccumulatedMileageController {

    private final AccumulatedMileageService accumulatedMileageService;

}
