package ru.solgrivik.gui.model;

import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CardTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"Id", "Вопрос", "Ожидаемый Ответ"};
    private final List<BlankOpenQuestionCard> cards;

    public CardTableModel(List<OpenQuestionCard> cards) {
        this.cards = cards;
    }

    @Override
    public int getRowCount() {
        return cards.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return cards.get(rowIndex).getId();
        } else {
            return cards.get(rowIndex).getQuestion();
        }
    }}
