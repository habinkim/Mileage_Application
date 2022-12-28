package com.habin.marketboro_mileage_task.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.LongSupplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class SerializablePageExecutionUtils {

    public static <T> SerializablePage<T> getPage(List<T> content, Pageable pageable, LongSupplier totalSupplier) {
        Assert.notNull(content, "Content must not be null");
        Assert.notNull(pageable, "Pageable must not be null");
        Assert.notNull(totalSupplier, "TotalSupplier must not be null");

        if (pageable.isUnpaged() || pageable.getOffset() == 0) {

            if (pageable.isUnpaged() || pageable.getPageSize() > content.size()) {
                return new SerializablePage<>(content, pageable, content.size());
            }

            return new SerializablePage<>(content, pageable, totalSupplier.getAsLong());
        }

        if (content.size() != 0 && pageable.getPageSize() > content.size()) {
            return new SerializablePage<>(content, pageable, pageable.getOffset() + content.size());
        }

        return new SerializablePage<>(content, pageable, totalSupplier.getAsLong());
    }

}
