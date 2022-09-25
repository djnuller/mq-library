package dk.jdsj.mq;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MqItem {
    int length();
    boolean rightPad() default true;
    char padding() default ' ';
    char trueChar() default 'Y';
    char falseChar() default 'N';
}
