package br.imd.Supermarket.model;

import br.imd.Supermarket.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    Long Id;
    String nomeProduto;
    String marca;
    LocalDate dataFabricacao;
    LocalDate dataValidade;
    private Genero genero;
    public enum Genero {
        COSMETICO,
        ALIMENTICIO,
        HIGIENE_PESSOAL,
        LIMPEZA
    };
    String lote;
    boolean ativo;

    public ProdutoEntity(){}

    public ProdutoEntity(ProdutoDTO produtoDTO){
        this.nomeProduto = produtoDTO.nomeProduto();
        this.marca = produtoDTO.marca();
        this.dataFabricacao = produtoDTO.dataFabricacao();
        this.dataValidade = produtoDTO.dataValidade();
        this.genero = produtoDTO.genero();
        this.lote = produtoDTO.lote();
        this.ativo = true;
    }
}
