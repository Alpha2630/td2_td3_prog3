package com.alpha.td4.validator;

import com.alpha.td4.model.Student;
import com.alpha.td4.exception.BadRequestException;

import java.util.List;

public class StudentValidator {

    public void validate(List<Student> students) {

        for (Student s : students) {

            if (s.getReference() == null || s.getReference().isEmpty()) {
                throw new BadRequestException("Reference manquante");
            }

            if (s.getFirstName() == null || s.getFirstName().isEmpty()) {
                throw new BadRequestException("FirstName manquant");
            }

            if (s.getLastName() == null || s.getLastName().isEmpty()) {
                throw new BadRequestException("LastName manquant");
            }
        }
    }
}