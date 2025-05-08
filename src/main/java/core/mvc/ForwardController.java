package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private final String path;

    public ForwardController(String path) {
        this.path = path;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView(new JspView(path));
    }
}
