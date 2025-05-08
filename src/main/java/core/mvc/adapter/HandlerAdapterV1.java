package core.mvc.adapter;

import core.mvc.controller.Controller;
import core.mvc.controller.ControllerV1;
import core.mvc.view.ModelAndView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerAdapterV1 implements HandlerAdapter {

    @Override
    public boolean supports(Controller handler) {
        return (handler instanceof ControllerV1);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Controller handler)
            throws ServletException, IOException, SQLException {
        ControllerV1 controller = (ControllerV1) handler;
        setControllerFields(request, controller);

        Map<String, String> params = createParamMap(request);
        ModelAndView modelAndView = controller.execute(params);
        return modelAndView;
    }
}