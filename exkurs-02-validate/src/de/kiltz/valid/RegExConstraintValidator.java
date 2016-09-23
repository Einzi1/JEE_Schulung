package de.kiltz.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegExConstraintValidator   
    implements ConstraintValidator<RegExConstraint, String>{

    private Pattern pattern;
    
    @Override
    public void initialize(RegExConstraint cons) {
        pattern = Pattern.compile(cons.value());
        
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        Matcher m = pattern.matcher(value);
        return m.matches();
    }

}
