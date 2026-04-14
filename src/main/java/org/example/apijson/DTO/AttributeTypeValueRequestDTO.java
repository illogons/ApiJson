package org.example.apijson.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AttributeTypeValueRequestDTO {
    private Long id;
    private String value;
    private Long attributeTypeId;
    private boolean deleted;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
