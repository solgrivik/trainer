package ru.solgrivik.domain.model;

public class OpenQuestionCard {
    private final String question;
    private final String expectedAnswer;
    private final Long id;

    public OpenQuestionCard(String question, String expectedAnswer, Long id) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Был введён пустой вопрос");
        }

        if (expectedAnswer == null || expectedAnswer.isEmpty()) {
            throw new IllegalArgumentException("Отсутствует ответ на введённый вопрос");
        }

        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.id = id;
    }

    public String getQuestion(){
        return question;
    }

    public Long getId(){
        return id;
    }

    public boolean checkAnswer (String answer) {
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Был введён пустой ответ");
        }
        return answer.equals(expectedAnswer);
    }
}
