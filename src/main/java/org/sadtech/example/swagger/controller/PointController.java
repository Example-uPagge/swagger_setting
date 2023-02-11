package org.sadtech.example.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.sadtech.example.swagger.dto.TypeOperation;
import org.sadtech.example.swagger.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Управление баллами", description = "Позволяет удалить или добавить баллы пользователю")
    public HttpStatus changePoints(
            @PathVariable @NotBlank @Parameter(description = "Идентификатор пользователя", example = "key1") String key,
            @RequestParam("point") @Min(0) @Parameter(description = "Количество баллов", required = true, example = "10") Long point,
            @RequestParam("type") @Parameter(description = "Тип операции", required = true) TypeOperation type,
            HttpServletRequest request
    ) {
        final UserDto userDto = repository.get(key);
        userDto.setPoints(
                TypeOperation.PLUS.equals(type) ? userDto.getPoints() + point : userDto.getPoints() - point
        );
        return HttpStatus.OK;
    }

}
