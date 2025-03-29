package ru.solgrivik.gui.controller;

import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.service.QuestionService;
import ru.solgrivik.gui.model.CardTableModel;

import java.awt.*;
import java.util.Optional;

import javax.swing.*;

public class MainController implements Runnable {
    private final QuestionService service;
    private JFrame mainFrame;
    private JPanel mainPanel;

    public MainController(QuestionService service) {
        this.service = service;
    }

    @Override
    public void run() {
        mainFrame = new JFrame("Open Question Tool");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setJMenuBar(prepareMenu());
        prepareMainPanelForListCard();
        mainFrame.setVisible(true);
    }

    private JMenuBar prepareMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu cardMenu = new JMenu("Card");
        JMenuItem addCard = new JMenuItem("Добавить карточку");
        addCard.addActionListener((event) -> {
            prepareMainPanelForAddCard(Optional.empty());
        });
        cardMenu.add(addCard);
        menuBar.add(cardMenu);
        JMenuItem listCard = new JMenuItem("Просмтреть карточки");
        listCard.addActionListener((event) -> {
            prepareMainPanelForListCard();
        });
        cardMenu.add(listCard);
        JMenuItem removeCard = new JMenuItem("Удалить карточку");
        removeCard.addActionListener((event) -> {
            OpenQuestionCard cardToDelete = (OpenQuestionCard) JOptionPane.showInputDialog(
                    mainFrame,
                    "Какую карточку хотите удалить?",
                    "Удаление карточки",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    service.getAll().toArray(),
                    null);
            service.delete(cardToDelete);
            prepareMainPanelForListCard();
        });
        cardMenu.add(removeCard);
        JMenuItem editTask = new JMenuItem("Обновить карточку");
        editTask.addActionListener((event) -> {
            BlankOpenQuestionCard cardToDelete = (BlankOpenQuestionCard) JOptionPane.showInputDialog(
                    mainFrame,
                    "Какую карточку хотите изменить?",
                    "Удаление карточки",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    service.getAll().toArray(),
                    null);
            prepareMainPanelForAddCard(Optional.of(cardToDelete));
        });
        cardMenu.add(editTask);
        menuBar.add(cardMenu);
        return menuBar;
    }

    private void prepareMainPanelForAddCard(Optional<BlankOpenQuestionCard> cardForEdit) {
        if (mainPanel != null) {
            mainFrame.remove(mainPanel);
        }
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        mainPanel.add(new JLabel("ID"), layoutConstraints);
        JTextField idField = new JTextField(15);
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        mainPanel.add(idField, layoutConstraints);
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        mainPanel.add(new JLabel("Вопрос"), layoutConstraints);
        JTextField questionField = new JTextField(15);
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        mainPanel.add(questionField, layoutConstraints);
        mainPanel.add(new JLabel("Ожидаемый ответ"), layoutConstraints);
        JTextField expectedAnswerField = new JTextField(15);
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        mainPanel.add(expectedAnswerField, layoutConstraints);
        JButton addButton = new JButton("Добавить");
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.gridwidth = 2;
        cardForEdit.ifPresent( c -> {
            idField.setText(String.valueOf(c.getId()));
            expectedAnswerField.setText(c.getQuestion());
            expectedAnswerField.setText(c.getExpectedAnswer());
        });
        addButton.addActionListener(event -> {
            BlankOpenQuestionCard card = new BlankOpenQuestionCard(
                    Long.parseLong(idField.getText()),
                    expectedAnswerField.getText(),
                    expectedAnswerField.getText()
            );
            service.save(card);
            prepareMainPanelForListCard();
        });
        mainPanel.add(addButton, layoutConstraints);
        mainFrame.add(mainPanel);
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    private void prepareMainPanelForListCard() {
        if (mainPanel != null) {
            mainFrame.remove(mainPanel);
        }
        mainPanel = new JPanel(new BorderLayout());
        JTable table = new JTable(new CardTableModel(service.getAll()));
        mainPanel.add(table, BorderLayout.CENTER);
        mainPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        mainFrame.add(mainPanel);
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }
}