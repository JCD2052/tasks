package tests;

import org.testng.annotations.Test;

public class SimpleTest {
    private static final String SYS_ENV_1 = System.getProperty("MyEnv1");
    private static final String SYS_ENV_2 = System.getProperty("MyEnv2");

    @Test
    public void test1() {
        System.out.println("simple test 1");
    }

    @Test
    public void test2() {
        System.out.println(SYS_ENV_1);
    }

    @Test
    public void test3() {
        System.out.println(SYS_ENV_2);
    }
}
