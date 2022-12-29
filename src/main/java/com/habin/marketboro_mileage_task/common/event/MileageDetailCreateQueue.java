package com.habin.marketboro_mileage_task.common.event;

import com.habin.marketboro_mileage_task.mileage_event.dto.MileageEventCreateRequestDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class MileageDetailCreateQueue {

    private final Queue<MileageEventCreateRequestDto> queue;

    private final Integer queueSize;

    public MileageDetailCreateQueue(Integer queueSize) {
        this.queue = new LinkedBlockingQueue<>(queueSize);
        this.queueSize = queueSize;
    }

    public static MileageDetailCreateQueue of(Integer size) {
        return new MileageDetailCreateQueue(size);
    }

    public boolean offer(MileageEventCreateRequestDto mileageEventCreateRequestDto) {
        boolean returnValue = queue.offer(mileageEventCreateRequestDto);
        healthCheck();
        return returnValue;
    }

    private int size() {
        return queue.size();
    }

    public boolean isFull() {
        return size() == queueSize;
    }

    public boolean isRemaining() {
        return size() > 0;
    }

    private void healthCheck() {
        log.info("{\"totalQueueSize\":{}, \"currentQueueSize\":{}}", queueSize, size());
    }
}
