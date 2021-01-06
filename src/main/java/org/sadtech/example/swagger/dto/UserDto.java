package org.sadtech.example.swagger.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author upagge 30.12.2020
 */
@Schema(description = "Пользователь")
public class UserDto {

    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private String key;

    @Schema(description = "ФИО", example = "Иванов Иван Иванович")
    private String name;

    @Schema(description = "Баллы пользователя", accessMode = Schema.AccessMode.READ_ONLY)
    private Long points = 0L;

    @Schema(description = "Пол пользователя")
    private Gender gender;

    @Schema(description = "Дата и время регистрации", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime regDate = LocalDateTime.now();

    public UserDto() {
    }

    public UserDto(String key, String name, Gender gender) {
        this.key = key;
        this.name = name;
        this.gender = gender;
    }

    public static UserDto of(String key, String value, Gender gender) {
        return new UserDto(key, value, gender);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
