package de.kiltz.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class KundenNummerConstraintValidator 
	implements ConstraintValidator<KundenNummerConstraint, String>{

	@Override
	public void initialize(KundenNummerConstraint cons) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		
		if (value.startsWith("k") && value.length() == 6) {
			return true;
		}
		return false;
	}

}
