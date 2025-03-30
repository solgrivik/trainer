package ru.solgrivik.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.solgrivik.api.dto.OpenQuestionCardDto;
import ru.solgrivik.api.mapper.QuestionDtoMapper;
import ru.solgrivik.domain.service.QuestionService;

@Controller
@RequestMapping(value = "/card")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionDtoMapper mapper;

    public QuestionController(QuestionService questionService, QuestionDtoMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("card", new OpenQuestionCardDto());
        return "edit_task";
    }

    @PostMapping("/save")
    public String add(@ModelAttribute OpenQuestionCardDto card, Model model) {
        questionService.save(mapper.mapToModel(card));
        model.addAttribute("cards", mapper.mapToDto(questionService.getAll()));
        return "main";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable("id") Long id, Model model) {
        model.addAttribute("card", mapper.mapToDto(questionService.getById(id).get()));
        return "edit_task";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        questionService.getById(id).ifPresent(questionService::delete);
        model.addAttribute("cards", mapper.mapToDto(questionService.getAll()));
        return "main";
    }
}
