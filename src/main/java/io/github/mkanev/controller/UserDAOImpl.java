package io.github.mkanev.controller;

import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

import io.github.mkanev.model.User;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@Repository
public class UserDAOImpl extends GenericEntityDAOImpl<User, Long> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        CriteriaSet cs = new CriteriaSet();
        cs.filterDeleted();
        cs.cq.where(cs.cb.equal(cs.r.get(User.FIELD_USERNAME), username));
        TypedQuery<User> query = em.createQuery(cs.cq);
        return getQueryResult(query, true);
    }
}
