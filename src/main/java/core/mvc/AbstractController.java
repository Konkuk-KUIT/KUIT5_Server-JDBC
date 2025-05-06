package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements Controller {
    protected ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
