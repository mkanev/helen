package io.github.mkanev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.mkanev.model.User;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends AbstractCrudController<User> {

    private static final String CURRENT_PAGE = "profile";
    private static final ActionType DEFAULT_ACTION = ActionType.NONE;

    public ProfileController() {
        super(DEFAULT_ACTION);
    }

    @Override
    public String getCurrentPage() {
        return CURRENT_PAGE;
    }

    @Override
    public User createDomainObject() {
        return new User();
    }

    @Override
    public String initUpdateDomainObjectForm(@PathVariable("domainObjectId") Long domainObjectId, Model model) {
        if (domainObjectId != null && !domainObjectId.equals(getCurrentUser().getId())) {
            return "/error";
        }
        return super.initUpdateDomainObjectForm(domainObjectId, model);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public ModelAndView showDomainObject(@PathVariable("domainObjectId") Long domainObjectId) {
        if (domainObjectId != null && !domainObjectId.equals(getCurrentUser().getId())) {
            return new ModelAndView("/error");
        }
        return super.showDomainObject(domainObjectId);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
