package ru.solgrivik.domain.model;

public sealed class OpenQuestionCard permits BlankOpenQuestionCard{
    private final Long id;
    private final String question;
    protected final String expectedAnswer;

    public OpenQuestionCard(Long id, String question, String expectedAnswer) {
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
