package de.kiltz.valid;

import java.util.Set;

import static org.junit.Assert.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class DerTester {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testeBean() {

        DieBean b = new DieBean();
        b.setEmail("f@kiltz.de");
        b.setNichtNull("text");
        b.setKdNr("k12345");
        b.setPlz("12345");
        Set<ConstraintViolation<DieBean>> constraintViolations = validator
                .validate(b);
        for (ConstraintViolation<DieBean> fehler : constraintViolations) {

            System.out.println("Message: " + fehler.getMessage());
            System.out.println("InvalidValue: " + fehler.getInvalidValue());
            System.out.println("LeafBean: " + fehler.getLeafBean());
            System.out.println("MessageTemplate: "
                    + fehler.getMessageTemplate());
            System.out.println("PropertyPath: " + fehler.getPropertyPath());
            System.out.println("RootBean: " + fehler.getRootBean());
        }
    }
}
