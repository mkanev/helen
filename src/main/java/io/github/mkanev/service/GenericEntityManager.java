package io.github.mkanev.service;

import java.util.List;

import io.github.mkanev.model.GenericEntity;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public interface GenericEntityManager<T extends GenericEntity> extends GenericManager<T, Long> {

    List<T> getExistingEntityList();

    List<T> findByNamedQuery(String queryName, Object... parameters);

    List<T> findByNamedQuery(String queryName, int resultLimit, Object... parameters);

    List<T> findByNamedQuery(String queryName, int start, int end, Object... parameters);
}
