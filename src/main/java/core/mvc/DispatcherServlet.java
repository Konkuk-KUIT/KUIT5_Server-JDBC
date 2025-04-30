package core.mvc;

import core.mvc.controller.Controller;
import core.mvc.controller.RequestMapping;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private List<HandlerMapping> mappings = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        RequestMapping requestMapping = new RequestMapping();
        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("jwp.controller");
        annotationHandlerMapping.initialize();
        mappings.add(requestMapping);
        mappings.add(annotationHandlerMapping);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);
        try {
            ModelAndView modelAndView = execute(handler, req, resp);
            View view = modelAndView.getView();
            view.render(modelAndView.getModel(), req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }

    private Object getHandler(HttpServletRequest request) {
        for (HandlerMapping mapping : mappings) {
            Object handler = mapping.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }

    private ModelAndView execute(Object handler, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (handler instanceof Controller) {
            return ((Controller) handler).execute(request, response);
        }
        return ((HandlerExecution) handler).handle(request, response);
    }
}
