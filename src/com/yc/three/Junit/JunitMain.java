import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitMain {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        JunitDemo junitDemo=new JunitDemo();
        Class<?> cls=junitDemo.getClass();
        Method beforeMethod=getMethodByAnnotation(cls,Before.class);
        Method afterMethod=getMethodByAnnotation(cls,After.class);
        //获取所有方法进行遍历
        for (Method m:cls.getMethods()){
            if (m.getAnnotation(JunitTest.class) !=null&&m.getAnnotation(After.class)==null
                    &&m.getAnnotation(Before.class)==null){
                try{
                    if (beforeMethod!=null){
                        beforeMethod.invoke(junitDemo);
                    }
                 m.invoke(junitDemo);
                    if (afterMethod!=null){
                        afterMethod.invoke(junitDemo);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Method getMethodByAnnotation
            (Class<?> testCls, @SuppressWarnings("rawtypes")Class annoCls){
        for (Method m:testCls.getMethods()){
            if (m.getAnnotation(annoCls)!=null){
                return m;
            }
        }
        return null;
    }
}
