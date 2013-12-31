package io.github.mkanev.repository;

import io.github.mkanev.model.User;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public interface UserDAO extends GenericEntityDAO<User, Long> {

    User findByUsername(String username);

}
