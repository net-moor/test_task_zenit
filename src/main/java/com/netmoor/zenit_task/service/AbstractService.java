package com.netmoor.zenit_task.service;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Nikolay_Batov on 06.05.2022
 */
public abstract class AbstractService {

    protected <T> void pathField(T t, Consumer<T> consumer) {
        Optional.ofNullable(t).ifPresent(consumer);
    }
}
