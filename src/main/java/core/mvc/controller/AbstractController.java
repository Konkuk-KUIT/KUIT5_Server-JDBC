package core.mvc.controller;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

public abstract class AbstractController implements Controller {

    public ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
