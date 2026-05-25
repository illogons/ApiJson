package org.example.apijson.Repository;

import org.example.apijson.Model.ConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigModel, Long> {


    Optional<ConfigModel> findById(Long id);

    List<ConfigModel> findAll();

    // Busca cuando la configuración ES la raíz (no tiene padre)
    Optional<ConfigModel> findByAttribute_IdAndParentConfigIsNull(Long attributeId);

    // Busca cuando la configuración está dentro de otra (tiene padre)
    Optional<ConfigModel> findByAttribute_IdAndParentConfig_Id(Long attributeId, Long parentId);

}
