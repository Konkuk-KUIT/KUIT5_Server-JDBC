package core.mvc.adapter;

import core.mvc.controller.Controller;
import core.mvc.controller.ControllerV2;
import core.mvc.view.ModelAndView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerAdapterV2 implements HandlerAdapter {
    @Override
    public boolean supports(Controller handler) {
        return (handler instanceof ControllerV2);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Controller handler)
            throws ServletException, IOException, SQLException {
        ControllerV2 controller = (ControllerV2) handler;
        setControllerFields(request, controller);

        Map<String, String> params = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.execute(params, model);

        ModelAndView modelAndView = new ModelAndView(viewName, model);
        return modelAndView;
    }
}