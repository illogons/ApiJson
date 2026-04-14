package org.example.apijson.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributeRequestDTO {


    @NotNull(message = "pon algo")
    private String name;
    private Long attributeTypeId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "pon algo")
    private LocalDateTime createdAt;



}
