package com.testtask.studentservice.listener;

import com.testtask.studentservice.model.Department;
import com.testtask.studentservice.service.DepartmentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DepartmentListener {
    private final DepartmentService departmentService;

    public DepartmentListener(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RabbitListener(queues = {"department_q"})
    public void create(Department department) {
        departmentService.create(department);
    }
}
