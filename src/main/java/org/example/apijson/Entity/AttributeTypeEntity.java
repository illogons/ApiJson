package org.example.apijson.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "attribute_type")
public class AttributeTypeEntity {

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

    @Column(name= "ENUM_DESCRIPTION")
    private String enumDescription;

    @Column(name = "IS_ENUM")
    private Boolean isEnum;

    @Column(name= "IS_LIST")
    private Boolean isList;

    @Column(name = "TYPE")
    private String type;


}