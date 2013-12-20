package io.github.mkanev.helper;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import io.github.mkanev.model.Struct;
import io.github.mkanev.model.User;


/**
 * @author Maksim Kanev
 */
public interface PersistenceHelper {

    <TDomain extends Struct> TDomain findDomainObjectByPrimaryKey(Class<TDomain> clazz, Long id);

    User findByUsername(String username);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    <T extends Struct> T saveOrUpdateDomainObject(T domainObject);

    <T extends Struct> List<T> getAllDomainObjects(Class<T> clazz);

    <T extends Struct> List<T> getDomainObjectsList(Class<T> clazz, Map<String, Object> paramsMap);

    <T extends Struct> List<T> getDomainObjectsPage(Class<T> clazz, Map<String, Object> paramsMap, Integer pageNum, Integer pageSize);
}
