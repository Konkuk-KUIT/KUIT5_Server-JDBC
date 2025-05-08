package jwp.controller;

import core.mvc.Controller;

import core.mvc.JspView;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/home.jsp";
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        return new ModelAndView(new JspView("/home.jsp"));
    }
}
