package core.mvc;

public abstract class AbstractController implements Controller {

    protected ModelAndView jspView(String jspPath) {
        return new ModelAndView(new JspView(jspPath));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
