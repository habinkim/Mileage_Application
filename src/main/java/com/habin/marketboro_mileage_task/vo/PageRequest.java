package com.habin.marketboro_mileage_task.vo;


import org.springframework.data.domain.Sort.Direction;

public class PageRequest {

    private Integer page = 1;
    private Integer size = 10;
    private Direction direction = Direction.DESC;

    private static final Integer DEFAULT_SIZE = 10;
    private static final Integer MAX_SIZE = 50;

    public void setPage(Integer page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(Integer size) {
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "id");
    }

}
