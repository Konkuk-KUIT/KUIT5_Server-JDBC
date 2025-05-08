package core.mvc;

import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import jwp.model.User;

public interface Controller {

    default void setSession(HttpSession session) {
    }

    default void setUserFromSession(User user) {
    }

    String execute(Map<String, String> params, Map<String, Object> model) throws SQLException;
}
