package com.habin.marketboro_mileage_task.mileage_event.service;

import com.habin.marketboro_mileage_task.mileage_event.repository.MileageEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MileageEventService {

    private final MileageEventRepository mileageEventRepository;

}
