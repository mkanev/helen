package io.github.mkanev.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.mkanev.common.LoggedClass;
import io.github.mkanev.controller.UserDAO;
import io.github.mkanev.model.User;


/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public class SecurityHelperImpl extends LoggedClass implements SecurityHelper {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logDebug("Nobody is here");
            return null;
        }
        if (!(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)) {
            logDebug("Problem with authentication");
            return null;
        }
        String currentUsername = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        return userDAO.findByUsername(currentUsername);
    }
}
