package de.kiltz.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={KundenNummerConstraintValidator.class})
public @interface KundenNummerConstraint {
	String message() default "Kundennummer ist ungültig!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
