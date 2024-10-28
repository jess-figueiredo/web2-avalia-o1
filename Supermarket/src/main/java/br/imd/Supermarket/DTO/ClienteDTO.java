package br.imd.Supermarket.DTO;

import br.imd.Supermarket.model.ClienteEntity;
import br.imd.Supermarket.model.ProdutoEntity;
import java.util.Date;

public record ClienteDTO (
        String nome,
        String cpf,
        ClienteEntity.Genero genero,
        Date dataNascimento
){
}
