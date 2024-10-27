package br.imd.Supermarket.repository;

import br.imd.Supermarket.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    @Query("SELECT p FROM ProdutoEntity p WHERE p.ativo = true")
    List<ProdutoEntity> findAllAtivos();
}
