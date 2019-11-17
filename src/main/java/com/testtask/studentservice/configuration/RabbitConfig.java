package com.testtask.studentservice.configuration;


import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@AllArgsConstructor
public class RabbitConfig implements RabbitListenerConfigurer {
    public static final String STUDENT = "student_q";
    public static final String DEPARTMENT = "department_q";
    public static final String INSTITUTION = "institution_q";

    public static String getExchangeName(String queueName) {
        return queueName.replaceFirst("_q$", "_x");
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(jackson2Converter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT);
    }

    @Bean
    public Queue departmentQueue() {
        return new Queue(DEPARTMENT);
    }

    @Bean
    public Queue institutionQueue() {
        return new Queue(INSTITUTION);
    }

    @Bean
    public TopicExchange subscriptionExchange() {
        return new TopicExchange(getExchangeName(DEPARTMENT), true, false);
    }

    @Bean
    public TopicExchange surveyExchange() {
        return new TopicExchange(getExchangeName(STUDENT), true, false);
    }

    @Bean
    public Binding subscriptionBinding(Queue departmentQueue, TopicExchange subscriptionExchange) {
        return BindingBuilder
                .bind(departmentQueue)
                .to(subscriptionExchange)
                .with(DEPARTMENT);
    }

    @Bean
    public Binding unsubscriptionBinding(Queue studentQueue, TopicExchange subscriptionExchange) {
        return BindingBuilder
                .bind(studentQueue)
                .to(subscriptionExchange)
                .with(STUDENT);
    }

    @Bean
    public Binding subscriptionConfirmationBinding(Queue institutionQueue, TopicExchange subscriptionExchange) {
        return BindingBuilder
                .bind(institutionQueue)
                .to(subscriptionExchange)
                .with(INSTITUTION);
    }
}
