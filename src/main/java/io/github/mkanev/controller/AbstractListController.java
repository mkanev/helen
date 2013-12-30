package io.github.mkanev.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import io.github.mkanev.model.GenericEntity;
import io.github.mkanev.service.GenericEntityManager;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public abstract class AbstractListController<TEntity extends GenericEntity> extends AbstractCrudController<TEntity> {

    private static final ActionType ROOT_ACTION = ActionType.LIST;

    AbstractListController(GenericEntityManager<TEntity> manager) {
        super(ROOT_ACTION, manager);
    }

    @ModelAttribute("entityList")
    public final List<TEntity> getEntityList() {
        return manager.getExistingEntityList();
    }

    @Override
    public final String getLandingPage() {
        return "common/list";
    }
}
