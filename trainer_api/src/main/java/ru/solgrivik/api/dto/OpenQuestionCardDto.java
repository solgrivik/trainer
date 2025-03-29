package ru.solgrivik.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача в системе TM")
public class OpenQuestionCardDto {
    @Schema(description = "ID карточки", example = "123")
    private Long id;

    @Schema(description = "Вопрос", example = "Какая самая высокая гора в мире?")
    private String question;

    public OpenQuestionCardDto() {}

    public OpenQuestionCardDto(Long id, String question) {
        this.question = question;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String displayedName() {
        return String.format("%s: %s", id,  question);
    }
}
