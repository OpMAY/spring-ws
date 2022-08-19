package com.validator.test;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Test.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Test test = (Test) o;
        if (test.getName() == null) {
            errors.rejectValue("reason", "name", "이름이 없습니다.");
        }
        if (test.getAge() == 0) {
            errors.rejectValue("reason", "age", "나이가 0살입니다.");
        }
    }
}
