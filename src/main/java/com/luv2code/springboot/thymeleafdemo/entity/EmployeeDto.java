package com.luv2code.springboot.thymeleafdemo.entity;

import lombok.Data;

@Data
public class EmployeeDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
