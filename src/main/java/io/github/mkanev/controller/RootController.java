package io.github.mkanev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA. User: Maksim Kanev Date: 12.08.13 Time: 18:20
 */
@Controller
@RequestMapping(value = "/")
public class RootController extends BasicController {

    private static final String CURRENT_PAGE = "index";

    public RootController() {
        super(CURRENT_PAGE);
    }

    @RequestMapping("/index")
    public String getIndexPage() {
        return getLandingPage();
    }

}
