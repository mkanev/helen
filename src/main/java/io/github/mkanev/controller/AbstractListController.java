package io.github.mkanev.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import io.github.mkanev.model.GenericEntity;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public abstract class AbstractListController<TDomain extends GenericEntity> extends AbstractCrudController<TDomain> {

    private static final ActionType ROOT_ACTION = ActionType.LIST;

    AbstractListController() {
        super(ROOT_ACTION);
    }

    @ModelAttribute("domainObjects")
    public final List<TDomain> getDomainObjectsList() {
        return entityDAO.getExistingEntityList();
    }

    @Override
    public final String getLandingPage() {
        return "/common/list";
    }
}
