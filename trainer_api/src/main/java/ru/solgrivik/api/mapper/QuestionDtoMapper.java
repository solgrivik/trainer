package ru.solgrivik.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.solgrivik.api.dto.OpenQuestionCardDto;
import ru.solgrivik.domain.model.BlankOpenQuestionCard;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.spring.hibernate.entity.OpenQuestionCardEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {
    @Mapping(target = "expectedAnswer", ignore = true)
    BlankOpenQuestionCard mapToModel(OpenQuestionCardDto entity);
    OpenQuestionCardEntity mapToDto(OpenQuestionCard card);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCard> entities);
    List <OpenQuestionCardEntity> mapToDto(List<OpenQuestionCard> cards);
}
