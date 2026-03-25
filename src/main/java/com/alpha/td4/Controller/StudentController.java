package com.alpha.td4.Controller;

import com.alpha.td4.model.Student;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class StudentController {

    private List<Student> students = new ArrayList<>();


    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) { //ao arinan le requestparams matetik variable fon

        if (name == null || name.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Paramètre 'name' manquant");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Welcome " + name);
    }


    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {

        try {
            students.addAll(newStudents);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(students);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }


    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        try {

            if (accept == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Header Accept manquant");
            }


            if (accept.equals("text/plain")) {

                String result = students.stream()
                        .map(s -> s.getFirstName() + " " + s.getLastName())
                        .reduce("", (a, b) -> a + (a.isEmpty() ? "" : ", ") + b);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-Type", "text/plain")
                        .body(result);
            }


            if (accept.equals("application/json")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(students);
            }

            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("Format non supporté");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }
}
