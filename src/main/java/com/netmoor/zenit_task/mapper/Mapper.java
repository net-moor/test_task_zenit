package com.netmoor.zenit_task.mapper;

import java.util.List;

/**
 * @author Nikolay_Batov on 05.05.2022
 */
public interface Mapper<E, D> {

    D mapToDto(E entity);

    List<D> mapToDto(List<E> entity);

    E mapToEntity(D dto);

    List<E> mapToEntity(List<D> dto);
}
