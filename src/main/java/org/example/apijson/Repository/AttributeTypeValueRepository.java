package org.example.apijson.Repository;

import org.example.apijson.Model.AttributeTypeValueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeTypeValueRepository extends JpaRepository<AttributeTypeValueModel, Long> {

    Optional<AttributeTypeValueModel> findById(Long id);

    List<AttributeTypeValueModel> findAllByDeletedFalse(Long id);

    List<AttributeTypeValueModel> findAllByDeletedFalse();

    List<AttributeTypeValueModel> findAllByDeletedTrue();


    List<AttributeTypeValueModel> findByAttributeType_IdAndDeletedFalse(Long id);



}

