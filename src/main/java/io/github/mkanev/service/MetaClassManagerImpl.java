package io.github.mkanev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mkanev.dao.MetaClassDAO;
import io.github.mkanev.model.MetaClass;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service
public class MetaClassManagerImpl extends GenericEntityManagerImpl<MetaClass> implements MetaClassManager {

    @Autowired
    public MetaClassManagerImpl(MetaClassDAO metaClassDao) {
        super(metaClassDao);
    }
}
