package org.sadtech.example.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sadtech.example.swagger.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author upagge 30.12.2020
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
public class UserController {

    private final Map<String, UserDto> repository;

    public UserController(Map<String, UserDto> repository) {
        this.repository = repository;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Регистрация пользователя", description = "Позволяет зарегистрировать пользователя")
    public HttpStatus registerUser(@RequestBody UserDto userDto) {
        userDto.setPoints(0L);
        repository.put(userDto.getKey(), userDto);
        return HttpStatus.OK;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Обновление пользователя")
    public HttpStatus updateUser(@RequestBody UserDto userDto) {
        if (!repository.containsKey(userDto.getKey())) return HttpStatus.NOT_FOUND;
        if (!repository.get(userDto.getKey()).getPoints().equals(userDto.getPoints())) return HttpStatus.BAD_REQUEST;
        repository.put(userDto.getKey(), userDto);
        return HttpStatus.OK;
    }

    @GetMapping(value = "{key}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить пользователя")
    public ResponseEntity<UserDto> getSimpleDto(@PathVariable("key") String key) {
        return ResponseEntity.ok(repository.get(key));
    }

}
