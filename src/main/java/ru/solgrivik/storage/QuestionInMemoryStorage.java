package ru.solgrivik.storage;

import org.springframework.stereotype.Repository;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;

import java.util.*;

public class QuestionInMemoryStorage implements QuestionRepository {
    private final Map<Long, OpenQuestionCard> cards;

    public QuestionInMemoryStorage() {
        cards = new HashMap<>();
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        return cards.values().stream().toList();
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        OpenQuestionCard foundCard = cards.get(id);
        if (Objects.nonNull(foundCard)) {
            return Optional.of(foundCard);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void add(BlankOpenQuestionCard card) {
        cards.put(card.getId(), card);
    }

    @Override
    public void update(BlankOpenQuestionCard card) {
        cards.put(card.getId(), card);
    }

    @Override
    public void remove(Long id) {
        cards.remove(id);
    }
}
