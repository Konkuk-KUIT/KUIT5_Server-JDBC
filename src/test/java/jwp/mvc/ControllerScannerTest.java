package jwp.mvc;

import core.mvc.ControllerScanner;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ControllerScannerTest {

    @Test
    void checkController() throws InstantiationException, IllegalAccessException {
        ControllerScanner controllerScanner = new ControllerScanner();
        Map<Class<?>, Object> controller = controllerScanner.getController();
        System.out.println("controller.size() = " + controller.size());
        for (Class<?> aClass : controller.keySet()) {
            System.out.println("aClass = " + aClass);
            ;
        }
    }
}
