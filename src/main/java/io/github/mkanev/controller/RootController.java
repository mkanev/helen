package io.github.mkanev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.mkanev.common.LoggedClass;

/**
 * Created with IntelliJ IDEA. User: Maksim Kanev Date: 12.08.13 Time: 18:20
 */
@Controller
@RequestMapping(value = "/")
public class RootController extends LoggedClass {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String getIndexPage() {
        logDebug("Sending redirect");
        return "redirect:/secure";
    }

}
