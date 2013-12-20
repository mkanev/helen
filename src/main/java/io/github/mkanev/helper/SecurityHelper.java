package io.github.mkanev.helper;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.github.mkanev.model.User;

/**
 * @author Maksim Kanev
 */
public interface SecurityHelper extends UserDetailsService {

    User getCurrentUser();
}
