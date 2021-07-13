package com.luv2code.springboot.springassignment.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank(message="is required")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message="is required")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message="is required")
    @Column(name="email")
    private String email;
}
