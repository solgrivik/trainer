package spring.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionJdbcTemplateDao implements QuestionRepository {
    private static final String DDL_QUERY = """
            CREATE TABLE cards (
              id int PRIMARY KEY,
              question VARCHAR(100),
              expectedAnswer VARCHAR(40)
            )
            """;
    private static final String FIND_ALL_QUERY = """
            SELECT * FROM cards
            """;
    private static final String FIND_BY_ID_QUERY = """
            SELECT * FROM cards WHERE id = ?
            """;
    private static final String INSERT_CARD_QUERY = """
            INSERT INTO cards(id, question, expectedAnswer) VALUES (?, ?, ?)
            """;
    private static final String UPDATE_CARD_QUERY = """
            UPDATE cards SET id=?, question=?, expectedAnswer=?
            """;
    private static final String DELETE_CARD_QUERY = """
            DELETE FROM cards WHERE id=?
            """;
    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        initSchema();
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY,
                (ResultSet rs, int rowNum) ->
                        new OpenQuestionCard(
                                rs.getLong("id"),
                                rs.getString("question"),
                                rs.getString("expectedAnswer")
                        )
        );
    }

    @Override
    public Optional<BlankOpenQuestionCard> findById(Long id) {
        List<OpenQuestionCard> cards = jdbcTemplate.query(FIND_BY_ID_QUERY,
                (ResultSet rs, int rowNum) ->
                        new OpenQuestionCard(
                                rs.getLong("id"),
                                rs.getString("question"),
                                rs.getString("expectedAnswer")
                        ),
                id);
        return cards.isEmpty() ? Optional.empty() : Optional.of((BlankOpenQuestionCard) cards.get(0));
    }

    @Override
    public void add(BlankOpenQuestionCard card) {
        jdbcTemplate.update(INSERT_CARD_QUERY, card.getId(), card.getQuestion(), card.getExpectedAnswer());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(DELETE_CARD_QUERY);
    }

    @Override
    public void update(BlankOpenQuestionCard card) {
        jdbcTemplate.update(UPDATE_CARD_QUERY, card.getId(), card.getQuestion(), card.getExpectedAnswer());
    }

    private void initSchema() {
        jdbcTemplate.update(DDL_QUERY);
    }
}
