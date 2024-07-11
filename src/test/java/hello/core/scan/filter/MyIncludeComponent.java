package hello.core.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // TYPE은 클래스 레벨
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
