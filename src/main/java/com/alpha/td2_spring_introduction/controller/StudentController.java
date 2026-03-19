package com.alpha.td2_spring_introduction.controller;
import com.alpha.td2_spring_introduction.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class StudentController {

    private List<Student> students = new ArrayList<>();


    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "Welcome " + name;
    }



    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {
        students.addAll(newStudents);
        return "OK";
    }


    @GetMapping("/students")
    public String getStudents(@RequestHeader("Accept") String accept) {

        if (!accept.equals("text/plain")) {
            return "Format non supporté";
        }

        return students.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }
}