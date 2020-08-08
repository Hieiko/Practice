import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD,
    ElementType.LOCAL_VARIABLE})
public @interface Test {

}
@Target(value = {ElementType.METHOD})
@interface  Select{
    String[] value();

    int age() default 100;
}