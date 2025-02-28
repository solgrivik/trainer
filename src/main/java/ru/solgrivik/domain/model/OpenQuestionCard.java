package ru.solgrivik.domain.model;

public class OpenQuestionCard {
    private final String question;
    private final String expectedAnswer;

    public OpenQuestionCard(String question, String expectedAnswer) {
        if (question == null) {
            throw new IllegalArgumentException("Был введён пустой вопрос");
        }

        if (expectedAnswer == null) {
            throw new IllegalArgumentException("Отсутствует ответ на введённый вопрос");
        }

        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    public String getQuestion(){
        return question;
    }

    public boolean checkAnswer (String answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Был введён пустой ответ");
        }

        return answer.equals(expectedAnswer);
    }
}
