package br.imd.Supermarket.DTO;

import br.imd.Supermarket.model.ProdutoEntity;
import java.util.Date;

public record ClienteDTO (
        String nome,
        String cpf,
        ProdutoEntity.Genero genero,
        Date dataNascimento
){
}
