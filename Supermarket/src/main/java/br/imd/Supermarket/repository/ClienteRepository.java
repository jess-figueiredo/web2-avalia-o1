package br.imd.Supermarket.repository;

import br.imd.Supermarket.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <ClienteEntity, Long> {
    @Query("SELECT c from ClienteEntity c WHERE c.ativo = true")
    List<ClienteEntity> findAllAtivos();

}
