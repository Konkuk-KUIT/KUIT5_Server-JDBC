package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.model.User;

public class UpdateUserFormController implements Controller {
    private final UserDao userDao = new UserDao();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");         // 수정되는 user

        User user = userDao.findByUserId(userId);
        HttpSession session = request.getSession();                    // 수정하는 user
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return new JspView("/user/updateForm.jsp");
            }
        }
        return new JspView("redirect:/");
    }
}