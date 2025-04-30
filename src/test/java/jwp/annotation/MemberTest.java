package jwp.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @Test
    void MemberReflectionTest()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Constructor<Member> constructor = Member.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Member member = constructor.newInstance();

        Field name = Member.class.getDeclaredField("name");
        name.setAccessible(true);
        name.set(member, "박지원");

        Field age = Member.class.getDeclaredField("age");
        age.setAccessible(true);
        age.set(member, 20);

        Assertions.assertEquals(member.getName(), "박지원");
        Assertions.assertEquals(member.getAge(), 20);
    }
}
