package io.github.mkanev.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import io.github.mkanev.helper.PathHelper;
import io.github.mkanev.model.GenericEntity;
import io.github.mkanev.service.GenericEntityManager;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@SessionAttributes({AbstractCrudController.DEFAULT_MODEL_ATTRIBUTE_NAME})
public abstract class AbstractCrudController<TEntity extends GenericEntity> extends GenericEntityController<TEntity> {

    public static final String DEFAULT_MODEL_ATTRIBUTE_NAME = "entity";

    AbstractCrudController(ActionType defaultActionType, GenericEntityManager<TEntity> manager) {
        super(defaultActionType, manager);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String initCreateEntityForm(Model model) {
        model.addAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME, createEntity());
        return "/common/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createEntity(@Valid @ModelAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME) TEntity entity, BindingResult result, SessionStatus status, Model model) {
        if (result.hasErrors()) {
            return "/common/edit";
        } else {
            saveEntity(entity);
            status.setComplete();
            return PathHelper.buildRedirectPath("/" + getCurrentPage());
        }
    }

    @RequestMapping(value = "/{entityId}/edit", method = RequestMethod.GET)
    public String initUpdateEntityForm(@PathVariable("entityId") Long entityId, Model model) {
        model.addAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME, getEntityById(entityId));
        return "/common/edit";
    }

    @RequestMapping(value = "/{entityId}/edit", method = RequestMethod.PUT)
    public String updateEntity(@Valid @ModelAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME) TEntity entity, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            return "/common/edit";
        } else {
            entity = saveEntity(entity);
            status.setComplete();
            model.addAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME, entity);
            return PathHelper.buildRedirectPath("/" + getCurrentPage()) + "/{entityId}";
        }
    }

    @RequestMapping("/{entityId}")
    public ModelAndView showEntity(@PathVariable("entityId") Long entityId) {
        ModelAndView mav = new ModelAndView("/common/view");
        mav.addObject(DEFAULT_MODEL_ATTRIBUTE_NAME, getEntityById(entityId));
        return mav;
    }

    public final TEntity getEntityById(Long entityId) {
        return manager.get(entityId);
    }

    public final TEntity saveEntity(TEntity entity) {
        return manager.save(entity);
    }

    protected abstract TEntity createEntity();
}
