package br.imd.Supermarket.DTO;

import br.imd.Supermarket.model.ProdutoEntity;

import java.time.LocalDate;

public record ProdutoDTO(
        String nomeProduto,
        String marca,
        LocalDate dataFabricacao,
        LocalDate dataValidade,
        ProdutoEntity.Genero genero,
        String lote
){
}
