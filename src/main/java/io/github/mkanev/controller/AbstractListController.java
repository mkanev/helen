package io.github.mkanev.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import io.github.mkanev.model.Struct;

/**
 * @author Maksim Kanev
 */
public abstract class AbstractListController<TDomain extends Struct> extends AbstractCrudController<TDomain> {

    private static final ActionType ROOT_ACTION = ActionType.LIST;

    AbstractListController(final Class<TDomain> handledClass) {
        super(ROOT_ACTION, handledClass);
    }

    @ModelAttribute("domainObjects")
    public final List<TDomain> getDomainObjectsList() {
        return persistenceHelper.getDomainObjectsList(getHandledClass(), null);
    }

    @Override
    public final String getLandingPage() {
        return "/common/list";
    }
}
