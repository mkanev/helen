package io.github.mkanev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.mkanev.common.LoggedClass;
import io.github.mkanev.helper.PathHelper;
import io.github.mkanev.helper.SecurityHelper;
import io.github.mkanev.model.User;


/**
 * @author Maksim Kanev
 */
public abstract class AbstractController extends LoggedClass {

    private static final String[] NAV_ITEMS = new String[]{"index", "blog"};
    @Autowired
    private SecurityHelper securityHelper;
    private ActionType defaultActionType;

    AbstractController(ActionType defaultActionType) {
        this.defaultActionType = defaultActionType;
    }

    @ModelAttribute("navItems")
    public String[] getNavItems() {
        return NAV_ITEMS;
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        return securityHelper.getCurrentUser();
    }

    @ModelAttribute("currentPage")
    public abstract String getCurrentPage();

    @RequestMapping
    public String getLandingPage() {
        return PathHelper.buildActionPathForPage(getCurrentPage(), defaultActionType);
    }
}
