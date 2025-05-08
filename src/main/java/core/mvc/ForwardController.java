package core.mvc;

import core.mvc.controller.ControllerV2;
import java.sql.SQLException;
import java.util.Map;

public class ForwardController implements ControllerV2 {

    private final String forwardUrl;

    public ForwardController(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null");
        }
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        return forwardUrl;
    }
}
