package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController extends AbstractController {
    UserDao userDao;

    public UpdateUserFormController(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");         // 수정되는 user
        User user = userDao.findByUserId(userId);

        HttpSession session = request.getSession();                    // 수정하는 user
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return jspView("/user/updateForm.jsp");
            }
        }
        return jspView("redirect:/");
    }
}