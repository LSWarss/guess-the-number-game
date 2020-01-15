package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD}) // that indicates context in which it is applicable
@Retention(RetentionPolicy.RUNTIME) //it indicates how long the annotation types will stay
@Qualifier //it show us that this can be used as qualifier
public @interface MinNumber {

}
