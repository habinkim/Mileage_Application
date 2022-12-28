package com.habin.marketboro_mileage_task.mileage_event.controller;

import com.habin.marketboro_mileage_task.mileage_event.service.MileageEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage/event")
public class MileageEventController {

    private final MileageEventService mileageEventService;

}
