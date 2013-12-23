package io.github.mkanev.helper;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import io.github.mkanev.model.GenericEntity;
import io.github.mkanev.model.User;


/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public interface PersistenceHelper {

    <TDomain extends GenericEntity> TDomain findDomainObjectByPrimaryKey(Class<TDomain> clazz, Long id);

    User findByUsername(String username);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    <T extends GenericEntity> T saveOrUpdateDomainObject(T domainObject);

    <T extends GenericEntity> List<T> getAllDomainObjects(Class<T> clazz);

    <T extends GenericEntity> List<T> getDomainObjectsList(Class<T> clazz, Map<String, Object> paramsMap);

    <T extends GenericEntity> List<T> getDomainObjectsPage(Class<T> clazz, Map<String, Object> paramsMap, Integer pageNum, Integer pageSize);
}
