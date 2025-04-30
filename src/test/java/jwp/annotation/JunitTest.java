package jwp.annotation;

public class JunitTest {

    @MyTest
    public void one() throws Exception {
        System.out.println("Running Test1");
    }

    @MyTest
    public void two() throws Exception {
        System.out.println("Running Test2");
    }

    public void three() {
        throw new RuntimeException("이 메소드는 실행되어선 안된다");
    }
}
