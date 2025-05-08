package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.JspView;  // JspView 임포트
import core.mvc.RedirectView;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            // JspView를 사용하여 뷰 생성
            JspView view = new JspView("/user/list.jsp");
            ModelAndView mav = new ModelAndView(view);
            mav.getModel().addAttribute("users", MemoryUserRepository.getInstance().findAll());
            return mav;
        }
        // 리다이렉트는 View 객체로 전달
        return new ModelAndView(String.valueOf(new RedirectView("redirect:/user/loginForm")));
    }
}
