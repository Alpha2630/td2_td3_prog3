package com.alpha.td4.Controller;

import com.alpha.td4.model.Student;
import com.alpha.td4.service.StudentService;
import com.alpha.td4.validator.StudentValidator;
import com.alpha.td4.exception.BadRequestException;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {

    private StudentService service = new StudentService();
    private StudentValidator validator = new StudentValidator();

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> students) {

        try {
            validator.validate(students);

            List<Student> result = service.addStudents(students);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }
}