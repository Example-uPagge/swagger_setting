package org.sadtech.example.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sadtech.example.swagger.dto.TypeOperation;
import org.sadtech.example.swagger.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author upagge 30.12.2020
 */
@Validated
@RestController
@RequestMapping("api/user/point")
@Tag(name = "Система лояльности", description = "Управляет балами пользователей")
public class PointController {

    private final Map<String, UserDto> repository;

    public PointController(Map<String, UserDto> repository) {
        this.repository = repository;
    }

    @PostMapping("{key}")
    @Operation(summary = "Управление баллами", description = "Позволяет удалить или добавить баллы пользователю")
    public HttpStatus changePoints(
            @PathVariable @NotBlank @Parameter(description = "Идентификатор пользователя") String key,
            @RequestPart("point") @Min(0) @Parameter(description = "Количество баллов", required = true) Long point,
            @RequestPart("type") @Parameter(description = "Тип операции", required = true) TypeOperation type
    ) {
        final UserDto userDto = repository.get(key);
        userDto.setPoints(
                TypeOperation.PLUS.equals(type) ? userDto.getPoints() + point : userDto.getPoints() - point
        );
        return HttpStatus.OK;
    }

}
