package ru.solgrivik.domain.repo;

import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<OpenQuestionCard> findAll();
    Optional<BlankOpenQuestionCard> findById(Long id);
    void add(BlankOpenQuestionCard card);
    void update(BlankOpenQuestionCard card);
    void remove(Long id);
}
