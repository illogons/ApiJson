package org.example.apijson.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeTypeRequestDTO {

    private Long id;

    @NotNull(message = "no puede ser nulo")
    private String Type;
    private boolean deleted;
    private Boolean isEnum;
    private Boolean isList;
}
