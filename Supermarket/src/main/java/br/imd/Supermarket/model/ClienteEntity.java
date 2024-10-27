package br.imd.Supermarket.model;

import br.imd.Supermarket.DTO.ClienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
@Entity
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nome;
    String cpf;
    private ProdutoEntity.Genero genero;
    public enum Genero {
        F,
        M,
        NB
    }
    Date dataNascimento;

    public ClienteEntity(ClienteDTO clienteDTO) {
        this.cpf = clienteDTO.cpf();
        this.nome = clienteDTO.nome();
        this.genero = clienteDTO.genero();
        this.dataNascimento = clienteDTO.dataNascimento();
    }
}
