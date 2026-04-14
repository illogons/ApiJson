package org.example.apijson.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.apijson.DTO.AttributeTypeValueDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "attribute_type_value")
public class AttributeTypeValueEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATED_AT" )
    private LocalDateTime createdAt;

    @Column(name = "DELETED")
    private Boolean deleted;

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name= "VERSION_LOCK")
    private Long versionLock;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_TYPE")
    private AttributeTypeEntity attributeType;




}