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
public class AttributeResponseDTO {

    private Long id;

    private String name;

    private Long attributeTypeId;

    private boolean deleted;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;






}
