import java.lang.reflect.Method;

import static java.lang.Class.forName;

@Test("测试类")
public class AnnotationDemo2 {
    @Test("测试方法")
    public void test(){

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,SecurityException {
        AnnotationDemo2 d=new AnnotationDemo2();
        //获取一个类的类对象
        Class<?> cls = AnnotationDemo2.class;
        cls=d.getClass();
//        cls= Class.forName("com.yc.three.Annotations.Demo2");

        cls.getFields();
        cls.getMethods();
        cls.getConstructors();
        /* ... */
        cls.getAnnotations();
        Test test=cls.getAnnotation(Test.class);
        System.out.println(test.value());
        Method method=cls.getMethod("test");

        Test test1= method.getAnnotation(Test.class);
        System.out.println(test1.value());
    }
}
