package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

public abstract class AbstractController implements Controller {

    protected ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
