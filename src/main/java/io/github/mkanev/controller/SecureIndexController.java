package io.github.mkanev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Controller
@RequestMapping("/secure")
public class SecureIndexController extends BasicController {

    protected SecureIndexController() {
        super("test_index");
    }

}
