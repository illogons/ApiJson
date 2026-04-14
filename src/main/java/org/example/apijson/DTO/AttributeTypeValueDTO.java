package org.example.apijson.DTO;

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

public class AttributeTypeValueDTO {

    private Long id;
    private String value;
    private Long attributeTypeId;
    private boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;




}
