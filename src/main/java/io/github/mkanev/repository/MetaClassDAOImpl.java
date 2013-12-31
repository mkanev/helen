package io.github.mkanev.repository;

import org.springframework.stereotype.Repository;

import io.github.mkanev.model.MetaClass;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@Repository
public class MetaClassDAOImpl extends GenericEntityDAOImpl<MetaClass, Long> implements MetaClassDAO {

    public MetaClassDAOImpl() {
        super(MetaClass.class);
    }
}
