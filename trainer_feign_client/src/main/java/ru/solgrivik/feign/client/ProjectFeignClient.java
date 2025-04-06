package ru.solgrivik.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ru.solgrivik.api.dto.OpenQuestionCardDto;

import java.util.List;

public interface ProjectFeignClient {
    @RequestLine("GET /project")
    @Headers({"Content-Type: application/json"})
    List<ru.solgrivik.api.dto.OpenQuestionCardDto> list();

    @RequestLine("POST /project")
    @Headers({"Content-Type: application/json"})
    void add(OpenQuestionCardDto dto);

    @RequestLine("PUT /project")
    @Headers({"Content-Type: application/json"})
    void update(OpenQuestionCardDto dto);

    @RequestLine("DELETE /project/{code}")
    @Headers({"Content-Type: application/json"})
    List<OpenQuestionCardDto> remove(@Param("id") Long id);
}
