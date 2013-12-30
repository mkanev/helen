package io.github.mkanev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.mkanev.common.LoggedClass;
import io.github.mkanev.helper.PathHelper;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Controller
@RequestMapping(value = "/")
public class RootController extends LoggedClass {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String getIndexPage() {
        return PathHelper.buildRedirectPath("secure");
    }

}
