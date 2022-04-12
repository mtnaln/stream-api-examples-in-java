package com.metin.github.steamapi.streamapi;

import com.metin.github.steamapi.streamapi.model.Student;
import com.metin.github.steamapi.streamapi.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamApiApplication implements CommandLineRunner {

    private final StudentService studentService;

    public StreamApiApplication(StudentService studentService) {
        this.studentService = studentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StreamApiApplication.class, args);
    }

    @Override
    public void run(String... args) {

        List<Student> students = studentService.getStudents();

        // Stream.of convert List<Student> to Stream<List<Student>>
        Stream<List<Student>> studentAsStudentListStream = Stream.of(students);

        // Stream.of convert List<Student> to Stream<Student>
        Stream<Student> studentAsStudentStream = students.stream();

        // Terminal operation example: forEach
        // forEach takes Consumer parameter
        students.stream().forEach(student -> System.out.println(student));
        // forEach takes parameter as method reference
        students.stream().forEach(System.out::println);

        // map --> intermediate operation
        // intermediate operation returns Stream so intermediate operations can be called many times.
        // But terminal operation can be called once
        Stream<Student> studentsStream = students.stream()
                .map(student -> new Student(student.getId(), student.getName().toUpperCase(),
                        student.getSurname().toUpperCase(), student.getCourses(), student.getGrade()));


        // Firstly, intermediate operation then terminal operation, returns List of Student
        List<Student> studentsNewFormat = students.stream()
                .map(student -> new Student(student.getId(), student.getName().toUpperCase(),
                        student.getSurname().toUpperCase(), student.getCourses(), student.getGrade()))
                .collect(Collectors.toList());


        // filter --> intermediate operation
        List<Student> studentsWhoGradeGreaterThan3 = students.stream()
                .filter(student -> student.getGrade() >= 3)
                .collect(Collectors.toList());

        // findFirst and findAny return Optional
        // you can control is value exist in studentWhoGradeGreaterThan4FirstOne --> if (studentWhoGradeGreaterThan4FirstOne.isPresent())
        Optional<Student> studentWhoGradeGreaterThan4FirstOne = students.stream()
                .filter(student -> student.getGrade() > 4)
                .findFirst();
        System.out.println(studentWhoGradeGreaterThan4FirstOne);

        // Alternative usage of optional --> no value case
        Student studentWhoGradeGreaterThan4FirstOne2 = students.stream()
                .filter(student -> student.getGrade() > 4)
                .findFirst()
                .orElse(null);
        System.out.println(studentWhoGradeGreaterThan4FirstOne2);

        // Alternative usage of optional --> value exists
        Student studentWhoGradeGreaterThan4FirstOne3 = students.stream()
                .filter(student -> student.getGrade() > 2)
                .findFirst()
                .orElse(null);
        System.out.println(studentWhoGradeGreaterThan4FirstOne3);

        // flatMap
        String courses = students.stream()
                .map(student -> student.getCourses())
                .flatMap(course -> course.stream())
                .collect(Collectors.joining(","));
        System.out.println(courses);

        // short circuit operations --> skip and limit
        List<Student> skipAndLimitExample = students.stream()
                .skip(1)   // skip first record
                .limit(2)  // show only two records
                .collect(Collectors.toList());
        System.out.println(skipAndLimitExample);

        // Generate data
        List<Double> randomNumbers = Stream.generate(Math::random)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(randomNumbers);

        // Generate data example 2
        List<Double> randomNumbers2 = Stream.generate(() -> Math.random() * 100)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(randomNumbers2);

        // Sorting
        List<Student> sortedList = students.stream()
                .sorted(Comparator.comparing(student -> student.getGrade()))
                .collect(Collectors.toList());
        System.out.println(sortedList);

        // Aggregation --> min and max
        Optional<Student> maxValue = students.stream().max(Comparator.comparing(student -> student.getGrade()));
        if (maxValue.isPresent()) {
            System.out.println(maxValue.get());
        }

        // Accumulation --> reduce
        Double totalGrade = students.stream()
                .map(student -> student.getGrade())
                .reduce(0d, Double::sum);
        System.out.println("Total Grades: " + totalGrade);
        System.out.println("Student Count: " + students.size());
        System.out.println("Average: " + totalGrade / students.size());

        // ParallelStream
        long startForStream = new Date().getTime();
        List<String> stream = students.stream()
                .map(student -> student.getName() + " - " + student.getSurname() + " - " + student.getGrade())
                .collect(Collectors.toList());
        long endForStream = new Date().getTime();
        long timeForStream = endForStream - startForStream;
        System.out.println(timeForStream);

        long startForParallelStream = new Date().getTime();
        List<String> streamParallel = students.stream()
                .map(student -> student.getName() + " - " + student.getSurname() + " - " + student.getGrade())
                .collect(Collectors.toList());
        long endForParallelStream = new Date().getTime();
        long timeForParallelStream = endForParallelStream - startForParallelStream;
        System.out.println(timeForParallelStream);
    }
}
