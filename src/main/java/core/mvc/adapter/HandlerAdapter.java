package core.mvc.adapter;

import core.mvc.controller.Controller;
import core.mvc.controller.ControllerV1;
import core.mvc.controller.ControllerV2;
import core.mvc.view.ModelAndView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jwp.model.User;
import jwp.util.UserSessionUtils;

public interface HandlerAdapter {

    boolean supports(Controller handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Controller handler)
            throws ServletException, IOException, SQLException;

    default Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    default void setControllerFields(HttpServletRequest request, ControllerV1 controllerV1) {
        HttpSession session = request.getSession();
        controllerV1.setSession(session);

        if (UserSessionUtils.isLogined(session)) {
            User userFromSession = UserSessionUtils.getUserFromSession(session);
            controllerV1.setUserFromSession(userFromSession);
        }
    }

    default void setControllerFields(HttpServletRequest request, ControllerV2 controller) {
        HttpSession session = request.getSession();
        controller.setSession(session);

        if (UserSessionUtils.isLogined(session)) {
            User userFromSession = UserSessionUtils.getUserFromSession(session);
            controller.setUserFromSession(userFromSession);
        }
    }
}