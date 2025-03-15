package ru.solgrivik.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OpenQuestionCardTest {
    private static final String question = "What is the highest mountain in the World?";
    private static final String expectedAnswer = "Everest";
    private static final Long id = 12345L;
    private OpenQuestionCard card;

    @BeforeEach
    void setUp(){
        card = new OpenQuestionCard(id, question, expectedAnswer);
    }

    @Test
    @DisplayName("checkAnswer возвращает false при неверном ответе")
    void having_wrongAnswer_when_checkAnswer_then_returnsFalse() {
        Assertions.assertFalse(card.checkAnswer("Kilimanjaro"));
    }

    @Test
    @DisplayName("При вводе пустого ответа выбрасывается исключение")
    void having_nullAnswer_when_checkAnswer_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> card.checkAnswer(null));
    }

    @Test
    @DisplayName("При вводе пустого вопроса выбрасывается исключение")
    void having_nullQuestion_when_setCard_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new OpenQuestionCard(id, null, expectedAnswer));
    }

    @Test
    @DisplayName("При отсутствии ответа на введённый вопрос выбрасывается исключение")
    void having_nullExpectedAnswer_when_setCard_then_exceptionThrown() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new OpenQuestionCard(id, question, null));
    }
}