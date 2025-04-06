package ru.solgrivik.domain.service;

import org.springframework.stereotype.Service;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    private boolean isCardInvalid(OpenQuestionCard card) {
        return Objects.isNull(card) || Objects.isNull(card.getId());
    }

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<OpenQuestionCard> getAll() {
        return repository.findAll();
    }

    public Optional<BlankOpenQuestionCard> getById(Long id) {
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        return repository.findById(id);
    }

    public List<OpenQuestionCard> findAll(){
        return repository.findAll();
    }

    public Optional<BlankOpenQuestionCard> findById(Long id){
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        return repository.findById(id);
    }

    public boolean contains(OpenQuestionCard card) {
        if (isCardInvalid(card)) {
            return false;
        }
        return repository.findById(card.getId()).isPresent();
    }

    public void save(BlankOpenQuestionCard card) {
        if (isCardInvalid(card)) {
            return;
        }
        if (contains(card)){
            repository.update(card);
        } else {
            repository.add(card);
        }
    }

    public void delete(OpenQuestionCard card) {
        if (isCardInvalid(card)) {
            return;
        }
        repository.remove(card.getId());
    }
}
