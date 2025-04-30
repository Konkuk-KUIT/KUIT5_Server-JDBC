package core.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.reflections.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

public class AnnotationHandlerMapping implements HandlerMapping {
    private Object[] basePackage;
    private Map<HandlerKey, HandlerExecution> handlerExecutionMap = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        ControllerScanner controllerScanner = new ControllerScanner();
        Map<Class<?>, Object> controllers = null;
        try {
            controllers = controllerScanner.getController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Set<Method> requestMethod = getRequestMethod(controllers.keySet());

        for (Method method : requestMethod) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            handlerExecutionMap.put(new HandlerKey(requestMapping.value(), requestMapping.method()),
                    new HandlerExecution(controllers.get(method.getDeclaringClass()), method));
        }
    }

    private Set<Method> getRequestMethod(Set<Class<?>> controllers) {
        Set<Method> requestMethods = new HashSet<>();
        for (Class<?> aClass : controllers) {
            requestMethods.addAll(ReflectionUtils.getAllMethods(aClass,
                    ReflectionUtils.withAnnotation(RequestMapping.class)));
        }
        return requestMethods;
    }

    @Override
    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(
                request.getMethod().toUpperCase());
        return handlerExecutionMap.get(new HandlerKey(requestURI, requestMethod));
    }

}
