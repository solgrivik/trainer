package ru.solgrivik.gui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.solgrivik.domain.service.QuestionService;
import ru.solgrivik.gui.config.SpringConfig;
import ru.solgrivik.gui.controller.MainController;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        QuestionService questionService =context.getBean(QuestionService.class);
        SwingUtilities.invokeLater(new MainController(questionService));
    }
}
