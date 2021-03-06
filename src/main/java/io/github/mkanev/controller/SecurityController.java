package io.github.mkanev.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@Controller
public class SecurityController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @Secured("ADMIN")
    @RequestMapping("/simulateError")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(escapeTags(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        model.addAttribute("currentPage", "error");
        return "error";
    }

    private String escapeTags(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
