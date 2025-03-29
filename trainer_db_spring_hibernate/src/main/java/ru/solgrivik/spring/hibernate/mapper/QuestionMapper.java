package ru.solgrivik.spring.hibernate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.solgrivik.domain.model.OpenQuestionCard;
import ru.solgrivik.spring.hibernate.entity.OpenQuestionCardEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "expectedAnswer", ignore = true)
    OpenQuestionCard mapToModel(OpenQuestionCardEntity entity);
    OpenQuestionCardEntity mapToEntity(OpenQuestionCard card);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCardEntity> entities);
    List <OpenQuestionCardEntity> mapToEntity(List<OpenQuestionCard> cards);
}
