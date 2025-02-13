package com.uniovi.sdi2425211spring.validators;

import com.uniovi.sdi2425211spring.entities.Mark;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class AddMarksValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        if (mark.getDescription().length() < 20) {
            errors.rejectValue("description", "Error.addMark.description.length");}
        if (mark.getScore()<0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.addMark.score");}
    }
}
