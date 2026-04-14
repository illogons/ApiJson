package org.example.apijson.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
@Table(name= "config")

public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name = "DELETED")
    private Boolean deleted;

    @Column(name = "CREATED_AT" )
    private LocalDateTime createdAt;

    @Column(name= "VERSION_LOCK")
    private Long versionLock;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_CUSTOM")
    private Boolean isCustom;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @Column(name = "APPLICATION_NODE")
    private String applicationNode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID")
    private AttributeEntity attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT")
    private ConfigEntity parentConfig;







}
