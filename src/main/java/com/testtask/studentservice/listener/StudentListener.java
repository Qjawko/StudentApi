package com.testtask.studentservice.listener;

import com.testtask.studentservice.model.Student;
import com.testtask.studentservice.service.StudentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StudentListener {
    private final StudentService studentService;

    public StudentListener(StudentService studentService) {
        this.studentService = studentService;
    }

    @RabbitListener(queues = {"student_q"})
    public void create(Student student) {
        studentService.create(student);
    }
}
