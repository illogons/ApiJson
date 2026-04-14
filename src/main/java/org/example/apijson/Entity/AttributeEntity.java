package org.example.apijson.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
@Table(name = "attribute")
public class AttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "NAME", nullable = false, unique = true)
    private String name;

    @Column(name= "CREATED_AT",nullable = false)
    private LocalDateTime createdAt;

    @Column(name= "DELETED")
    private Boolean deleted;

    @Column(name= "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name= "VERSION_LOCK")
    private Long versionLock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_TYPE")
    private AttributeTypeEntity AtributeType;


}
