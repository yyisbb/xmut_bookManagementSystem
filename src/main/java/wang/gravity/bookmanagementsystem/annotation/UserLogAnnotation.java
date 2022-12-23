package wang.gravity.bookmanagementsystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import wang.gravity.bookmanagementsystem.constant.UserLogConst;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLogAnnotation {
    UserLogConst operation() ;
}
