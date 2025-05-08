package jwp.controller;

import core.mvc.controller.ControllerV2;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class LogoutController implements ControllerV2 {
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
