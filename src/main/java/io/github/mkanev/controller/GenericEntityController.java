package io.github.mkanev.controller;

import io.github.mkanev.model.GenericEntity;
import io.github.mkanev.service.GenericEntityManager;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericEntityController<T extends GenericEntity> extends AbstractController {

    protected GenericEntityManager<T> manager;

    GenericEntityController(ActionType defaultActionType, GenericEntityManager<T> manager) {
        super(defaultActionType);
        this.manager = manager;
    }
}
