package core.mvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

public class ControllerScanner {

    public Map<Class<?>, Object> getController() throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("jwp");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);

        Map<Class<?>, Object> controllers = new HashMap<>();
        for (Class<?> aClass : annotated) {
            controllers.put(aClass, aClass.newInstance());
        }
        return controllers;
    }
}
