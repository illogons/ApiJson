package org.example.apijson.Repository;

import org.example.apijson.Entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {


    Optional<ConfigEntity> findById(Long id);

    List<ConfigEntity> findAll();

    // Busca cuando la configuración ES la raíz (no tiene padre)
    Optional<ConfigEntity> findByAttribute_IdAndParentConfigIsNull(Long attributeId);

    // Busca cuando la configuración está dentro de otra (tiene padre)
    Optional<ConfigEntity> findByAttribute_IdAndParentConfig_Id(Long attributeId, Long parentId);

}
