package org.example.apijson.Repository;

import org.example.apijson.Model.AttributeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeModel, Long> {

    Optional<AttributeModel> findById(Long id);

    List<AttributeModel> findAllByDeletedFalse(Long id);

    List<AttributeModel> findAllByDeletedFalse();

    List<AttributeModel> findAllByDeletedTrue();


    Optional<AttributeModel> findByName(String nombre);
}
