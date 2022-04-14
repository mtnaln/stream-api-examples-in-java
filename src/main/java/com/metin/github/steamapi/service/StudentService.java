package com.metin.github.steamapi.service;

import com.metin.github.steamapi.model.Student;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students;

    public StudentService() {
        students = Arrays.asList(
                new Student(1L, "metin", "alnıaçık", Arrays.asList("math", "automata"), 3.00),
                new Student(2L, "yusuf", "alnıaçık", Arrays.asList("C++", "automata"), 2.50),
                new Student(3L, "kağan", "alnıaçık", Arrays.asList("math", "java"), 3.50),
                new Student(4L, "cemal", "kurt", Arrays.asList("php", "chemist"), 1),
                new Student(5L, "aylin", "akça", Arrays.asList("physics", "math"), 3.75)
        );
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
