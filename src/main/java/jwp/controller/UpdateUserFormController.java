package jwp.controller;

import core.mvc.Controller;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.model.User;

public class UpdateUserFormController implements Controller {
    private final UserDao userDao = new UserDao();
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        String userId = params.get("userId");         // 수정되는 user

        User user = userDao.findByUserId(userId);
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return "/user/updateForm.jsp";
            }
        }
        return "redirect:/";
    }
}