package com.metin.github.steamapi.streamapi.model;

import java.util.List;

public class Student {

    public Student(Long id, String name, String surname, List<String> courses, double grade) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.courses = courses;
        this.grade = grade;
    }

    private Long id;
    private String name;
    private String surname;
    private List<String> courses;
    private double grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", courses=" + courses +
                ", grade=" + grade +
                '}';
    }
}
