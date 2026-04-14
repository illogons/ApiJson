package org.example.apijson.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ConfigRequestDto {

    private Long id;
    private Long parent;
    private boolean delete;
    private String defaultValue;
    private Long attributeId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
