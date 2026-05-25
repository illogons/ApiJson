package org.example.apijson.Repository;

import org.example.apijson.Model.AttributeTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeTypeRepository extends JpaRepository<AttributeTypeModel, Long> {

    Optional<AttributeTypeModel> findById(Long id);

    List<AttributeTypeModel> findIdByDeletedFalse(Long id);

    List<AttributeTypeModel> findAllByDeletedFalse();

    List<AttributeTypeModel> findAllByDeletedTrue();

    // Busca si ya existe un tipo exacto con esos 3 valores
    Optional<AttributeTypeModel> findByTypeAndIsListAndIsEnum(String type, Boolean isList, Boolean isEnum);

    Optional<AttributeTypeModel> findByType(String type);

}