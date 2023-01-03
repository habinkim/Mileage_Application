package com.habin.mileage.common.event;

import com.habin.mileage.mileage_detail.service.MileageDetailService;
import com.habin.mileage.mileage_event.dto.MileageEventCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MileageDetailCreateWorker implements Runnable {

    private final MileageDetailCreateQueue detailCreateQueue;
    private final MileageDetailService detailService;

    @Override
    public void run() {

        MileageEventCreateRequestDto poll = detailCreateQueue.poll();



    }
}
