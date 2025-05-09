package core.mvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private RequestMapping requestMapping;
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest requeset, HttpServletResponse response) throws ServletException, IOException {
        String url = requeset.getRequestURI();
        Controller controller = requestMapping.getController(url);
        try {
            ModelAndView modelAndView = controller.execute(requeset, response);
            View view = modelAndView.getView();
            view.render(modelAndView.getModel(),requeset,response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }
}
