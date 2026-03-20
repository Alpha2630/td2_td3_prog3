package com.alpha.td3.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String reference;
    private String firstName;
    private String lastName;
    private int age;
}
