package core.mvc;

import java.sql.SQLException;
import java.util.Map;

public class ForwardController implements Controller {

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
