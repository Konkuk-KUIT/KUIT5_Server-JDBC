package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.RedirectView;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");         // 수정되는 user
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        HttpSession session = req.getSession();                    // 수정하는 user
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return new ModelAndView(String.valueOf(new RedirectView("/user/updateForm.jsp")));
            }
        }
        return new ModelAndView(String.valueOf(new RedirectView("/")));
    }
}
