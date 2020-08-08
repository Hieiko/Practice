import java.util.List;

public class AnnotationDemo1 {
    @Test
    @Select(value = {"select * from a","select * from b"})
    public void test(){
        @Test
        int a;
    }
    @Select(value = "select * from a",age =1)
    public void test1(){
        @Test
        int a;
    }
    @Select("select * from a")
    public void test2(){
        @Test
        int a;
    }
    @Override
    public String toString(){
        @SuppressWarnings("rawtypes")
        List a=null;

        System.out.println(a);

        return super.toString();
    }
}
