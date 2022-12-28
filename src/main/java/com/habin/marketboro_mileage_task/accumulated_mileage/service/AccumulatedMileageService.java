package com.habin.marketboro_mileage_task.accumulated_mileage.service;

import com.habin.marketboro_mileage_task.accumulated_mileage.repository.AccumulatedMileageMileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccumulatedMileageService {

    private final AccumulatedMileageMileageRepository accumulatedMileageRepository;

}
