package org.example.apijson.Repository;

import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeTypeRepository extends JpaRepository<AttributeTypeEntity, Long> {

    Optional<AttributeTypeEntity> findById(Long id);

    List<AttributeTypeEntity> findIdByDeletedFalse(Long id);

    List<AttributeTypeEntity> findAllByDeletedFalse();

    List<AttributeTypeEntity> findAllByDeletedTrue();

    // Busca si ya existe un tipo exacto con esos 3 valores
    Optional<AttributeTypeEntity> findByTypeAndIsListAndIsEnum(String type, Boolean isList, Boolean isEnum);

    Optional<AttributeTypeEntity> findByType(String type);

}