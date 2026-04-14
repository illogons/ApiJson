package org.example.apijson.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AttributeTypeValueResponseDTO {

    private Long id;

    @NotNull(message = "pon algo")
    private String value;
    private Long attributeTypeId;
    private boolean deleted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "pon algo")
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;




}
