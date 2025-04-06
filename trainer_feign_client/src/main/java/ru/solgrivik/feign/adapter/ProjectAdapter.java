package ru.solgrivik.feign.adapter;

import org.springframework.stereotype.Component;
import ru.solgrivik.api.dto.OpenQuestionCardDto;
import ru.solgrivik.api.mapper.QuestionDtoMapper;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.domain.repo.QuestionRepository;
import ru.solgrivik.feign.client.ProjectFeignClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectAdapter implements QuestionRepository {
    private final ProjectFeignClient feignClient;
    private final QuestionDtoMapper mapper;

    public ProjectAdapter(ProjectFeignClient feignClient, QuestionDtoMapper mapper) {
        this.feignClient = feignClient;
        this.mapper = mapper;
    }

    @Override
    public List<OpenQuestionCard> findAll() {
        List<OpenQuestionCardDto> projects = feignClient.list();
        return projects.stream()
                .map(mapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BlankOpenQuestionCard> findById(Long id) {
        List<OpenQuestionCardDto> projects = feignClient.list();
        return projects.stream()
                .map(mapper::mapToModel)
                .filter(project -> project.getId().equals(id))
                .findAny();
    }

    @Override
    public void add(BlankOpenQuestionCard project) {
        feignClient.add(mapper.mapToDto(project));
    }

    @Override
    public void update(BlankOpenQuestionCard project) {
        feignClient.update(mapper.mapToDto(project));
    }

    @Override
    public void remove(Long id) {
        feignClient.remove(id);
    }
}
