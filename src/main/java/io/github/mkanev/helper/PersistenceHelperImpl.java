package io.github.mkanev.helper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import io.github.mkanev.common.LoggedClass;
import io.github.mkanev.model.Struct;
import io.github.mkanev.model.User;

/**
 * @author Maksim Kanev
 */
@Repository
@Transactional(readOnly = true)
public class PersistenceHelperImpl extends LoggedClass implements PersistenceHelper {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <TDomain extends Struct> TDomain findDomainObjectByPrimaryKey(Class<TDomain> clazz, Long id) {
        try {
            return entityManager.find(clazz, id);
        } catch (PersistenceException e) {
            getLogger().error("Can't find entity", e);
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("username", username);
        return findByDefaultField(User.class, paramsMap);
    }

    private <T extends Struct> T findByDefaultField(Class<T> clazz, Map<String, Object> paramsMap) {
        try {
            TypedQuery<T> namedQuery = entityManager.createNamedQuery(clazz.getSimpleName() + ".DEFAULT_FIND_QUERY", clazz);
            for (Map.Entry<String, Object> param : paramsMap.entrySet()) {
                namedQuery.setParameter(param.getKey(), param.getValue());
            }
            List<T> resultList = namedQuery.getResultList();
            if (resultList == null || resultList.isEmpty()) {
                logDebug("No result");
                return null;
            }
            if (resultList.size() > 1) {
                logDebug("Found more than one entity");
            }
            return resultList.iterator().next();
        } catch (PersistenceException e) {
            getLogger().error("Can't find entity", e);
            return null;
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public <T extends Struct> T saveOrUpdateDomainObject(T domainObject) {
        if (domainObject.isBlank()) {
            entityManager.persist(domainObject);
        } else {
            domainObject = entityManager.merge(domainObject);
        }
        entityManager.flush();
        return domainObject;
    }

    @Override
    public <T extends Struct> List<T> getAllDomainObjects(Class<T> clazz) {
        return getDomainObjectsList(clazz, null);
    }

    @Override
    public <T extends Struct> List<T> getDomainObjectsList(Class<T> clazz, Map<String, Object> paramsMap) {
        return getDomainObjectsPage(clazz, paramsMap, null, null);
    }

    @Override
    public <T extends Struct> List<T> getDomainObjectsPage(Class<T> clazz, Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) {
        try {
            StringBuilder querySb = new StringBuilder("from ").append(clazz.getName()).append(" o ");
            if (paramsMap != null && !paramsMap.isEmpty()) {
                Set<Map.Entry<String, Object>> paramsEntrySet = paramsMap.entrySet();
                if (!paramsEntrySet.isEmpty()) {
                    querySb.append(" where ");
                }
                Iterator<Map.Entry<String, Object>> paramIterator = paramsEntrySet.iterator();
                boolean isFirst = true;
                while (paramIterator.hasNext()) {
                    Map.Entry<String, Object> param = paramIterator.next();
                    querySb.append(isFirst ? "" : " and ").append(" o.").append(param.getKey()).append("=:").append(param.getKey());
                    isFirst = false;
                }
            }
            Query query = entityManager.createQuery(querySb.toString());
            if (paramsMap != null && !paramsMap.isEmpty()) {
                for (Map.Entry<String, Object> param : paramsMap.entrySet()) {
                    query.setParameter(param.getKey(), param.getValue());
                }
            }
            if (pageSize != null) {
                query.setMaxResults(pageSize);
                if (pageNum != null) {
                    int startPosition = pageNum * pageSize + 1;
                    query.setFirstResult(startPosition);
                }
            }
            List<T> resultList = query.getResultList();
            if (resultList == null || resultList.isEmpty()) {
                logDebug("No result");
                return null;
            }
            return resultList;
        } catch (PersistenceException e) {
            getLogger().error("Can't retrieve entity list", e);
            return null;
        }
    }
}