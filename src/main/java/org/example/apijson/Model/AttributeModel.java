package org.example.apijson.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "attribute")
@EntityListeners(AuditingEntityListener.class)  // ← añade esto
public class AttributeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "NAME", nullable = false, unique = true)
    private String name;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name= "DELETED")
    private Boolean deleted = false;

    @Column(name= "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name= "VERSION_LOCK")
    private Long versionLock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_TYPE")
    private AttributeTypeModel AtributeType;


}
