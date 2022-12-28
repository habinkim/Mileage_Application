package com.habin.marketboro_mileage_task.mileage_detail.service;

import com.habin.marketboro_mileage_task.mileage_detail.repository.MileageDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageDetailService {

    private final MileageDetailRepository mileageDetailRepository;

}
