package org.example.apijson.Repository;

import org.example.apijson.Entity.AttributeEntity;
import org.example.apijson.Entity.AttributeTypeEntity;
import org.example.apijson.Entity.AttributeTypeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeTypeValueRepository extends JpaRepository<AttributeTypeValueEntity, Long> {

    Optional<AttributeTypeValueEntity> findById(Long id);

    List<AttributeTypeValueEntity> findAllByDeletedFalse(Long id);

    List<AttributeTypeValueEntity> findAllByDeletedFalse();

    List<AttributeTypeValueEntity> findAllByDeletedTrue();


    List<AttributeTypeValueEntity> findByAttributeType_IdAndDeletedFalse(Long id);



}

