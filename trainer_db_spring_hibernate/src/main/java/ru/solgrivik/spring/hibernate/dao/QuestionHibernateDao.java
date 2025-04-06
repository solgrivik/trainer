package ru.solgrivik.spring.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;
import ru.solgrivik.spring.hibernate.entity.OpenQuestionCardEntity;
import ru.solgrivik.spring.hibernate.mapper.QuestionMapper;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class QuestionHibernateDao implements QuestionRepository {
    private static final Logger logger = LogManager.getLogger(QuestionHibernateDao.class);
    private final QuestionMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionHibernateDao(QuestionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpenQuestionCard> findAll() {
        logger.debug("Выбираем всех");
        List<OpenQuestionCardEntity> cardEntities = entityManager
                .createQuery("SELECT card FROM OpenQuestionCardEntity card", OpenQuestionCardEntity.class)
                .getResultList();
        return mapper.mapToModel(cardEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BlankOpenQuestionCard> findById(Long id) {
        List<OpenQuestionCardEntity> entity =entityManager.createQuery(
            "SELECT card FROM OpenQuestionCardEntity card WHERE card.id = ?1",
                OpenQuestionCardEntity.class
        ).setParameter(1, id).getResultList();
        if (!entity.isEmpty()) {
            OpenQuestionCard card = mapper.mapToModel(entity.get(0));
            return Optional.of((BlankOpenQuestionCard) card);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void add(BlankOpenQuestionCard card) {
        OpenQuestionCardEntity entity = mapper.mapToEntity(card);
        entityManager.persist(entity);

    }

    @Override
    public void update(BlankOpenQuestionCard card) {
        OpenQuestionCardEntity entity = mapper.mapToEntity(card);
        entityManager.persist(entity);
    }

    @Override
    public void remove(Long id) {
        OpenQuestionCardEntity entity = entityManager.createQuery(
                "SELECT card FROM OpenQuestionCardEntity card WHERE card.id = ?1",
                OpenQuestionCardEntity.class
        ).setParameter(1, id).getSingleResult();
        entityManager.remove(entity);
    }
}
