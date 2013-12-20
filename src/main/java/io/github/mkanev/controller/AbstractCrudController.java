package io.github.mkanev.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.github.mkanev.helper.PersistenceHelper;
import io.github.mkanev.model.Struct;

/**
 * Created with IntelliJ IDEA. User: Maksim Kanev Date: 13.08.13 Time: 13:06
 */
@SessionAttributes({"domainObject"})
public abstract class AbstractCrudController<TDomain extends Struct> extends AbstractController {

    private static final String DEFAULT_MODEL_ATTRIBUTE_NAME = "domainObject";
    private final Class<TDomain> handledClass;
    @Autowired
    protected PersistenceHelper persistenceHelper;

    AbstractCrudController(ActionType defaultActionType, final Class<TDomain> handledClass) {
        super(defaultActionType);
        this.handledClass = handledClass;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String initCreateDomainObjectForm(Model model) {
        model.addAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME, createDomainObject());
        return "/common/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreateDomainObjectForm(@Valid @ModelAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME) TDomain domainObject, BindingResult result, SessionStatus status, Model model) {
        if (result.hasErrors()) {
            return "/common/edit";
        } else {
            saveOrUpdateDomainObject(domainObject);
            status.setComplete();
            return PathHelper.buildRedirectPath("/" + getCurrentPage());
        }
    }

    @RequestMapping(value = "/{domainObjectId}/edit", method = RequestMethod.GET)
    public String initUpdateDomainObjectForm(@PathVariable("domainObjectId") Long domainObjectId, Model model) {
        model.addAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME, getDomainObjectById(domainObjectId));
        return "/common/edit";
    }

    @RequestMapping(value = "/{domainObjectId}/edit", method = RequestMethod.PUT)
    public String processUpdateDomainObjectForm(@Valid @ModelAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME) TDomain domainObject, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            return "/common/edit";
        } else {
            domainObject = saveOrUpdateDomainObject(domainObject);
            status.setComplete();
            model.addAttribute(DEFAULT_MODEL_ATTRIBUTE_NAME, domainObject);
            return PathHelper.buildRedirectPath("/" + getCurrentPage()) + "/{domainObjectId}";
        }
    }

    @RequestMapping("/{domainObjectId}")
    public ModelAndView showDomainObject(@PathVariable("domainObjectId") Long domainObjectId) {
        ModelAndView mav = new ModelAndView("/common/view");
        mav.addObject(DEFAULT_MODEL_ATTRIBUTE_NAME, getDomainObjectById(domainObjectId));
        return mav;
    }

    protected final Class<TDomain> getHandledClass() {
        return handledClass;
    }

    public final TDomain getDomainObjectById(Long domainObjectId) {
        return persistenceHelper.findDomainObjectByPrimaryKey(getHandledClass(), domainObjectId);
    }

    public final TDomain saveOrUpdateDomainObject(TDomain domainObject) {
        return persistenceHelper.saveOrUpdateDomainObject(domainObject);
    }

    protected abstract TDomain createDomainObject();
}
