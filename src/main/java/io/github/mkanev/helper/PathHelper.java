package io.github.mkanev.helper;

import com.google.common.base.Strings;

import io.github.mkanev.controller.ActionType;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public class PathHelper {

    private static final String pathSeparator = "/";

    private PathHelper() {
    }

    public static String buildRedirectPathToRoot() {
        return buildRedirectPath(null);
    }

    public static String buildRedirectPath(String redirectPath) {
        return "redirect:" + (Strings.isNullOrEmpty(redirectPath) ? "/" : redirectPath);
    }

    public static String buildActionPathForPage(String pageName, ActionType... actions) {
        return pageName + buildActionPath(actions);
    }

    public static String buildActionPath(ActionType... actions) {
        if (actions == null || actions.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ActionType actionType : actions) {
            if (ActionType.NONE.equals(actionType)) {
                continue;
            }
            sb.append(pathSeparator).append(actionType.name().toLowerCase());
        }
        return sb.toString();
    }
}
