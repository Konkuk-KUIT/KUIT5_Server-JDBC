package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbstractController implements Controller{
    protected ModelAndView jspView(String viewName){
        return new ModelAndView(new JspView(viewName));
    }

    protected ModelAndView jsonView(){
        return new ModelAndView(new JsonView());
    }

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }
}
