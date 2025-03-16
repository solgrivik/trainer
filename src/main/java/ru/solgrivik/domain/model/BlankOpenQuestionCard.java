package ru.solgrivik.domain.model;

public final class BlankOpenQuestionCard extends OpenQuestionCard {

    public BlankOpenQuestionCard(Long id, String question, String expectedAnswer) {
        super(id, question, expectedAnswer);
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }
}
