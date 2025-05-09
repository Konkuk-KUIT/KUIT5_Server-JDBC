package jwp.controller;

import core.mvc.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.dao.UserDao;
import jwp.model.User;

public class UpdateUserFormController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");         // 수정되는 user
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);

        HttpSession session = request.getSession();                    // 수정하는 user
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return jspView( "/user/updateForm.jsp");
            }
        }
        return jspView("redirect:/");
    }
}