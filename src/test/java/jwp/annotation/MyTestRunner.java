package jwp.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyTestRunner {

    @Test
    void myTestAnnotationTest() throws Exception {
        int myTestMethodCount = getMyTestMethodCount();

        Assertions.assertEquals(myTestMethodCount, 2);
    }

    private int getMyTestMethodCount() {
        JunitTest testInstance = new JunitTest();
        Method[] methods = JunitTest.class.getDeclaredMethods();

        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(MyTest.class))
                .map(method -> {
                    try {
                        return method.invoke(testInstance);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList()
                .size();
    }
}
