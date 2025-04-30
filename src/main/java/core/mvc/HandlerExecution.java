package core.mvc;

import core.mvc.view.ModelAndView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerExecution {

    private Object declaredObject;
    private Method method;

    public HandlerExecution(Object declaredObject, Method method) {
        this.declaredObject = declaredObject;
        this.method = method;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response)
            throws InvocationTargetException, IllegalAccessException {
        return (ModelAndView) method.invoke(declaredObject, request, response);
    }
}
