package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private RequestMapping requestMapping;
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DispatcherServlet received URL: " + request.getRequestURI());
        String url = request.getRequestURI();
        Controller controller = requestMapping.getController(url);
        try {
            ModelAndView modelAndView = controller.execute(request, response);
            View view = modelAndView.getView();
            view.render(modelAndView.getModel(), request,response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }

    private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            resp.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}
