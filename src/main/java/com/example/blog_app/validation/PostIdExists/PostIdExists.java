package com.example.blog_app.validation.PostIdExists;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Validate that the string is between min and max included.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
@Documented
@Constraint(validatedBy = {PostIdExistsValidator.class})
@Target({FIELD, PARAMETER,METHOD,ANNOTATION_TYPE,CONSTRUCTOR,TYPE_USE })
@Retention(RUNTIME)
public @interface PostIdExists {

  String message() default "Bài viết không tồn tại";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

}
