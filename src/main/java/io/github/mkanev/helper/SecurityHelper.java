package io.github.mkanev.helper;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.github.mkanev.model.User;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public interface SecurityHelper extends UserDetailsService {

    User getCurrentUser();
}
