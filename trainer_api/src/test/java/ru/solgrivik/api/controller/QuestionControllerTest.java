package ru.solgrivik.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.solgrivik.api.dto.OpenQuestionCardDto;

import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;


import java.util.List;

@SpringBootTest
class QuestionControllerTest {
    @MockitoBean
    private QuestionRepository taskRepository;

    @Autowired
    private QuestionController controller;

    @Test
    @DisplayName("Создание Task с корректными code и title проходит успешно")
    void having_task_when_list_then_return() {
        Mockito.when(taskRepository.findAll())
                .thenReturn(List.of(new OpenQuestionCard(1L, "Задача 1", "123")));
        List<OpenQuestionCardDto> list = controller.list();
        Assertions.assertEquals(1, list.size());
    }
}