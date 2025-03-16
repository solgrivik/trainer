package ru.solgrivik.controller;

import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.service.QuestionService;

import java.util.Optional;
import java.util.Scanner;

public class ConsoleController {
    private static final String MENU = """
          Введите [1], чтобы показать все карточки
          Введите [2], чтобы добавить карточку
          Введите [3], чтобы удалить карточку
          Введите [4], чтобы найти карточку
          Введите [5], чтобы выйти
          """;
    private final QuestionService service;
    private final Scanner scanner;

    public ConsoleController(QuestionService service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println(MENU);
    }

    private void printAllCards() {
        System.out.println(service.getAll());
    }

    private void addCard() {
        System.out.println("Введите вопрос");
        String question = scanner.nextLine();
        System.out.println("Введите ожидаемый ответ");
        String expectedAnswer = scanner.nextLine();
        System.out.println("Введите ID карточки");
        Long id = scanner.nextLong();
        BlankOpenQuestionCard card = new BlankOpenQuestionCard(id, question, expectedAnswer);
        service.save(card);
    }

    private void removeCard() {
        System.out.println("Введите код карточки, которую хотите удалить");
        Long id = scanner.nextLong();
        Optional<OpenQuestionCard> card = service.getById(id);
        if (card.isPresent()) {
            System.out.println("Введите [Y], если точно хотите удалить карточку " + card.get());
            String confirmation = scanner.nextLine();
            if ("Y".equals(confirmation)) {
                service.delete(card.get());
            }
        } else {
            System.out.println("Такой карточки найти не удалось");
        }
    }

    private void findCard() {
        System.out.println("Введите код карточки, которую хотите найти");
        Long id = scanner.nextLong();
        Optional<OpenQuestionCard> card = service.getById(id);
        if (card.isPresent()) {
            System.out.println(card.get());
        } else {
            System.out.println("Такой карточки найти не удалось");
        }
    }

    public void interactWithUser() {
        while(true) {
            printMenu();
            String operationCode = scanner.nextLine();
            switch (operationCode) {
                case "1" -> printAllCards();
                case "2" -> addCard();
                case "3" -> removeCard();
                case "4" -> findCard();
                case "5" -> { return; }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
}
