package org.example.apijson.Repository;

import org.example.apijson.Entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Long> {

    Optional<AttributeEntity> findById(Long id);

    List<AttributeEntity> findAllByDeletedFalse(Long id);

    List<AttributeEntity> findAllByDeletedFalse();

    List<AttributeEntity> findAllByDeletedTrue();


    Optional<AttributeEntity> findByName(String nombre);
}
