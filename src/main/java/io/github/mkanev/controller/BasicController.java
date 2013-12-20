package io.github.mkanev.controller;

/**
 * Created with IntelliJ IDEA. User: Maksim Kanev Date: 12.08.13 Time: 20:13
 */
public class BasicController extends AbstractController {

    private static final ActionType ROOT_ACTION = ActionType.NONE;
    private String currentPage;

    protected BasicController(String currentPage) {
        super(ROOT_ACTION);
        this.currentPage = currentPage;
    }

    @Override
    public String getCurrentPage() {
        return currentPage;
    }
}
