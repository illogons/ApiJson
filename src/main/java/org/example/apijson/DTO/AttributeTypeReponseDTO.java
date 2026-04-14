package org.example.apijson.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class AttributeTypeReponseDTO {

    private Long id;
    private String Type;
    private boolean deleted;
    private Boolean isEnum;
    private Boolean isList;




}
