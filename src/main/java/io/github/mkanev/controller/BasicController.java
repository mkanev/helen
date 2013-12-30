package io.github.mkanev.controller;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
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
