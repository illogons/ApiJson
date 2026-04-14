package org.example.apijson.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.apijson.Entity.ConfigEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ConfigResponseDTO {

    private Long id;
    private Long parent;
    private boolean delete;
    private String defaultValue;
    private Long attributeId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;



}
