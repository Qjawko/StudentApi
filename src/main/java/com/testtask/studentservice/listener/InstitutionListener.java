package com.testtask.studentservice.listener;

import com.testtask.studentservice.model.Institution;
import com.testtask.studentservice.service.InstitutionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InstitutionListener {
    private final InstitutionService institutionService;

    public InstitutionListener(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RabbitListener(queues = {"institution_q"})
    public void create(Institution institution) {
        institutionService.create(institution);
    }
}
