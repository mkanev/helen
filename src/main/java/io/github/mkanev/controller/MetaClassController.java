package io.github.mkanev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.mkanev.model.MetaClass;
import io.github.mkanev.service.MetaClassManager;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Controller
@RequestMapping(value = "/" + MetaClassController.CURRENT_PAGE)
public class MetaClassController extends AbstractListController<MetaClass> {

    public static final String CURRENT_PAGE = "admin";

    @Autowired
    public MetaClassController(MetaClassManager manager) {
        super(manager);
    }

    @Override
    protected MetaClass createEntity() {
        return new MetaClass();
    }

    @Override
    public String getCurrentPage() {
        return CURRENT_PAGE;
    }
}
