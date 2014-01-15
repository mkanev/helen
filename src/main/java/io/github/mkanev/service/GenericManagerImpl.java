package io.github.mkanev.service;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

import io.github.mkanev.common.LoggedClass;
import io.github.mkanev.model.GenericEntity;
import io.github.mkanev.repository.GenericEntityDAO;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Transactional(readOnly = true)
public abstract class GenericManagerImpl<T extends GenericEntity, PK extends Serializable> extends LoggedClass implements GenericManager<T, PK> {

    private static final long serialVersionUID = 4852856653776616598L;
    protected final GenericEntityDAO<T, PK> dao;

    public GenericManagerImpl(GenericEntityDAO<T, PK> genericDao) {
        this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.getEntity(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public T save(T object) {
        logDebug("___GenericManagerImpl.save %s", object);
        return dao.saveEntity(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.deleteEntity(id);
    }

    public T getFull(PK id) {
        return dao.getFull(id);
    }
}

