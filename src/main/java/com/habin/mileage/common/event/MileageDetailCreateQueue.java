package com.habin.mileage.common.event;

import com.habin.mileage.mileage_event.dto.MileageEventCreateRequestDto;
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

    public MileageEventCreateRequestDto poll() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("이벤트 큐에 이벤트 없음");
        }

        MileageEventCreateRequestDto mileageEventCreateRequestDto = queue.poll();
        healthCheck();
        return mileageEventCreateRequestDto;
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
