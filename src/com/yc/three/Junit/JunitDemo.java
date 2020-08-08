public class JunitDemo {
    @Before
    public void before(){
        System.out.println("before");
    }
    @After
    public void after(){
        System.out.println("after");
    }

    @JunitTest
    public void test1(){
        System.out.println("test1");
    }

    @JunitTest
    public void test2(){
        System.out.println("test2");
    }

    public void test3(){
        System.out.println("test3");
    }
}
