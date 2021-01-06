package org.sadtech.example.swagger.config;

import org.sadtech.example.swagger.dto.Gender;
import org.sadtech.example.swagger.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * // TODO: 30.12.2020 Добавить описание.
 *
 * @author upagge 30.12.2020
 */
@Configuration
public class AppConfig {

    @Bean
    public Map<String, UserDto> userRepository() {
        return Stream.of(
                UserDto.of("key1", "value1", Gender.MAN),
                UserDto.of("key2", "value2", Gender.WOMAN)
        ).collect(Collectors.toMap(UserDto::getKey, userDto -> userDto));
    }

}
