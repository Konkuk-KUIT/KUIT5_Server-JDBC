package core.mvc;

import core.mvc.view.View;
import core.mvc.view.ViewResolver;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.model.User;
import jwp.util.UserSessionUtils;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private RequestMapping requestMapping;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
        viewResolver = new ViewResolver();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controller controller = getController(request, response);
        if (controller == null) {
            return;
        }

        Map<String, Object> model = new HashMap<>();
        String viewName = getViewName(request, controller, model);
        if (viewName == null) {
            return;
        }

        View view = viewResolver.getView(viewName);
        view.render(model, request, response);

    }

    private Controller getController(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();
        Controller controller = requestMapping.getController(url);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        setControllerFields(request, controller);
        return controller;
    }

    private String getViewName(HttpServletRequest req, Controller controller, Map<String, Object> model) {
        Map<String, String> params = createParamMap(req);
        String viewName;
        try {
            viewName = controller.execute(params, model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return viewName;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

    private static void setControllerFields(HttpServletRequest request, Controller controller) {
        HttpSession session = request.getSession();
        controller.setSession(session);

        if (UserSessionUtils.isLogined(session)) {
            User userFromSession = UserSessionUtils.getUserFromSession(session);
            controller.setUserFromSession(userFromSession);
        }
    }
}
