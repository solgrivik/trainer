package ru.solgrivik.jdbc.dao;

import org.springframework.stereotype.Repository;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionJdbcDao implements QuestionRepository {
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
    private final DataSource dataSource;

    public QuestionJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
        initDataBase();
    }

    public void initDataBase() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DDL_QUERY)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCard> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                OpenQuestionCard card = new OpenQuestionCard(
                        resultSet.getLong("id"),
                        resultSet.getString("question"),
                        resultSet.getString("expectedAnswer")
                );
                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public Optional<OpenQuestionCard> findById(Long id) {
        List<OpenQuestionCard> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OpenQuestionCard card = new OpenQuestionCard(
                        resultSet.getLong("id"),
                        resultSet.getString("question"),
                        resultSet.getString("expectedAnswer")
                );
                cards.add(card);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards.isEmpty() ? Optional.empty() : Optional.of(cards.get(0));
    }

    @Override
    public void add(BlankOpenQuestionCard card) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CARD_QUERY);) {
            statement.setString(1, String.valueOf(card.getId()));
            statement.setString(2, card.getQuestion());
            statement.setString(3, card.getExpectedAnswer());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BlankOpenQuestionCard card) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CARD_QUERY);) {
            statement.setString(1, String.valueOf(card.getId()));
            statement.setString(2, card.getQuestion());
            statement.setString(3, card.getExpectedAnswer());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CARD_QUERY);) {
            statement.setString(1, String.valueOf(id));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}